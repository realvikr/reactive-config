// Generated by the Scala Plugin for the Protocol Buffer Compiler.
// Do not edit!
//
// Protofile syntax: PROTO3

package com.github.fit51.reactiveconfig.etcd.gen.rpc

/** @param deleted
  *   deleted is the number of keys deleted by the delete range request.
  * @param prevKvs
  *   if prev_kv is set in the request, the previous key-value pairs will be returned.
  */
@SerialVersionUID(0L)
final case class DeleteRangeResponse(
    header: _root_.scala.Option[com.github.fit51.reactiveconfig.etcd.gen.rpc.ResponseHeader] = _root_.scala.None,
    deleted: _root_.scala.Long = 0L,
    prevKvs: _root_.scala.Seq[com.github.fit51.reactiveconfig.etcd.gen.kv.KeyValue] = _root_.scala.Seq.empty
    ) extends scalapb.GeneratedMessage with scalapb.Message[DeleteRangeResponse] with scalapb.lenses.Updatable[DeleteRangeResponse] {
    @transient
    private[this] var __serializedSizeCachedValue: _root_.scala.Int = 0
    private[this] def __computeSerializedValue(): _root_.scala.Int = {
      var __size = 0
      if (header.isDefined) {
        val __value = header.get
        __size += 1 + _root_.com.google.protobuf.CodedOutputStream.computeUInt32SizeNoTag(__value.serializedSize) + __value.serializedSize
      };
      
      {
        val __value = deleted
        if (__value != 0L) {
          __size += _root_.com.google.protobuf.CodedOutputStream.computeInt64Size(2, __value)
        }
      };
      prevKvs.foreach { __item =>
        val __value = __item
        __size += 1 + _root_.com.google.protobuf.CodedOutputStream.computeUInt32SizeNoTag(__value.serializedSize) + __value.serializedSize
      }
      __size
    }
    final override def serializedSize: _root_.scala.Int = {
      var read = __serializedSizeCachedValue
      if (read == 0) {
        read = __computeSerializedValue()
        __serializedSizeCachedValue = read
      }
      read
    }
    def writeTo(`_output__`: _root_.com.google.protobuf.CodedOutputStream): _root_.scala.Unit = {
      header.foreach { __v =>
        val __m = __v
        _output__.writeTag(1, 2)
        _output__.writeUInt32NoTag(__m.serializedSize)
        __m.writeTo(_output__)
      };
      {
        val __v = deleted
        if (__v != 0L) {
          _output__.writeInt64(2, __v)
        }
      };
      prevKvs.foreach { __v =>
        val __m = __v
        _output__.writeTag(3, 2)
        _output__.writeUInt32NoTag(__m.serializedSize)
        __m.writeTo(_output__)
      };
    }
    def mergeFrom(`_input__`: _root_.com.google.protobuf.CodedInputStream): com.github.fit51.reactiveconfig.etcd.gen.rpc.DeleteRangeResponse = {
      var __header = this.header
      var __deleted = this.deleted
      val __prevKvs = (_root_.scala.collection.immutable.Vector.newBuilder[com.github.fit51.reactiveconfig.etcd.gen.kv.KeyValue] ++= this.prevKvs)
      var _done__ = false
      while (!_done__) {
        val _tag__ = _input__.readTag()
        _tag__ match {
          case 0 => _done__ = true
          case 10 =>
            __header = Option(_root_.scalapb.LiteParser.readMessage(_input__, __header.getOrElse(com.github.fit51.reactiveconfig.etcd.gen.rpc.ResponseHeader.defaultInstance)))
          case 16 =>
            __deleted = _input__.readInt64()
          case 26 =>
            __prevKvs += _root_.scalapb.LiteParser.readMessage(_input__, com.github.fit51.reactiveconfig.etcd.gen.kv.KeyValue.defaultInstance)
          case tag => _input__.skipField(tag)
        }
      }
      com.github.fit51.reactiveconfig.etcd.gen.rpc.DeleteRangeResponse(
          header = __header,
          deleted = __deleted,
          prevKvs = __prevKvs.result()
      )
    }
    def getHeader: com.github.fit51.reactiveconfig.etcd.gen.rpc.ResponseHeader = header.getOrElse(com.github.fit51.reactiveconfig.etcd.gen.rpc.ResponseHeader.defaultInstance)
    def clearHeader: DeleteRangeResponse = copy(header = _root_.scala.None)
    def withHeader(__v: com.github.fit51.reactiveconfig.etcd.gen.rpc.ResponseHeader): DeleteRangeResponse = copy(header = Option(__v))
    def withDeleted(__v: _root_.scala.Long): DeleteRangeResponse = copy(deleted = __v)
    def clearPrevKvs = copy(prevKvs = _root_.scala.Seq.empty)
    def addPrevKvs(__vs: com.github.fit51.reactiveconfig.etcd.gen.kv.KeyValue*): DeleteRangeResponse = addAllPrevKvs(__vs)
    def addAllPrevKvs(__vs: Iterable[com.github.fit51.reactiveconfig.etcd.gen.kv.KeyValue]): DeleteRangeResponse = copy(prevKvs = prevKvs ++ __vs)
    def withPrevKvs(__v: _root_.scala.Seq[com.github.fit51.reactiveconfig.etcd.gen.kv.KeyValue]): DeleteRangeResponse = copy(prevKvs = __v)
    def getFieldByNumber(__fieldNumber: _root_.scala.Int): _root_.scala.Any = {
      (__fieldNumber: @_root_.scala.unchecked) match {
        case 1 => header.orNull
        case 2 => {
          val __t = deleted
          if (__t != 0L) __t else null
        }
        case 3 => prevKvs
      }
    }
    def getField(__field: _root_.scalapb.descriptors.FieldDescriptor): _root_.scalapb.descriptors.PValue = {
      _root_.scala.Predef.require(__field.containingMessage eq companion.scalaDescriptor)
      (__field.number: @_root_.scala.unchecked) match {
        case 1 => header.map(_.toPMessage).getOrElse(_root_.scalapb.descriptors.PEmpty)
        case 2 => _root_.scalapb.descriptors.PLong(deleted)
        case 3 => _root_.scalapb.descriptors.PRepeated(prevKvs.iterator.map(_.toPMessage).toVector)
      }
    }
    def toProtoString: _root_.scala.Predef.String = _root_.scalapb.TextFormat.printToUnicodeString(this)
    def companion = com.github.fit51.reactiveconfig.etcd.gen.rpc.DeleteRangeResponse
}

