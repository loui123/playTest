package controllers.response

import model.Data
import tool.JsonUtil

/**
  * Created by loui.wang on 2017/3/15.
  */
object DataResponse {
  private def packDataMap(data: Data) = {
    Map("id" -> data.id.toString, "data" -> data.data)
  }

  private def packOptionDataMap(data: Option[Data]) = {
    if (data.isEmpty) None
    else packDataMap(data.get)
  }

  def packResponse(data: Data, action: String): String = {
    val dataMap = Map("action" -> action, "data" -> packDataMap(data))
    val responseMap: Map[String, Any] = Map("response" -> dataMap)
    JsonUtil.toJson(responseMap)
  }

  def packResponse(data: Option[Data], action: String): String = {
    val dataMap = Map("action" -> action, "data" -> packOptionDataMap(data))
    val responseMap: Map[String, Any] = Map("response" -> dataMap)
    JsonUtil.toJson(responseMap)
  }

  def packResponse(data: List[Data], action: String): String = {
    val dataList = data.map(tempData => packDataMap(tempData))
    val dataMap = Map("action" -> action, "data" -> dataList)
    val responseMap = Map("response" -> dataMap)
    JsonUtil.toJson(responseMap)
  }

  def packResponse(data: Any, action: String): String = {
    val dataMap: Map[String, Any] = Map("action" -> action, "data" -> data)
    val responseMap: Map[String, Any] = Map("response" -> dataMap)
    JsonUtil.toJson(responseMap)
  }
}
