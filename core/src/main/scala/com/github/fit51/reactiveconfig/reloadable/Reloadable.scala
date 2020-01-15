package com.github.fit51.reactiveconfig.reloadable

import cats.MonadError
import cats.syntax.applicative._
import cats.syntax.applicativeError._
import cats.syntax.either._
import cats.syntax.flatMap._
import cats.syntax.functor._
import com.typesafe.scalalogging.StrictLogging
import monix.eval.Task
import monix.eval.TaskLift
import monix.eval.TaskLike
import monix.execution.Scheduler
import monix.reactive.Consumer
import monix.reactive.Observable
import monix.reactive.Observable.Operator
import monix.reactive.observers.Subscriber

import scala.concurrent.Future
import scala.util.control.NonFatal
import scala.util.Failure

object Reloadable {
  def apply[F[_], A](
      initial: A,
      ob: Observable[A]
  )(implicit scheduler: Scheduler, F: MonadError[F, Throwable], T: TaskLike[F], L: TaskLift[F]): F[Reloadable[F, A]] =
    (for {
      connectableObservable <- Task.pure(ob.publish)
      canceler   = connectableObservable.connect()
      reloadable = new Reloadable[F, A](initial, connectableObservable)
      fiber <- connectableObservable
        .consumeWith(Consumer.foreach { newValue =>
          reloadable.value = newValue
        })
        .start
    } yield {
      reloadable.canceler = (fiber.cancel >> Task.delay(canceler.cancel())).to[F]
      reloadable
    }).to[F]

  type Id[X] = X

  implicit val taskLikeId = new TaskLike[Id] {
    override def apply[A](fa: Id[A]): Task[A] = Task.pure(fa)
  }
}

/**
  * Reloadable is a wrapped [[B]] value, that can be accessed at any time.
  * Inside it encapsulates logic for updating [[B]] on [[A]] changes.
  *
  * @see [[ReloadableInternal]]
  * @param initial   value of type [[B]]
  * @param ob        observable of [[A]] changes
  * @param start     function to get [[B]] from [[A]]. Is used in [[behaviour]]
  * @param behaviour [[ReloadBehaviour]]
  * @tparam F [_] reloading effect
  * @tparam A input
  * @tparam B output
  **/
