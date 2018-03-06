val commonSettings = Seq(
  organization := "yuiwai.com",
  version := "0.1.0",
  scalaVersion := "2.12.4"
)
lazy val coreDependency = core % "test->test;compile->compile"

lazy val core = (project in file("core"))
  .settings(
    commonSettings,
    name := "moiwa-core",
    libraryDependencies ++= Seq(
      "org.scalactic" %% "scalactic" % "3.0.1",
      "org.scalatest" %% "scalatest" % "3.0.1" % Test
    )
  )

