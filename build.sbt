name := "example"

version := "1.0"

lazy val `example` = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.7"

routesGenerator := InjectedRoutesGenerator

libraryDependencies ++= Seq(
  jdbc,
  cache,
  ws,
  specs2 % Test,
  "com.github.salat" % "salat_2.11" % "1.10.0",
  "org.mongodb" %% "casbah" % "2.8.2",
  "com.fasterxml.jackson.module" % "jackson-module-scala_2.11" % "2.4.2",
  "io.swagger" %% "swagger-play2" % "1.5.1"
)

resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"