class Reloadable[F[_], A] private (initial: A, ob: Observable[A])(
    implicit scheduler: Scheduler,
    F: MonadError[F, Throwable],
    T: TaskLike[F],
    L: TaskLift[F]
) extends StrictLogging {
  import Reloadable._

  @volatile
  private var value = initial

  protected[reloadable] val observable: Observable[A] = ob

  def unsafeGet: A =
    value

  def get: F[A] =
    value.pure[F]

  def forEachF(f: A => F[Unit]): F[Unit] =
    (this.get >>= f) >> observable.mapEvalF(f).lastL.to[F]

  def map[B](
      f: A => B,
      reloadBehaviour: ReloadBehaviour[F, A, B] = ReloadBehaviour.simpleBehaviour[F, A, B]
  ): F[Reloadable[F, B]] =
    for {
      init <- get
      result <- reloadBehaviour match {
        case Simple() =>
          Reloadable.apply(f(value), observable.map(f))
        case Stop(stop) =>
          mapAndStopF[Id, A, B](f(init), observable, f, stop)
        case restart: Restart[F, A, B] =>
          mapAndRestartF(f(init), observable, restart)
      }
    } yield result

  def mapF[B](
      f: A => F[B],
      reloadBehaviour: ReloadBehaviour[F, A, B] = ReloadBehaviour.simpleBehaviour[F, A, B]
  ): F[Reloadable[F, B]] =
    (for {
      initB <- f(value)
      result <- reloadBehaviour match {
        case Simple() =>
          Reloadable.apply(initB, mapEvalAndSkipErrors(observable, f))
        case Stop(stop) =>
          mapAndStopF[F, A, B](initB, observable, f, stop)
        case restart: Restart[F, A, B] =>
          mapAndRestartF(initB, observable, restart)
      }
    } yield result) handleErrorWith {
      case excp =>
        log("Failed to construct init value for Reloadable.mapF", excp) >> F.raiseError(excp)
    }

  def combine[B, C](
      other: Reloadable[F, B],
      reloadBehaviour: ReloadBehaviour[F, (A, B), C] = ReloadBehaviour.simpleBehaviour[F, (A, B), C]
  )(f: (A, B) => C): F[Reloadable[F, C]] =
    for {
      val1 <- this.get
      val2 <- other.get
      combinedInit = f(val1, val2)
      result <- reloadBehaviour match {
        case Simple() =>
          val combinedObs = (val1 +: this.observable).combineLatestMap(val2 +: other.observable)(f).drop(1)
          Reloadable.apply(combinedInit, combinedObs)
        case Stop(stop) =>
          val combinedObs = (val1 +: this.observable).combineLatest(val2 +: other.observable).drop(1)
          mapAndStopF[Id, (A, B), C](combinedInit, combinedObs, f.tupled, stop)
        case restart: Restart[F, (A, B), C] =>
          val combinedObs = (val1 +: this.observable).combineLatest(val2 +: other.observable).drop(1)
          mapAndRestartF(combinedInit, combinedObs, restart)
      }
    } yield result

  def combineF[B, C](
      other: Reloadable[F, B],
      reloadBehaviour: ReloadBehaviour[F, (A, B), C] = ReloadBehaviour.simpleBehaviour[F, (A, B), C]
  )(f: (A, B) => F[C]): F[Reloadable[F, C]] =
    (for {
      val1         <- this.get
      val2         <- other.get
      combinedInit <- f(val1, val2)
      combinedObs = (val1 +: this.observable).combineLatest(val2 +: other.observable).drop(1)
      result <- reloadBehaviour match {
        case Simple() =>
          Reloadable.apply(combinedInit, mapEvalAndSkipErrors(combinedObs, f.tupled))
        case Stop(stop) =>
          mapAndStopF[F, (A, B), C](combinedInit, combinedObs, f.tupled, stop)
        case restart: Restart[F, (A, B), C] =>
          mapAndRestartF(combinedInit, combinedObs, restart)
      }
    } yield result) handleErrorWith {
      case excp =>
        log("Failed to construct init value for Reloadable.combineF", excp) >> F.raiseError(excp)
    }

  private var canceler: F[Unit] = F.unit

  def stop: F[Unit] =
    canceler

  private def mapEvalAndSkipErrors[G[_]: TaskLike, A, B](ob: Observable[A], f: A => G[B]): Observable[B] =
    ob.mapEvalF(f.andThen(Task.from(_).attempt)).collect { case Right(b) => b }

  private def mapAndStopF[G[_]: TaskLike, I, O](
      init: O,
      obs: Observable[I],
      f: I => G[O],
      stop: O => F[_]
  ): F[Reloadable[F, O]] = {
    val nextObservable = mapEvalAndSkipErrors[G, I, O](obs, f)
      .liftByOperator(new ReleasePrevOperator(init, stop.andThen(Task.from(_).void)))
    Reloadable.apply(init, nextObservable)
  }

  private def mapAndRestartF[O, I](
      init: O,
      obs: Observable[I],
      restart: Restart[F, I, O]
  ): F[Reloadable[F, O]] = {
    val nextObservable = obs
      .scanEvalF(init.asRight[O].pure[F]) {
        case (state, newEl) =>
          val o = state.fold(identity, identity)
          restart
            .restart(newEl, o)
            .map(_.asRight[O])
            .handleErrorWith {
              case e =>
                log("Failed to restart", e).as(o.asLeft[O])
            }
      }
      .collect { case Right(o) => o }
    Reloadable.apply(init, nextObservable)
  }

  private def log(message: String, e: Throwable): F[Unit] =
    Task.delay(logger.error(message, e)).to[F]
}

private class ReleasePrevOperator[A](init: A, release: A => Task[Unit]) extends Operator[A, A] with StrictLogging {

  import monix.execution.Ack
  import monix.execution.Ack.Stop

  override def apply(out: Subscriber[A]): Subscriber[A] =
    new Subscriber[A] {
      implicit val scheduler              = out.scheduler
      @volatile private[this] var prev: A = init

      override def onNext(elem: A): Future[Ack] = {
        try {
          val nonUsed = prev
          prev = elem
          val result = out.onNext(elem)
          result.onComplete { _ =>
            releaseWithLogging(nonUsed)
          }
          result
        } catch {
          case NonFatal(ex) =>
            onError(ex)
            Stop
        }
      }

      override def onError(ex: Throwable): Unit = {
        releaseWithLogging(prev)
        out.onError(ex)
      }

      override def onComplete(): Unit = {
        releaseWithLogging(prev)
        out.onComplete()
      }

      private def releaseWithLogging(elem: A): Unit =
        release(elem).runToFuture.onComplete {
          case Failure(excp) => logger.error(s"Failed to release $elem", excp)
          case _             => ()
        }
    }
}
