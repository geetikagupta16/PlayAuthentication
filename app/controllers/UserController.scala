package controllers

import scala.concurrent.Future

import controllers.security.SecuredAction
import play.api.mvc.Controller

class UserController extends Controller {

  def userHome = {
    SecuredAction.async {
      implicit request =>
        Future.successful(Ok("Hello User !! " + request.userName))
    }
  }
}
