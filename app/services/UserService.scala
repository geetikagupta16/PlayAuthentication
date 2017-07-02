package services

import models.User


trait UserService {

  def getAllUsers(): List[User] = {
    List(User("Jake"), User("Alex"), User("Ryan"), User("Nicholas"))
  }
}

object UserService extends UserService