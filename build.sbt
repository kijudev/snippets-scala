// Monorepo: every directory in modules/ is an independent sbt subproject.
// No explicit root project -> sbt creates one that aggregates all modules,
// so `sbt compile` / `sbt test` cover everything.

ThisBuild / scalaVersion := "3.10.1"
ThisBuild / organization := "dev.snippets"

ThisBuild / scalacOptions ++= Seq(
  "-deprecation",
  "-feature",
  "-unchecked",
  "-Wunused:all",
  "-Wvalue-discard"
)

// Helper so adding a module is a one-liner. Every module gets ZIO + zio-test.
def module(name: String): Project =
  Project(name, file(s"modules/$name")).settings(
    libraryDependencies ++= Deps.zio,
    run / fork         := true,
    run / connectInput := true
  )

// ---- modules (`just new <name>` appends here) ----

lazy val `hello-zio` = module("hello-zio")

lazy val `http-api` = module("http-api")
  .settings(libraryDependencies ++= Deps.http ++ Deps.json)
