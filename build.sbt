ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.10"

lazy val root = (project in file(".")).
  enablePlugins(ParadoxPlugin).
  settings(
    name := "http4s-overview",
    paradoxTheme := Some(builtinParadoxTheme("generic"))
  )

libraryDependencies ++= Seq(
  "org.typelevel" %% "cats-effect" % "3.4.4",
  "co.fs2" %% "fs2-core" % "3.4.0",
  "co.fs2" %% "fs2-io" % "3.4.0"
)
