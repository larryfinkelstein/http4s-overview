# FS2 (Functional Streams for Scala)

## What is FS2?

**Functional, effectful, concurrent streams for Scala**

* I/O (networking, files) computations in constant memory
* Stateful transformations
* Resource safety and effect evaluation
* Built on Cats Effect and powers http4s, skunk, and doobie

## Example

FS2 is a streaming I/O library. The design goals
are compositionality, expressiveness, resource 
safety, and speed. Here's a simple example
of its use:

```scala
import cats.effect.{IO, IOApp}
import fs2.{Stream, text}
import fs2.io.file.{Files, Path}

object Converter extends IOApp.Simple {

  val converter: Stream[IO, Unit] = {
    def fahrenheitToCelsius(f: Double): Double =
      (f - 32.0) * (5.0/9.0)

    Files[IO].readUtf8Lines(Path("testdata/fahrenheit.txt"))
      .filter(s => !s.trim.isEmpty && !s.startsWith("//"))
      .map(line => fahrenheitToCelsius(line.toDouble).toString)
      .intersperse("\n")
      .through(text.utf8.encode)
      .through(Files[IO].writeAll(Path("testdata/celsius.txt")))
  }

  def run: IO[Unit] =
    converter.compile.drain
}
```

This will construct a program that reads lines
incrementally from `testdata/fahrenheit.txt`,
skipping blank lines and commented lines. It then
parses temperatures in degrees Fahrenheit, converts
these to Celsius, UTF-8 encodes the output,
and writes incrementally to `testdata/celsius.txt`,
using constant memory. The input and output files 
will be closed upon normal termination or if 
exceptions occur.

Note that this example is specialised to `IO` for 
simplicity, but `Stream` is fully polymorphic in the 
effect type (the `F[_]` in `Stream[F, A]`), as long 
as `F[_]` is compatible with the `cats-effect`
typeclasses.

## References
* https://github.com/typelevel/fs2
* https://fs2.io
* https://www.baeldung.com/scala/fs2-functional-streams
* https://blog.rockthejvm.com/fs2/