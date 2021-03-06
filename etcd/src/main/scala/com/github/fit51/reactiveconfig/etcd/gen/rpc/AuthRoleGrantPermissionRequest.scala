// Generated by the Scala Plugin for the Protocol Buffer Compiler.
// Do not edit!
//
// Protofile syntax: PROTO3

package com.github.fit51.reactiveconfig.etcd.gen.rpc

/** @param name
  *   name is the name of the role which will be granted the permission.
  * @param perm
  *   perm is the permission to grant to the role.
  */
@SerialVersionUID(0L)
final case class AuthRoleGrantPermissionRequest(
    name: _root_.scala.Predef.String = "",
    perm: _root_.scala.Option[com.github.fit51.reactiveconfig.etcd.gen.auth.Permission] = _root_.scala.None
    ) extends scalapb.GeneratedMessage with scalapb.Message[AuthRoleGrantPermissionRequest] with scalapb.lenses.Updatable[AuthRoleGrantPermissionRequest] {
    @transient
    private[this] var __serializedSizeCachedValue: _root_.scala.Int = 0
    private[this] def __computeSerializedValue(): _root_.scala.Int = {
      var __size = 0
      
      {
        val __value = name
        if (__value != "") {
          __size += _root_.com.google.protobuf.CodedOutputStream.computeStringSize(1, __value)
        }
      };
      if (perm.isDefined) {
        val __value = perm.get
        __size += 1 + _root_.com.google.protobuf.CodedOutputStream.computeUInt32SizeNoTag(__value.serializedSize) + __value.serializedSize
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
        val __v = name
        if (__v != "") {
          _output__.writeString(1, __v)
        }
      };
      perm.foreach { __v =>
        val __m = __v
        _output__.writeTag(2, 2)
        _output__.writeUInt32NoTag(__m.serializedSize)
        __m.writeTo(_output__)
      };
    }
    def mergeFrom(`_input__`: _root_.com.google.protobuf.CodedInputStream): com.github.fit51.reactiveconfig.etcd.gen.rpc.AuthRoleGrantPermissionRequest = {
      var __name = this.name
      var __perm = this.perm
      var _done__ = false
      while (!_done__) {
        val _tag__ = _input__.readTag()
        _tag__ match {
          case 0 => _done__ = true
          case 10 =>
            __name = _input__.readString()
          case 18 =>
            __perm = Option(_root_.scalapb.LiteParser.readMessage(_input__, __perm.getOrElse(com.github.fit51.reactiveconfig.etcd.gen.auth.Permission.defaultInstance)))
          case tag => _input__.skipField(tag)
        }
      }
      com.github.fit51.reactiveconfig.etcd.gen.rpc.AuthRoleGrantPermissionRequest(
          name = __name,
          perm = __perm
      )
    }
    def withName(__v: _root_.scala.Predef.String): AuthRoleGrantPermissionRequest = copy(name = __v)
    def getPerm: com.github.fit51.reactiveconfig.etcd.gen.auth.Permission = perm.getOrElse(com.github.fit51.reactiveconfig.etcd.gen.auth.Permission.defaultInstance)
    def clearPerm: AuthRoleGrantPermissionRequest = copy(perm = _root_.scala.None)
    def withPerm(__v: com.github.fit51.reactiveconfig.etcd.gen.auth.Permission): AuthRoleGrantPermissionRequest = copy(perm = Option(__v))
    def getFieldByNumber(__fieldNumber: _root_.scala.Int): _root_.scala.Any = {
      (__fieldNumber: @_root_.scala.unchecked) match {
        case 1 => {
          val __t = name
          if (__t != "") __t else null
        }
        case 2 => perm.orNull
      }
    }
    def getField(__field: _root_.scalapb.descriptors.FieldDescriptor): _root_.scalapb.descriptors.PValue = {
      _root_.scala.Predef.require(__field.containingMessage eq companion.scalaDescriptor)
      (__field.number: @_root_.scala.unchecked) match {
        case 1 => _root_.scalapb.descriptors.PString(name)
        case 2 => perm.map(_.toPMessage).getOrElse(_root_.scalapb.descriptors.PEmpty)
      }
    }
    def toProtoString: _root_.scala.Predef.String = _root_.scalapb.TextFormat.printToUnicodeString(this)
    def companion = com.github.fit51.reactiveconfig.etcd.gen.rpc.AuthRoleGrantPermissionRequest
}

