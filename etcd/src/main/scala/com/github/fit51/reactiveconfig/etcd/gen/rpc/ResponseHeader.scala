// Generated by the Scala Plugin for the Protocol Buffer Compiler.
// Do not edit!
//
// Protofile syntax: PROTO3

package com.github.fit51.reactiveconfig.etcd.gen.rpc

/** @param clusterId
  *   cluster_id is the ID of the cluster which sent the response.
  * @param memberId
  *   member_id is the ID of the member which sent the response.
  * @param revision
  *   revision is the key-value store revision when the request was applied.
  * @param raftTerm
  *   raft_term is the raft term when the request was applied.
  */
@SerialVersionUID(0L)
final case class ResponseHeader(
    clusterId: _root_.scala.Long = 0L,
    memberId: _root_.scala.Long = 0L,
    revision: _root_.scala.Long = 0L,
    raftTerm: _root_.scala.Long = 0L
    ) extends scalapb.GeneratedMessage with scalapb.Message[ResponseHeader] with scalapb.lenses.Updatable[ResponseHeader] {
    @transient
    private[this] var __serializedSizeCachedValue: _root_.scala.Int = 0
    private[this] def __computeSerializedValue(): _root_.scala.Int = {
      var __size = 0
      
      {
        val __value = clusterId
        if (__value != 0L) {
          __size += _root_.com.google.protobuf.CodedOutputStream.computeUInt64Size(1, __value)
        }
      };
      
      {
        val __value = memberId
        if (__value != 0L) {
          __size += _root_.com.google.protobuf.CodedOutputStream.computeUInt64Size(2, __value)
        }
      };
      
      {
        val __value = revision
        if (__value != 0L) {
          __size += _root_.com.google.protobuf.CodedOutputStream.computeInt64Size(3, __value)
        }
      };
      
      {
        val __value = raftTerm
        if (__value != 0L) {
          __size += _root_.com.google.protobuf.CodedOutputStream.computeUInt64Size(4, __value)
        }
      };
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
      {
        val __v = clusterId
        if (__v != 0L) {
          _output__.writeUInt64(1, __v)
        }
      };
      {
        val __v = memberId
        if (__v != 0L) {
          _output__.writeUInt64(2, __v)
        }
      };
      {
        val __v = revision
        if (__v != 0L) {
          _output__.writeInt64(3, __v)
        }
      };
      {
        val __v = raftTerm
        if (__v != 0L) {
          _output__.writeUInt64(4, __v)
        }
      };
    }
    def mergeFrom(`_input__`: _root_.com.google.protobuf.CodedInputStream): com.github.fit51.reactiveconfig.etcd.gen.rpc.ResponseHeader = {
      var __clusterId = this.clusterId
      var __memberId = this.memberId
      var __revision = this.revision
      var __raftTerm = this.raftTerm
      var _done__ = false
      while (!_done__) {
        val _tag__ = _input__.readTag()
        _tag__ match {
          case 0 => _done__ = true
          case 8 =>
            __clusterId = _input__.readUInt64()
          case 16 =>
            __memberId = _input__.readUInt64()
          case 24 =>
            __revision = _input__.readInt64()
          case 32 =>
            __raftTerm = _input__.readUInt64()
          case tag => _input__.skipField(tag)
        }
      }
      com.github.fit51.reactiveconfig.etcd.gen.rpc.ResponseHeader(
          clusterId = __clusterId,
          memberId = __memberId,
          revision = __revision,
          raftTerm = __raftTerm
      )
    }
    def withClusterId(__v: _root_.scala.Long): ResponseHeader = copy(clusterId = __v)
    def withMemberId(__v: _root_.scala.Long): ResponseHeader = copy(memberId = __v)
    def withRevision(__v: _root_.scala.Long): ResponseHeader = copy(revision = __v)
    def withRaftTerm(__v: _root_.scala.Long): ResponseHeader = copy(raftTerm = __v)
    def getFieldByNumber(__fieldNumber: _root_.scala.Int): _root_.scala.Any = {
      (__fieldNumber: @_root_.scala.unchecked) match {
        case 1 => {
          val __t = clusterId
          if (__t != 0L) __t else null
        }
        case 2 => {
          val __t = memberId
          if (__t != 0L) __t else null
        }
        case 3 => {
          val __t = revision
          if (__t != 0L) __t else null
        }
        case 4 => {
          val __t = raftTerm
          if (__t != 0L) __t else null
        }
      }
    }
    def getField(__field: _root_.scalapb.descriptors.FieldDescriptor): _root_.scalapb.descriptors.PValue = {
      _root_.scala.Predef.require(__field.containingMessage eq companion.scalaDescriptor)
      (__field.number: @_root_.scala.unchecked) match {
        case 1 => _root_.scalapb.descriptors.PLong(clusterId)
        case 2 => _root_.scalapb.descriptors.PLong(memberId)
        case 3 => _root_.scalapb.descriptors.PLong(revision)
        case 4 => _root_.scalapb.descriptors.PLong(raftTerm)
      }
    }
    def toProtoString: _root_.scala.Predef.String = _root_.scalapb.TextFormat.printToUnicodeString(this)
    def companion = com.github.fit51.reactiveconfig.etcd.gen.rpc.ResponseHeader
}

