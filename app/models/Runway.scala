package models

import play.api.libs.json.Json

case class Runway(csvId: Long,
             airport_ref: Long,
             airport_ident: String,
             length_ft: Double,
             width_ft: Double,
             surface: String,
             lighted: Boolean,
             closed: Boolean,
             le_ident: String,
             le_latitude_deg: Float,
             le_longitude_deg: Float,
             le_elevation_ft: Double,
             le_heading_degT: Float,
             le_displaced_threshold_ft: String,
             he_ident: String,
             he_latitude_deg: String,
             he_longitude_deg: String,
             he_elevation_ft: String,
             he_heading_degT: String,
             he_displaced_threshold_ft: String) {



  object Runway{
    implicit val json = Json.format[Runway]
  }


}
