package database

import javax.inject.Inject

import database.persintence.DB
import models.Runway

class RunwayService @Inject() extends DatabaseOperations {
  override def queryAll(): Stream[Any] = {
    DB.query[Runway].fetch()
  }

  override def find(id: Long): Any = {
    DB.fetchById[Runway](id)
  }

  def findByAirPortReference(reference: Long): Stream[Runway] = {
    DB.query[Runway].whereContains("airport_ref", reference).fetch()
  }

}
