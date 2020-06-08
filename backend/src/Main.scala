package backend

import caliban.GraphQL

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.Http
import akka.actor.ActorSystem
import scala.concurrent.ExecutionContextExecutor
import scala.io.StdIn

object Main extends App {

  implicit val system: ActorSystem                        = ActorSystem()
  implicit val executionContext: ExecutionContextExecutor = system.dispatcher

  val helloController = new GreetController("hello")
  val byeController   = new GreetController("bye")

  val routes =
    helloController.routes ~
      byeController.routes ~
      GQLController().routes ~
      pathPrefix("assets") {
        getFromResourceDirectory("dest")
      }

  val bindingFuture = Http().bindAndHandle(routes, "0.0.0.0", 8080)
  println(s"Server online at http://localhost:8080/\nPress RETURN to stop...")
  StdIn.readLine()
  bindingFuture
    .flatMap(_.unbind())
    .onComplete(_ => system.terminate())

}
