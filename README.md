# [Coursier](https://get-coursier.io)

Manejador de dependencias para scala.
Lo instalamos en este proyecto asi:

```shell
curl -L -o bin/cs https://git.io/coursier-cli
```


# [Ammonite](https://ammonite.io/)

Ammonite es un REPL para scala super poderoso que permite 
descargar dependencias automaticamente y un monton de monerias.

Lo podemos ejecutar mediante coursier usando nuestro script `bin/amm`


# [Mill](http://www.lihaoyi.com/mill/)

Mill es la herramienta para construir proyectos de scala.

Se instala automaticamente usando nuestro script `bin/mill`


# Re-Generate IntelliJ IDEA Project structure

Si agregamos una nueva dependencia a nuestro proyecto, es una buena idea
exportar el layout para intellij, usando:

```shell
bin/mill mill.scalalib.GenIdea/idea
```


# Building

```shell
bin/mill backend.compile # compila el proyecto backend

bin/mill backend.runMain backend.Main # corre la App backend.Main

```

