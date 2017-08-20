val commonSettings = Seq(
  organization := "yuiwai.com",
  version := "0.1.0",
  scalaVersion := "2.12.3"
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

lazy val router = (project in file("router"))
  .settings(
    commonSettings,
    name := "moiwa-router"
  )
  .dependsOn(core)

lazy val auth = (project in file("auth"))
  .settings(
    commonSettings,
    name := "moiwa-auth"
  )
  .dependsOn(core)

lazy val adapter = (project in file("adapter"))
  .settings(
    commonSettings,
    name := "moiwa-adapter"
  )
  .dependsOn(core % "test->test;compile->compile")

lazy val generator = (project in file("generator"))
  .settings(
    commonSettings,
    name := "moiwa-generator"
  )
  .dependsOn(core)

lazy val formation = (project in file("formation"))
  .settings(
    commonSettings,
    name := "moiwa-formation"
  )
  .dependsOn(core)
