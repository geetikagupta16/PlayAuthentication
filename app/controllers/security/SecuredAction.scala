package controllers.security

import scala.concurrent.Future

import models.User
import play.api.mvc.{ActionBuilder, Request, Result, Results, WrappedRequest}
import services.UserService

case class UserRequest[A](val userName: String, val request: Request[A])
  extends WrappedRequest[A](request)

object SecuredAction extends ActionBuilder[UserRequest] {

  override def invokeBlock[A](request: Request[A],
      block: (UserRequest[A]) => Future[Result]): Future[Result] = {
    val userName = request.headers.get("username").fold("")(identity)
    if (UserService.getAllUsers().contains(User(userName))) {
      block(UserRequest(userName, request))
    } else {
      Future.successful(Results.Unauthorized("Unauthorized access !!"))
    }
  }
}
