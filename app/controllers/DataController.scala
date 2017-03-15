package controllers

import java.util.UUID

import controllers.response.DataResponse
import io.swagger.annotations._
import model.{Data}
import play.api.data.Form
import play.api.data.Forms._
import play.api.mvc._

@Api(value = "data")
class DataController extends Controller {
  @ApiOperation(nickname = "新增", value = "新增Data", httpMethod = "POST")
  @ApiImplicitParams(
    Array(
      new ApiImplicitParam(name = "data",
                           defaultValue = "",
                           value = "資料",
                           required = true,
                           dataType = "String",
                           paramType = "form")))
  def add = Action { implicit request =>
    val parameterMap = Form("data" -> text).bindFromRequest.data
    val newData = Data.add(parameterMap.get("data"))
    Ok(DataResponse.packResponse(newData, "add"))
  }

  @ApiOperation(nickname = "刪除", value = "刪除Data", httpMethod = "DELETE")
  def remove(id: String) = Action {
    val data = Data.delete(id)
    Ok(DataResponse.packResponse(data, "remove"))
  }

  @ApiOperation(nickname = "更新", value = "更新Data", httpMethod = "POST")
  @ApiImplicitParams(
    Array(
      new ApiImplicitParam(name = "data",
                           defaultValue = "",
                           value = "資料",
                           required = true,
                           dataType = "String",
                           paramType = "form")))
  def update(id: String) = Action { implicit request =>
    val parameterMap = Form("data" -> text).bindFromRequest.data
    val oldData = Data.get(id).get
    val data = Data.update(oldData.copy(data = parameterMap.get("data")))
    Ok(DataResponse.packResponse(data, "update"))
  }

  @ApiOperation(nickname = "取得", value = "取得Data", httpMethod = "POST")
  def get = Action { implicit request =>
    val data = Data.getAll()
    Ok(DataResponse.packResponse(data, "get"))
  }
}
