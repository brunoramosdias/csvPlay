package database

import javax.inject.Inject

import database.persintence.DB
import models.Country

class CountryService @Inject() extends DatabaseOperations {
  override def queryAll(): Stream[Any] = {
    DB.query[Country].fetch()
  }

  override def find(id: Long): Any = {
    DB.fetchById[Country](id)
  }


}
