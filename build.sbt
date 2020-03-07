organization in ThisBuild := "com.yuiwai"
version in ThisBuild := "0.2.0-SNAPSHOT"
scalaVersion in ThisBuild := "2.13.1"

lazy val coreDependency = core % "test->test;compile->compile"

lazy val core = (project in file("core"))
  .settings(
    name := "moiwa-core",
    libraryDependencies ++= Seq(
      "org.scalactic" %% "scalactic" % "3.1.1",
      "org.scalatest" %% "scalatest" % "3.1.1" % Test
    )
  )

lazy val vcon = (project in file("vcon"))
  .settings(
    name := "moiwa-vcon"
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

lazy val `min-rpg` = (project in file("min-rpg"))
  .dependsOn(scene)

lazy val example = (project in file("example"))
  .dependsOn(scene)
