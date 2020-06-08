package backend

import akka.http.scaladsl.server.Directives._
import scalatags.Text.all._
import scalatags.Text.all.head
import akka.http.scaladsl.model._

import akka.http.scaladsl.model.ContentTypes.`text/html(UTF-8)`

class GreetController(greeting: String) {

  val routes =
    pathPrefix(greeting) {
      path(Segment) { name: String => complete(textHtml(sayHello(name))) }
    }

  def textHtml(content: String): HttpResponse = {
    val htmlContentType: ContentType.WithCharset = ContentTypes.`text/html(UTF-8)`
    val html                                     = "<!DOCTYPE html>" + content
    val entity                                   = HttpEntity(html).withContentType(`text/html(UTF-8)`)
    HttpResponse(entity = entity)
  }

  def sayHello(name: String): String = {
    view(s"${greeting.capitalize}, ${name}").render
  }

  def view(fullGreeting: String) = {
    html(
      body(
        h1(
          fullGreeting
        ),
        div(`id` := "app"),
        script(src := "/assets/out.js", `type` := "text/javascript")
      )
    )

  }

}
