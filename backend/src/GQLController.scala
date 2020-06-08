package backend

import akka.http.scaladsl.server.Directives._
import caliban.interop.circe.AkkaHttpCirceAdapter
import scala.concurrent.ExecutionContext
import akka.stream.Materializer

case class GQLController()(
    implicit ec: ExecutionContext,
    materializer: Materializer
) extends AkkaHttpCirceAdapter {

  implicit val runtime =
    zio.Runtime.default

  val apiInterpreter = runtime.unsafeRun(GQLSchema.api.interpreter)

  val routes =
    path("graphql") {
      adapter.makeHttpService(apiInterpreter) ~
        adapter.makeWebSocketService(apiInterpreter)
    }

}
