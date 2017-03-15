package tool

import com.fasterxml.jackson.databind.{DeserializationFeature, ObjectMapper}
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import com.fasterxml.jackson.module.scala.experimental.ScalaObjectMapper
import play.api.libs.json.{Json, JsValue}

/**
  * Created by loui.wang on 2017/3/9.
  */
object JsonUtil {
  val mapper = new ObjectMapper() with ScalaObjectMapper
  mapper.registerModule(DefaultScalaModule)
  mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

  def toJson(value: Any): String = {
    mapper.writeValueAsString(value)
  }

  def getMapFromJsonString(jsonString: String) =
    mapper.readValue[Map[String, Any]](jsonString)

  def getSetMapFromJsonString(jsonString: String) =
    mapper.readValue[Set[Map[String, Any]]](jsonString)

  def fromJson[T](json: String)(implicit m: Manifest[T]): T = {
    mapper.readValue[T](json)
  }

  def toJsValue(jsonString: Any): JsValue = {
    Json.parse(toJson(jsonString))
  }
}