object DeleteRangeResponse extends scalapb.GeneratedMessageCompanion[com.github.fit51.reactiveconfig.etcd.gen.rpc.DeleteRangeResponse] {
  implicit def messageCompanion: scalapb.GeneratedMessageCompanion[com.github.fit51.reactiveconfig.etcd.gen.rpc.DeleteRangeResponse] = this
  def fromFieldsMap(__fieldsMap: scala.collection.immutable.Map[_root_.com.google.protobuf.Descriptors.FieldDescriptor, _root_.scala.Any]): com.github.fit51.reactiveconfig.etcd.gen.rpc.DeleteRangeResponse = {
    _root_.scala.Predef.require(__fieldsMap.keys.forall(_.getContainingType() == javaDescriptor), "FieldDescriptor does not match message type.")
    val __fields = javaDescriptor.getFields
    com.github.fit51.reactiveconfig.etcd.gen.rpc.DeleteRangeResponse(
      __fieldsMap.get(__fields.get(0)).asInstanceOf[_root_.scala.Option[com.github.fit51.reactiveconfig.etcd.gen.rpc.ResponseHeader]],
      __fieldsMap.getOrElse(__fields.get(1), 0L).asInstanceOf[_root_.scala.Long],
      __fieldsMap.getOrElse(__fields.get(2), Nil).asInstanceOf[_root_.scala.Seq[com.github.fit51.reactiveconfig.etcd.gen.kv.KeyValue]]
    )
  }
  implicit def messageReads: _root_.scalapb.descriptors.Reads[com.github.fit51.reactiveconfig.etcd.gen.rpc.DeleteRangeResponse] = _root_.scalapb.descriptors.Reads{
    case _root_.scalapb.descriptors.PMessage(__fieldsMap) =>
      _root_.scala.Predef.require(__fieldsMap.keys.forall(_.containingMessage == scalaDescriptor), "FieldDescriptor does not match message type.")
      com.github.fit51.reactiveconfig.etcd.gen.rpc.DeleteRangeResponse(
        __fieldsMap.get(scalaDescriptor.findFieldByNumber(1).get).flatMap(_.as[_root_.scala.Option[com.github.fit51.reactiveconfig.etcd.gen.rpc.ResponseHeader]]),
        __fieldsMap.get(scalaDescriptor.findFieldByNumber(2).get).map(_.as[_root_.scala.Long]).getOrElse(0L),
        __fieldsMap.get(scalaDescriptor.findFieldByNumber(3).get).map(_.as[_root_.scala.Seq[com.github.fit51.reactiveconfig.etcd.gen.kv.KeyValue]]).getOrElse(_root_.scala.Seq.empty)
      )
    case _ => throw new RuntimeException("Expected PMessage")
  }
  def javaDescriptor: _root_.com.google.protobuf.Descriptors.Descriptor = RpcProto.javaDescriptor.getMessageTypes.get(6)
  def scalaDescriptor: _root_.scalapb.descriptors.Descriptor = RpcProto.scalaDescriptor.messages(6)
  def messageCompanionForFieldNumber(__number: _root_.scala.Int): _root_.scalapb.GeneratedMessageCompanion[_] = {
    var __out: _root_.scalapb.GeneratedMessageCompanion[_] = null
    (__number: @_root_.scala.unchecked) match {
      case 1 => __out = com.github.fit51.reactiveconfig.etcd.gen.rpc.ResponseHeader
      case 3 => __out = com.github.fit51.reactiveconfig.etcd.gen.kv.KeyValue
    }
    __out
  }
  lazy val nestedMessagesCompanions: Seq[_root_.scalapb.GeneratedMessageCompanion[_ <: _root_.scalapb.GeneratedMessage]] = Seq.empty
  def enumCompanionForFieldNumber(__fieldNumber: _root_.scala.Int): _root_.scalapb.GeneratedEnumCompanion[_] = throw new MatchError(__fieldNumber)
  lazy val defaultInstance = com.github.fit51.reactiveconfig.etcd.gen.rpc.DeleteRangeResponse(
    header = _root_.scala.None,
    deleted = 0L,
    prevKvs = _root_.scala.Seq.empty
  )
  implicit class DeleteRangeResponseLens[UpperPB](_l: _root_.scalapb.lenses.Lens[UpperPB, com.github.fit51.reactiveconfig.etcd.gen.rpc.DeleteRangeResponse]) extends _root_.scalapb.lenses.ObjectLens[UpperPB, com.github.fit51.reactiveconfig.etcd.gen.rpc.DeleteRangeResponse](_l) {
    def header: _root_.scalapb.lenses.Lens[UpperPB, com.github.fit51.reactiveconfig.etcd.gen.rpc.ResponseHeader] = field(_.getHeader)((c_, f_) => c_.copy(header = Option(f_)))
    def optionalHeader: _root_.scalapb.lenses.Lens[UpperPB, _root_.scala.Option[com.github.fit51.reactiveconfig.etcd.gen.rpc.ResponseHeader]] = field(_.header)((c_, f_) => c_.copy(header = f_))
    def deleted: _root_.scalapb.lenses.Lens[UpperPB, _root_.scala.Long] = field(_.deleted)((c_, f_) => c_.copy(deleted = f_))
    def prevKvs: _root_.scalapb.lenses.Lens[UpperPB, _root_.scala.Seq[com.github.fit51.reactiveconfig.etcd.gen.kv.KeyValue]] = field(_.prevKvs)((c_, f_) => c_.copy(prevKvs = f_))
  }
  final val HEADER_FIELD_NUMBER = 1
  final val DELETED_FIELD_NUMBER = 2
  final val PREV_KVS_FIELD_NUMBER = 3
  def of(
    header: _root_.scala.Option[com.github.fit51.reactiveconfig.etcd.gen.rpc.ResponseHeader],
    deleted: _root_.scala.Long,
    prevKvs: _root_.scala.Seq[com.github.fit51.reactiveconfig.etcd.gen.kv.KeyValue]
  ): _root_.com.github.fit51.reactiveconfig.etcd.gen.rpc.DeleteRangeResponse = _root_.com.github.fit51.reactiveconfig.etcd.gen.rpc.DeleteRangeResponse(
    header,
    deleted,
    prevKvs
  )
}