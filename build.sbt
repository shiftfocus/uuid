name := "uuid"

organization := "ca.shiftfocus"

version := "2.0.0"

scalaVersion := "2.12.18"

crossScalaVersions := Seq("2.10.4", "2.11.6")

val root = project in file(".")

publishMavenStyle := true

publishTo := {
  val privateKeyFile = new java.io.File(sys.env("HOME") + "/.ssh/id_rsa")
  Some(Resolver.sftp(
    "ShiftFocus Maven Repository",
    "maven.private.shiftfocus.ca",
    22,
    "/var/www/maven.shiftfocus.ca/repositories/" + {
      if (isSnapshot.value) "snapshots" else "releases"
    }
  ) as ("gitlab-runner", privateKeyFile))
}

resolvers += "Typesafe repository" at "https://repo.typesafe.com/typesafe/releases/"

libraryDependencies ++= Seq(
  "com.typesafe.play" %% "play-json" % "2.9.1"
)
