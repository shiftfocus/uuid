package ca.shiftfocus.uuid

import java.nio.ByteBuffer
import play.api.libs.json._
import play.api.libs.json.Writes._
import play.api.libs.functional.syntax._

case class UUID (
  uuid: java.util.UUID
) {
  def bytes: Array[Byte] = UUID.uuidToBytes(uuid)
  def string: String = uuid.toString()
  def cleanString: String = {
    val idString = uuid.toString()
    val cleanIdString =
      idString.slice(0, 8) +
      idString.slice(9, 13) +
      idString.slice(14, 18) +
      idString.slice(19, 23) +
      idString.slice(24, 36)
    cleanIdString
  }
  override def equals(o: Any) = o match {
    case that: UUID => uuid.equals(that.uuid)
    case _ => false
  }
}

object UUID {

  implicit val uuidReads : Reads[UUID] = new Reads[UUID] {
    def reads(json: JsValue) = json match {
      case JsString(s) => JsSuccess(UUID(s))
      case _ => JsError("String value expected.")
    }
  }

  implicit val uuidWrites : Writes[UUID] = new Writes[UUID] {
    def writes(uuid: UUID): JsValue = {
      JsString(uuid.string)
    }
  }

  def apply(uuid: String)       = new UUID(java.util.UUID.fromString(uuid))
  def apply(bytes: Array[Byte]) = new UUID(bytesToUuid(bytes))

  def random = new UUID(java.util.UUID.randomUUID())

  def isValid(proposed: String): Boolean = {
    val pattern = """^[0-9A-Fa-f]{8}\-[0-9A-Fa-f]{4}\-[0-9A-Fa-f]{4}\-[0-9A-Fa-f]{4}\-[0-9A-Fa-f]{12}$"""
    if (proposed.matches(pattern)) {
      true
    }
    else {
      false
    }
  }

  private def uuidToBytes(uuid: java.util.UUID): Array[Byte] = {
    var bb = ByteBuffer.allocate(16)
    bb.putLong(uuid.getMostSignificantBits())
    bb.putLong(uuid.getLeastSignificantBits())
    bb.array()
  }

  private def uuidToBytes(uuidString: String): Array[Byte] = {
    var uuid = java.util.UUID.fromString(uuidString)
    var bb = ByteBuffer.allocate(16)
    bb.putLong(uuid.getMostSignificantBits())
    bb.putLong(uuid.getLeastSignificantBits())
    bb.array()
  }

  private def bytesToUuid(bytes: Array[Byte]): java.util.UUID = {
    var bb = ByteBuffer.wrap(bytes)
    new java.util.UUID(bb.getLong(), bb.getLong())
  }
}
