import com.typesafe.sbt.packager.docker.{Cmd, DockerPlugin}
import sbt.dsl._
import sbt.Resolver

organization := "com.github.splee"
version := "0.2-SNAPSHOT"
description := "Monitors consumer group offset lag in Burrow using InfluxDB"
scalaVersion := "2.11.11"
scalacOptions := Seq("-unchecked", "-feature", "-deprecation", "-encoding", "utf8")

resolvers ++= Seq(
  "Spray Repository" at "http://repo.spray.cc/"
)

enablePlugins(JavaAppPackaging)
enablePlugins(DockerPlugin)

val playJsonVer = "2.5.2"

val playJson = "com.typesafe.play" %% "play-json" % playJsonVer
val scalaLogging = "com.typesafe.scala-logging" %% "scala-logging" % "3.1.0"
val influxDb = "com.paulgoldbaum" %% "scala-influxdb-client" % "0.5.2"
val scalaJ = "org.scalaj" %% "scalaj-http" % "2.3.0"
val logbackClassic = "ch.qos.logback" % "logback-classic" % "1.1.7"

libraryDependencies ++= Seq(
  playJson,
  scalaLogging,
  logbackClassic,
  scalaJ,
  influxDb
)

dockerBaseImage := "openjdk:8u131"
//dockerCommands ++= Seq(
//  // ensure container has bash for debugging
//  Cmd("USER", "root"),
//  Cmd("USER", "daemon"),
////  Cmd("RUN", "chmod", "+x", "/opt/docker/start.sh")
//)

//
//// sbt-assembly settings for building a fat jar
//import sbtassembly.Plugin._
//import AssemblyKeys._
//lazy val sbtAssemblySettings = assemblySettings ++ Seq(
//
//  // Slightly cleaner jar name
//  jarName in assembly := {
//    name.value + "-" + version.value + ".jar"
//  },
//
//  // Drop these jars
//  excludedJars in assembly <<= (fullClasspath in assembly) map { cp =>
//    val excludes = Set(
//      "jsp-api-2.1-6.1.14.jar",
//      "jsp-2.1-6.1.14.jar",
//      "jasper-compiler-5.5.12.jar",
//      "commons-beanutils-core-1.8.0.jar",
//      "commons-beanutils-1.7.0.jar",
//      "servlet-api-2.5-20081211.jar",
//      "servlet-api-2.5.jar"
//    )
//    cp filter { jar => excludes(jar.data.getName) }
//  },
//
//  mergeStrategy in assembly <<= (mergeStrategy in assembly) {
//    (old) => {
//      // case "project.clj" => MergeStrategy.discard // Leiningen build files
//      case x if x.startsWith("META-INF") => MergeStrategy.discard // Bumf
//      case x if x.endsWith(".html") => MergeStrategy.discard // More bumf
//      case x if x.endsWith("UnusedStubClass.class") => MergeStrategy.first // really?
//      case PathList("com", "esotericsoftware", xs @ _*) => MergeStrategy.last // For Log$Logger.class
//      case x if x.endsWith("project.clj") => MergeStrategy.discard // throw it away.
//      case x => old(x)
//    }
//  }
//)

// Leave this here for later so we can add sbtAssemblySettings if we want.
//lazy val buildSettings = basicSettings ++ sbtAssemblySettings
