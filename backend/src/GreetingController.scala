package backend


import akka.http.scaladsl.server.Directives._


class GreetController(greeting: String) {

  val routes =
    pathPrefix(greeting) { 
      path(Segment) { name: String =>
        complete(sayHello(name))
      }
    }

  def sayHello(name: String): String = {
    s"${greeting.capitalize}, ${name}"
  }

}