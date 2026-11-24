import zio.test.*

object MainSpec extends ZIOSpecDefault:
  def spec = suite("hello-zio")(
    test("greet") {
      for msg <- Main.greet("ZIO")
      yield assertTrue(msg == "Hello, ZIO!")
    }
  )
