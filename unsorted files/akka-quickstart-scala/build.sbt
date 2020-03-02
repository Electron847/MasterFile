name := "akka-quickstart-scala"

version := "1.0"

scalaVersion := "2.12.6"

resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"


lazy val akkaVersion = "2.5.22"

libraryDependencies ++= Seq(
  
  "com.typesafe.akka" %% "akka-actor" % akkaVersion,
  "com.typesafe.akka" %% "akka-testkit" % akkaVersion,
  "org.scalatest" %% "scalatest" % "3.0.5" % "test",
  "com.typesafe.akka" %% "akka-actor" % "2.5.22",
  "com.typesafe.akka" %% "akka-remote" % "2.5.22"
)
