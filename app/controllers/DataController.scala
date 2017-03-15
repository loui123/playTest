package controllers

import java.util.UUID

import controllers.response.DataResponse
import io.swagger.annotations.Api
import model.{Data}
import play.api.mvc._

@Api(value = "data",
     produces = "application/json",
     consumes = "application/json")
class DataController extends Controller {
  def add = Action {
    val data = Data.add(UUID.randomUUID().toString)
    Ok(DataResponse.packResponse(data, "add"))
  }

  def remove(id: String) = Action {
    val data = Data.delete(id)
    Ok(DataResponse.packResponse(data, "remove"))
  }

  def update(id: String) = Action {
    val oldData = Data.get(id).get
    val data = Data.update(oldData.copy(data = UUID.randomUUID().toString))
    Ok(DataResponse.packResponse(data, "update"))
  }

  def get = Action { implicit request =>
    val data = Data.getAll()
    Ok(DataResponse.packResponse(data, "get"))
  }
}
