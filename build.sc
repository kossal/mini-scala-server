import mill._, scalalib._

object backend extends ScalaModule {

  override def scalaVersion = "2.13.2"

  override def ivyDeps =
    super.ivyDeps() ++ List(
      ivy"com.github.ghostdogpr::caliban:0.8.1",
      ivy"com.github.ghostdogpr::caliban-akka-http:0.8.1",
      ivy"de.heikoseeberger::akka-http-circe:1.32.0",
      ivy"io.circe::circe-generic:0.13.0"
    )

}