object ResponseHeader extends scalapb.GeneratedMessageCompanion[com.github.fit51.reactiveconfig.etcd.gen.rpc.ResponseHeader] {
  implicit def messageCompanion: scalapb.GeneratedMessageCompanion[com.github.fit51.reactiveconfig.etcd.gen.rpc.ResponseHeader] = this
  def fromFieldsMap(__fieldsMap: scala.collection.immutable.Map[_root_.com.google.protobuf.Descriptors.FieldDescriptor, _root_.scala.Any]): com.github.fit51.reactiveconfig.etcd.gen.rpc.ResponseHeader = {
    _root_.scala.Predef.require(__fieldsMap.keys.forall(_.getContainingType() == javaDescriptor), "FieldDescriptor does not match message type.")
    val __fields = javaDescriptor.getFields
    com.github.fit51.reactiveconfig.etcd.gen.rpc.ResponseHeader(
      __fieldsMap.getOrElse(__fields.get(0), 0L).asInstanceOf[_root_.scala.Long],
      __fieldsMap.getOrElse(__fields.get(1), 0L).asInstanceOf[_root_.scala.Long],
      __fieldsMap.getOrElse(__fields.get(2), 0L).asInstanceOf[_root_.scala.Long],
      __fieldsMap.getOrElse(__fields.get(3), 0L).asInstanceOf[_root_.scala.Long]
    )
  }
  implicit def messageReads: _root_.scalapb.descriptors.Reads[com.github.fit51.reactiveconfig.etcd.gen.rpc.ResponseHeader] = _root_.scalapb.descriptors.Reads{
    case _root_.scalapb.descriptors.PMessage(__fieldsMap) =>
      _root_.scala.Predef.require(__fieldsMap.keys.forall(_.containingMessage == scalaDescriptor), "FieldDescriptor does not match message type.")
      com.github.fit51.reactiveconfig.etcd.gen.rpc.ResponseHeader(
        __fieldsMap.get(scalaDescriptor.findFieldByNumber(1).get).map(_.as[_root_.scala.Long]).getOrElse(0L),
        __fieldsMap.get(scalaDescriptor.findFieldByNumber(2).get).map(_.as[_root_.scala.Long]).getOrElse(0L),
        __fieldsMap.get(scalaDescriptor.findFieldByNumber(3).get).map(_.as[_root_.scala.Long]).getOrElse(0L),
        __fieldsMap.get(scalaDescriptor.findFieldByNumber(4).get).map(_.as[_root_.scala.Long]).getOrElse(0L)
      )
    case _ => throw new RuntimeException("Expected PMessage")
  }
  def javaDescriptor: _root_.com.google.protobuf.Descriptors.Descriptor = RpcProto.javaDescriptor.getMessageTypes.get(0)
  def scalaDescriptor: _root_.scalapb.descriptors.Descriptor = RpcProto.scalaDescriptor.messages(0)
  def messageCompanionForFieldNumber(__number: _root_.scala.Int): _root_.scalapb.GeneratedMessageCompanion[_] = throw new MatchError(__number)
  lazy val nestedMessagesCompanions: Seq[_root_.scalapb.GeneratedMessageCompanion[_ <: _root_.scalapb.GeneratedMessage]] = Seq.empty
  def enumCompanionForFieldNumber(__fieldNumber: _root_.scala.Int): _root_.scalapb.GeneratedEnumCompanion[_] = throw new MatchError(__fieldNumber)
  lazy val defaultInstance = com.github.fit51.reactiveconfig.etcd.gen.rpc.ResponseHeader(
    clusterId = 0L,
    memberId = 0L,
    revision = 0L,
    raftTerm = 0L
  )
  implicit class ResponseHeaderLens[UpperPB](_l: _root_.scalapb.lenses.Lens[UpperPB, com.github.fit51.reactiveconfig.etcd.gen.rpc.ResponseHeader]) extends _root_.scalapb.lenses.ObjectLens[UpperPB, com.github.fit51.reactiveconfig.etcd.gen.rpc.ResponseHeader](_l) {
    def clusterId: _root_.scalapb.lenses.Lens[UpperPB, _root_.scala.Long] = field(_.clusterId)((c_, f_) => c_.copy(clusterId = f_))
    def memberId: _root_.scalapb.lenses.Lens[UpperPB, _root_.scala.Long] = field(_.memberId)((c_, f_) => c_.copy(memberId = f_))
    def revision: _root_.scalapb.lenses.Lens[UpperPB, _root_.scala.Long] = field(_.revision)((c_, f_) => c_.copy(revision = f_))
    def raftTerm: _root_.scalapb.lenses.Lens[UpperPB, _root_.scala.Long] = field(_.raftTerm)((c_, f_) => c_.copy(raftTerm = f_))
  }
  final val CLUSTER_ID_FIELD_NUMBER = 1
  final val MEMBER_ID_FIELD_NUMBER = 2
  final val REVISION_FIELD_NUMBER = 3
  final val RAFT_TERM_FIELD_NUMBER = 4
  def of(
    clusterId: _root_.scala.Long,
    memberId: _root_.scala.Long,
    revision: _root_.scala.Long,
    raftTerm: _root_.scala.Long
  ): _root_.com.github.fit51.reactiveconfig.etcd.gen.rpc.ResponseHeader = _root_.com.github.fit51.reactiveconfig.etcd.gen.rpc.ResponseHeader(
    clusterId,
    memberId,
    revision,
    raftTerm
  )
}
