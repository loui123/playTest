package controllers

import play.api.mvc._
import play.api.libs.json.Json._

object DataController extends Controller {

  def add = Action {
    Ok(toJson(Map("action" -> "add")))
  }

  def remove = Action {
    Ok(toJson(Map("action" -> "remove")))
  }

  def update = Action {
    Ok(toJson(Map("action" -> "update")))
  }

  def get = Action {
    Ok(toJson(Map("action" -> "get")))
  }
}
