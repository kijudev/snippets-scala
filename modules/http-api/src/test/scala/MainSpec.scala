import zio.http.*
import zio.test.*

object MainSpec extends ZIOSpecDefault:
  def spec = suite("http-api")(
    test("GET /hello/:name") {
      for
        res  <- Main.routes.runZIO(Request.get(URL.root / "hello" / "zio"))
        body <- res.body.asString
      yield assertTrue(res.status == Status.Ok, body == """{"message":"Hello, zio!"}""")
    }
  )
