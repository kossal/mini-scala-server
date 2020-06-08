import mill._, scalalib._, scalajslib._

object backend extends ScalaModule {

  override def scalaVersion = "2.13.2"

  override def ivyDeps =
    super.ivyDeps() ++ Agg(
      ivy"com.github.ghostdogpr::caliban:0.8.1",
      ivy"com.github.ghostdogpr::caliban-akka-http:0.8.1",
      ivy"de.heikoseeberger::akka-http-circe:1.32.0",
      ivy"io.circe::circe-generic:0.13.0",
      ivy"com.lihaoyi::scalatags:0.8.2"
    )

  override def unmanagedClasspath = T {
    super.unmanagedClasspath() ++ Agg(
      PathRef(frontend.fastOpt().path / os.up / os.up)
    )  
  }

}


object frontend extends ScalaJSModule {
  override def scalaVersion = "2.13.2"
  override def scalaJSVersion = "1.1.0"
  override def scalaJSWorkerVersion = "1.0"
  
  import coursier.MavenRepository

  override def repositories =
   super.repositories ++ Agg(
           MavenRepository("https://jitpack.io")

   )

  override def ivyDeps = 
   super.ivyDeps() ++ Agg(
     ivy"com.github.vic.laminar_cycle::cycle-core::0.5.7"
   )
}