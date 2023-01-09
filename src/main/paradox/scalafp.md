
# Functional Programming (in Scala)

## What is FP?

Functional programming is a programming paradigm where programs are constructed by applying and composing functions

It is a declarative programming paradigm in which function definitions are trees of expressions that each return a value, rather than a sequence of imperative statements which change the state of the program.

* Immutable values
* Pure functions
* Functions are values
* Functional error handling

## Immutable values

In pure functional programming, only immutable values are used. In Scala this means:

* All variables are created as val fields
* Only immutable collections classes are used, 
such as List, Vector, and the immutable Map and Set
classes

Using only immutable variables raises an interesting
question: If everything is immutable, how does 
anything ever change?

When it comes to using collections, one answer is 
that you don’t mutate an existing collection;
instead, you *apply* a function to an existing 
collection to create a new collection. This is 
where **higher-order** functions like `map` and 
`filter` come in.

For example, imagine that you have a list of names — 
a `List[String]` — that are all in lowercase, and 
you want to find all the names that begin with the
letter "j", and then you want to capitalize those 
names. 

How would you write this code?  Perhaps:

```scala
val a = List("jane", "jon", "mary", "joe")
val b = new ListBuffer[String]
a.foreach(i => {
  if (i.startsWith("j")) {
    b += i.capitalize
  }
})
b.toList
```

In FP you write this code:

```scala
val a = List("jane", "jon", "mary", "joe")
val b = a.filter(_.startsWith("j"))
  .map(_.capitalize)
```

Which is easier to read?  understand?  reason about?

When functions get complex, we still need to be able
to make them as easy to comprehend as possible.

## Pure Functions

Another feature that Scala offers to help you write
functional code is the ability to write pure 
functions. A *pure function* can be defined like
this:

* A function `f` is pure if, given the same input `x`,
it always returns the same output `f(x)`
* The function’s output depends only on its input
variables and its implementation
* It only computes the output and does not modify
the world around it

This implies:

* It doesn’t modify its input parameters
* It doesn’t mutate any hidden state
* It doesn’t have any “back doors”: It doesn’t read
data from the outside world (including the console,
web services, databases, files, etc.), 
or write data to the outside world

As a result of this definition, any time you call a 
pure function with the same input value(s), you’ll
always get the same result.

For example, you can call a `double` function an 
infinite number of times with the input value `2`,
and you’ll always get the result `4`.


## Functions are values

You can create functions as values, 
just like you create `String` and `Int` values.

```scala
val nums = (1 to 10).toList

val doubles = nums.map(_ * 2)           // double each value
val lessThanFive = nums.filter(_ < 5)   // List(1,2,3,4)
```

In this example, anonymous functions are passed 
into map and filter.

*Anonymous functions are also known as lambdas.*

In addition to passing anonymous functions into 
`filter` and `map`, you can also supply them with 
methods:

```scala
// two methods
def double(i: Int): Int = i * 2
def underFive(i: Int): Boolean = i < 5

// pass those methods into filter and map
val doubles = nums.filter(underFive).map(double)
```

This ability to treat methods and functions as 
values is a powerful feature that functional
programming languages provide.

*Technically, a function that takes another function
as an input parameter is known as a Higher-Order 
Function.*

## Functional error handling

Functional programming is like writing a series of
algebraic equations, and because algebra doesn’t
have null values or throw exceptions, you don’t
use these features in FP.

Scala’s solution is to use constructs like the 
`Option`/`Some`/`None` classes.

### An example

Write a function that makes it easy to convert 
strings to integer values, and you want an elegant
way to handle the exception that’s thrown when your
function gets a string that cannot be parsed to
an Int value.

```scala
def makeInt(s: String): Int =
  try {
    Integer.parseInt(s.trim)
  } catch {
    case e: Exception => 0
  }
```
If we get any string we cannot parse, we return 0.

### Using Option/Some/None

1. Declare that makeInt returns an Option type
1. If makeInt receives a string it can convert 
to an Int, the answer is wrapped inside a Some
1. If makeInt receives a string it can’t convert,
it returns a None

Here’s the revised version of makeInt:

```scala
def makeInt(s: String): Option[Int] =
  try {
    Some(Integer.parseInt(s.trim))
  } catch {
    case e: Exception => None
  }
```

### Consuming makeInt()

There are two common answers, depending on your 
needs:

* Use a match expression
* Use a for expression

```scala
makeInt(x) match {
  case Some(i) => println(i)
  case None => println("That didn’t work.")
}
```

#### For expression

Imagine that you want to convert three strings to
integer values, and then add them together. This 
is how you do that with a for expression and
makeInt:

```scala
val y = for {
  a <- makeInt(stringA)
  b <- makeInt(stringB)
  c <- makeInt(stringC)
} yield {
  a + b + c
}
```

After that expression runs, y will be one of two 
things:

If all three strings convert to Int values, y will
be a `Some[Int]`, i.e., an integer wrapped inside
a `Some`

If any of the three strings can’t be converted to
an `Int`, y will be a `None`

### Thinking of Option as a container
Mental models can often help us understand new 
situations, so if you’re not familiar with the
Option classes, one way to think about them is as
a container:

`Some` is a container with one item in it

`None` is a container, but it has nothing in it

If you prefer to think of the `Option` classes as 
being like a box, `None` is like an empty box. 
It could have had something in it, but it doesn’t.

### Using Option to replace null

Getting back to null values, a place where a null
value can silently creep into your code is with
a class like this:

```scala
class Address(
  var street1: String,
  var street2: Option[String],   // an optional value
  var city: String, 
  var state: String, 
  var zip: String
)

val santa1 = new Address(
  "1 Main Street",
  None,           // 'street2' has no value
  "North Pole",
  "Alaska",
  "99705"
)

val santa2 = new Address(
  "123 Main Street",
  Some("Apt. 2B"),
  "Talkeetna",
  "Alaska",
  "99676"
)
```

## Summary

* Functional programmers don’t use null values
* A main replacement for `null` values is to use 
the `Option` classes
* Functional methods don’t throw exceptions;
instead they return values like `Option`, `Try`, 
or `Either`
* Common ways to work with `Option` values are
`match` and `for` expressions
* Options can be thought of as containers of one 
item (`Some`) and no items (`None`)
* Options can also be used for optional constructor
or method parameters

## References
* 
* https://docs.scala-lang.org/scala3/book/fp-intro.html
* https://www.manning.com/books/functional-programming-in-scala
* https://livebook.manning.com/book/functional-programming-in-scala-second-edition/meap-version-3/
