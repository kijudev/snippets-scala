import zio.*

object Main extends ZIOAppDefault:

  def greet(name: String): UIO[String] =
    ZIO.succeed(s"Hello, $name!")

  def run: ZIO[ZIOAppArgs & Scope, Any, Any] =
    for
      _    <- Console.printLine("What's your name?")
      name <- Console.readLine.orElseSucceed("stranger")
      msg  <- greet(name)
      _    <- Console.printLine(msg)
    yield ()
