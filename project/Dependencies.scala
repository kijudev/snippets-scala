import sbt.*

// Single place for library versions shared by all modules.
object V:
  val zio       = "2.1.27"
  val zioHttp   = "3.12.0"
  val zioJson   = "1.1.3"
  val zioConfig = "4.1.2"
  val zioLog    = "2.5.5"

object Deps:
  val zio = Seq(
    "dev.zio" %% "zio"          % V.zio,
    "dev.zio" %% "zio-streams"  % V.zio,
    "dev.zio" %% "zio-test"     % V.zio % Test,
    "dev.zio" %% "zio-test-sbt" % V.zio % Test
  )

  val http     = Seq("dev.zio" %% "zio-http" % V.zioHttp)
  val datastar = Seq("dev.zio" %% "zio-http-datastar-sdk" % V.zioHttp)
  val json     = Seq("dev.zio" %% "zio-json" % V.zioJson)
  val config   = Seq(
    "dev.zio" %% "zio-config"          % V.zioConfig,
    "dev.zio" %% "zio-config-typesafe" % V.zioConfig
  )
  val logging = Seq("dev.zio" %% "zio-logging" % V.zioLog)
