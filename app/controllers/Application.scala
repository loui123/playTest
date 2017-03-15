package controllers

import play.api.mvc._

class Application extends Controller {

  def index = Action {
    Ok(views.html.index("Hello world"))
  }

  def swagger = Action { request =>
    Ok(views.html.swagger())
  }
}
