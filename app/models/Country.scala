package models

import play.api.libs.json.Json

case class Country(csvId: Long,
                   code: String,
                   name: String,
                   continent: String,
                   wikipedia_link: String,
                   keywords: String){

object Country {

  implicit val json = Json.format[Country]
}

}
