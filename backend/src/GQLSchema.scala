package backend

import caliban.GraphQL.graphQL
import caliban.RootResolver

object GQLSchema {

  case class GreetingArgs(name: String)

  case class Queries(
      version: String,
      hello: GreetingArgs => String,
      bye: GreetingArgs => String
  )

  def greet(greeting: String): GreetingArgs => String = { args => s"${greeting}, ${args.name}" }

  val queries = Queries(
    version = "1.0.0-WILSON",
    hello = greet("hello"),
    bye = greet("bye")
  )

  val api = graphQL(RootResolver(queries))

}
