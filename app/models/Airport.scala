package models

import play.api.libs.json.Json

case class Airport(csvId: Long,
              ident: String,
              typeOfairport: String,
              name: String,
              latitude_deg: Float,
              longitude_deg: Float,
              elevation_ft: Float,
              continent: String,
              iso_country: String,
              iso_region: String,
              municipality: String,
              scheduled_service: String,
              gps_code: String,
              iata_code: String,
              local_code: String,
              home_link: String,
              wikipedia_link:
              String,
              keywords: String){

  object Airport {

    implicit val json = Json.format[Airport]
  }

}
