name := """csvPlay"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.8"

import com.typesafe.sbt.packager.MappingsHelper._
mappings in Universal ++= directory(baseDirectory.value / "resources")

libraryDependencies ++= Seq(
  jdbc,
  cache,
  ws,
  "org.sorm-framework" % "sorm" % "0.3.18",
  "com.github.tototoshi" %% "scala-csv" % "1.3.4",
  "org.postgresql" % "postgresql" % "9.4-1200-jdbc41",
  "org.scalatestplus.play" %% "scalatestplus-play" % "1.5.1" % Test
)

dependencyOverrides += "org.scala-lang" % "scala-compiler" % scalaVersion.value