object AuthRoleGrantPermissionRequest extends scalapb.GeneratedMessageCompanion[com.github.fit51.reactiveconfig.etcd.gen.rpc.AuthRoleGrantPermissionRequest] {
  implicit def messageCompanion: scalapb.GeneratedMessageCompanion[com.github.fit51.reactiveconfig.etcd.gen.rpc.AuthRoleGrantPermissionRequest] = this
  def fromFieldsMap(__fieldsMap: scala.collection.immutable.Map[_root_.com.google.protobuf.Descriptors.FieldDescriptor, _root_.scala.Any]): com.github.fit51.reactiveconfig.etcd.gen.rpc.AuthRoleGrantPermissionRequest = {
    _root_.scala.Predef.require(__fieldsMap.keys.forall(_.getContainingType() == javaDescriptor), "FieldDescriptor does not match message type.")
    val __fields = javaDescriptor.getFields
    com.github.fit51.reactiveconfig.etcd.gen.rpc.AuthRoleGrantPermissionRequest(
      __fieldsMap.getOrElse(__fields.get(0), "").asInstanceOf[_root_.scala.Predef.String],
      __fieldsMap.get(__fields.get(1)).asInstanceOf[_root_.scala.Option[com.github.fit51.reactiveconfig.etcd.gen.auth.Permission]]
    )
  }
  implicit def messageReads: _root_.scalapb.descriptors.Reads[com.github.fit51.reactiveconfig.etcd.gen.rpc.AuthRoleGrantPermissionRequest] = _root_.scalapb.descriptors.Reads{
    case _root_.scalapb.descriptors.PMessage(__fieldsMap) =>
      _root_.scala.Predef.require(__fieldsMap.keys.forall(_.containingMessage == scalaDescriptor), "FieldDescriptor does not match message type.")
      com.github.fit51.reactiveconfig.etcd.gen.rpc.AuthRoleGrantPermissionRequest(
        __fieldsMap.get(scalaDescriptor.findFieldByNumber(1).get).map(_.as[_root_.scala.Predef.String]).getOrElse(""),
        __fieldsMap.get(scalaDescriptor.findFieldByNumber(2).get).flatMap(_.as[_root_.scala.Option[com.github.fit51.reactiveconfig.etcd.gen.auth.Permission]])
      )
    case _ => throw new RuntimeException("Expected PMessage")
  }
  def javaDescriptor: _root_.com.google.protobuf.Descriptors.Descriptor = RpcProto.javaDescriptor.getMessageTypes.get(60)
  def scalaDescriptor: _root_.scalapb.descriptors.Descriptor = RpcProto.scalaDescriptor.messages(60)
  def messageCompanionForFieldNumber(__number: _root_.scala.Int): _root_.scalapb.GeneratedMessageCompanion[_] = {
    var __out: _root_.scalapb.GeneratedMessageCompanion[_] = null
    (__number: @_root_.scala.unchecked) match {
      case 2 => __out = com.github.fit51.reactiveconfig.etcd.gen.auth.Permission
    }
    __out
  }
  lazy val nestedMessagesCompanions: Seq[_root_.scalapb.GeneratedMessageCompanion[_ <: _root_.scalapb.GeneratedMessage]] = Seq.empty
  def enumCompanionForFieldNumber(__fieldNumber: _root_.scala.Int): _root_.scalapb.GeneratedEnumCompanion[_] = throw new MatchError(__fieldNumber)
  lazy val defaultInstance = com.github.fit51.reactiveconfig.etcd.gen.rpc.AuthRoleGrantPermissionRequest(
    name = "",
    perm = _root_.scala.None
  )
  implicit class AuthRoleGrantPermissionRequestLens[UpperPB](_l: _root_.scalapb.lenses.Lens[UpperPB, com.github.fit51.reactiveconfig.etcd.gen.rpc.AuthRoleGrantPermissionRequest]) extends _root_.scalapb.lenses.ObjectLens[UpperPB, com.github.fit51.reactiveconfig.etcd.gen.rpc.AuthRoleGrantPermissionRequest](_l) {
    def name: _root_.scalapb.lenses.Lens[UpperPB, _root_.scala.Predef.String] = field(_.name)((c_, f_) => c_.copy(name = f_))
    def perm: _root_.scalapb.lenses.Lens[UpperPB, com.github.fit51.reactiveconfig.etcd.gen.auth.Permission] = field(_.getPerm)((c_, f_) => c_.copy(perm = Option(f_)))
    def optionalPerm: _root_.scalapb.lenses.Lens[UpperPB, _root_.scala.Option[com.github.fit51.reactiveconfig.etcd.gen.auth.Permission]] = field(_.perm)((c_, f_) => c_.copy(perm = f_))
  }
  final val NAME_FIELD_NUMBER = 1
  final val PERM_FIELD_NUMBER = 2
  def of(
    name: _root_.scala.Predef.String,
    perm: _root_.scala.Option[com.github.fit51.reactiveconfig.etcd.gen.auth.Permission]
  ): _root_.com.github.fit51.reactiveconfig.etcd.gen.rpc.AuthRoleGrantPermissionRequest = _root_.com.github.fit51.reactiveconfig.etcd.gen.rpc.AuthRoleGrantPermissionRequest(
    name,
    perm
  )
}
