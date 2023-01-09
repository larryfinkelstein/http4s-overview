# Cats Effect (ce3)

## What is CATs Effect?

Cats Effect is a high-performance, asynchronous, 
composable framework for building real-world 
applications in a purely functional style within
the Typelevel ecosystem. 

It provides a concrete tool, known as "the `IO` 
monad"  (see [5 minute monad tutorial](http://www.cs.cornell.edu/~akhirsch/monads.html)), 
for capturing and controlling actions, often
referred to as "effects",
that your program wishes to perform within a 
resource-safe, typed context with seamless support
for concurrency and coordination.

These effects may be asynchronous (callback-driven)
or synchronous (directly returning values); they
may return within microseconds or run infinitely.

Even more importantly, Cats Effect defines a set of
typeclasses which define what it means to be a purely
functional runtime system. These abstractions power 
a thriving ecosystem consisting of streaming 
frameworks, JDBC database layers, HTTP servers and 
clients, asynchronous clients for systems like Redis
and MongoDB, and so much more! 

Additionally, you can leverage these abstractions
within your own application to unlock powerful 
capabilities with little-or-no code changes,
for example solving problems such as dependency
injection, multiple error channels, shared state 
across modules, tracing, and more.

## Hello, World

```scala
import cats.effect._

object Main extends IOApp.Simple {
  val run = IO.println("Hello, World!")
}
```

Or, if you need the ability to take arguments and 
return exit codes:

```scala
import cats.effect._

object Main extends IOApp {
    def run(args: List[String]): IO[ExitCode] =
      if (args.headOption.map(_ == "--do-it").getOrElse(false))
        IO.println("I did it!").as(ExitCode.Success)
      else 
        IO.println("Didn't do it").as(ExitCode(-1))
}
```
## Five Simple Rules
Any program written using Cats Effect provides 
incredibly strong guarantees and powerful 
functionality, performance, safety, and 
composability, provided you follow each of the 
following rules:

1. **Wrap all side-effects** in `delay`, `async`, `blocking`,
or `interruptible`/`interruptibleMany`</br>
    * (pro tip: try to keep the size of your `delay`
    blocks small; two `delay`s with a `flatMap` is
    much better than one big delay)
2. Use `bracket` or `Resource` for anything which 
must be closed
1. ***Never* hard-block a thread** outside of `blocking`
or `interruptible`/`interruptibleMany`
1. Use `IOApp` instead of writing your own `def main`
1. Never call anything that has the word `unsafe` 
in the name

## References

* https://typelevel.org/cats-effect/
* https://github.com/typelevel/cats-effect
* https://typelevel.org/cats-effect/docs/tutorial
* https://yadukrishnan.live/cats-effect-3-for-beginners-part-1
* https://www.baeldung.com/scala/cats-effects-intro
* https://www.baeldung.com/scala/cats-effect-fibers-concurrent-programming
