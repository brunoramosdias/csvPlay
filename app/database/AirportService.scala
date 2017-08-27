package database

import javax.inject.Inject

import database.persintence.DB
import models.Airport

class AirportService @Inject() extends DatabaseOperations {
  override def queryAll(): Stream[Any] = {
    DB.query[Airport].fetch()
  }

  override def find(id: Long): Any = {
    DB.fetchById[Airport](id)
  }

  def findByCounrtyCode(code: String): Stream[Airport] = {
    DB.query[Airport].whereContains("iso_country", code).fetch()
  }

}
