package database.persintence

import models.{Airport, Country, Runway}
import sorm._

object DB extends Instance(entities = Seq(Entity[Country](),Entity[Airport](),Entity[Runway]()),
  url = "jdbc:postgresql://localhost:5432/csv",user="brunoramosdias",password = "",initMode = InitMode.DropAllCreate) {

}
