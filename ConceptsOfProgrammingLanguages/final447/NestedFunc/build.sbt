scalaVersion := "2.13.1"
scalacOptions += "-language:higherKinds"
addCompilerPlugin("org.typelevel" %% "kind-projector" % "0.11.0" cross CrossVersion.full)

libraryDependencies += "org.scastie" %% "runtime-scala" % "1.0.0-SNAPSHOT"
turbo := true
useSuperShell := false
scalacOptions ++= Seq(
  "-deprecation",
  "-encoding", "UTF-8",
  "-feature",
  "-unchecked"
)