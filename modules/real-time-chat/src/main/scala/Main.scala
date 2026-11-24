import zio.*
import zio.http.*
import zio.json.*

final case class Greeting(message: String) derives JsonEncoder

object Main extends ZIOAppDefault:

  val routes: Routes[Any, Response] = Routes(
    Method.GET / "health"                 -> handler(Response.text("ok")),
    Method.GET / "hello" / string("name") -> handler {
      (name: String, _: Request) =>
        Response.json(Greeting(s"Hello, $name!").toJson)
    }
  )

  def run = Server.serve(routes).provide(Server.defaultWithPort(8080))
