organization in ThisBuild := "com.yuiwai"
version in ThisBuild := "0.1.0"
scalaVersion in ThisBuild := "2.12.4"

lazy val coreDependency = core % "test->test;compile->compile"

lazy val core = (project in file("core"))
  .settings(
    name := "moiwa-core",
    libraryDependencies ++= Seq(
      "org.scalactic" %% "scalactic" % "3.0.1",
      "org.scalatest" %% "scalatest" % "3.0.1" % Test
    )
  )

lazy val components = (project in file("components"))
  .settings(
    name := "moiwa-components"
  )
  .dependsOn(core)

lazy val scene = (project in file("scene"))
  .settings(
    name := "moiwa-scene"
  )
  .dependsOn(components)