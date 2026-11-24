//> using scala 3.10.1
//> using dep dev.zio::zio:2.1.27

// Single-file scratchpad: `just snippet snippets/hello.scala`
import zio.*

object App extends ZIOAppDefault:
  def run =
    ZIO.foreachPar(1 to 5)(i => ZIO.sleep(i.millis * 100) *> Console.printLine(s"fiber $i done"))
