package services

import javax.inject._

import com.github.tototoshi.csv.CSVReader
import models.persintence.DB
import models.{Airport, Country, Runway}
import play.api.Environment
import play.api.inject.ApplicationLifecycle

trait Load{
  def start() : Unit
}


@Singleton
class LoadDataFromCSVToDB  @Inject() (appLifecycle: ApplicationLifecycle) extends Load{


  private val environment = Environment.simple()

  def start(): Unit = {
    val countryFile = environment.getFile("/resources/countries.csv")
    val reader = CSVReader.open(countryFile)
    var lines = reader.all();
    lines = lines.slice(1,lines.size -1)
    val countries = lines.map(line => {
       new Country(line(0).toLong,
                        line(1),
                        line(2),
                        line(3),
                        line(4),
                        line(5))
    }).map(country => {
      DB.save(country)
    })
    reader.close()

    val airportFiles = environment.getFile("/resources/airports.csv")
    val aiportReader = CSVReader.open(airportFiles)
    lines = aiportReader.all()
    lines = lines.slice(1,lines.size -1)
    val aiports = lines.map(line => {
      val latitude  : Float = if (line(4) != null && line(4).length > 0) line(4).toFloat else  null.asInstanceOf[Float]
      val longitude : Float = if (line(5) != null && line(5).length > 0) line(5).toFloat else  null.asInstanceOf[Float]
      val elevation : Float = if (line(6) != null && line(6).length > 0) line(6).toFloat else  null.asInstanceOf[Float]
      new Airport(line(0).toLong,
        line(1),
        line(2),
        line(3),
        latitude,
        longitude,
        elevation,
        line(7),
        line(8),
        line(9),
        line(10),
        line(11),
        line(12),
        line(13),
        line(14),
        line(15),
        line(16),
        line(17))
    }).map(aiport => DB.save(aiport))
    val runwayFiles = environment.getFile("/resources/runways.csv")
    val runwayReader = CSVReader.open(runwayFiles)
    lines = runwayReader.all()
    lines = lines.slice(1,lines.size -1)
    val runways  = lines.map(line => {
      val length_ft   : Double = if (line(3) != null && line(3).length > 0) line(3).toFloat else  null.asInstanceOf[Double]
      val width_ft   : Double = if (line(4) != null && line(4).length > 0) line(4).toFloat else  null.asInstanceOf[Double]
      val le_latitude_deg   : Float = if (line(10) != null && line(10).length > 0) line(10).toFloat else  null.asInstanceOf[Float]
      val le_longitude_deg  : Float = if (line(11) != null && line(11).length > 0) line(11).toFloat else  null.asInstanceOf[Float]
      val le_elevation_ft   : Double = if (line(12) != null && line(12).length > 0) line(12).toFloat else  null.asInstanceOf[Double]
      val le_heading_degT   : Float = if (line(13) != null && line(13).length > 0) line(13).toFloat else  null.asInstanceOf[Float]
      val he_displaced_threshold_ft : String  = if (line.length > 20) line(20) else ""
      val lighted : Boolean = if (line(7).nonEmpty) line(7).map(char => {char.toInt > 0}).toList.head else false
      val closed  : Boolean = if(line(8).nonEmpty) line(8).map(char => {char.toInt > 0}).toList.head else false
      new Runway(line(0).toLong,
                line(1).toLong,
                line(2),
                length_ft,
                width_ft,
                line(5),
                lighted,
                closed,
                line(9),
                le_latitude_deg,
                le_longitude_deg,
                le_elevation_ft,
                le_heading_degT ,
                line(14),
                line(15),
                line(16),
                line(17),
                line(18),
                line(19),
        he_displaced_threshold_ft)
    }).map(runway => DB.save(runway))
  }

  start()
}
