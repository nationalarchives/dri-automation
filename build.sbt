name := "dri-automation"

version := "0.1"

scalaVersion := "2.13.8"

libraryDependencies += "com.lightbend.akka" %% "akka-stream-alpakka-sqs" % "3.0.4"
libraryDependencies += "org.scalactic" %% "scalactic" % "3.2.12"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.12" % "test"