>#Notes for *Programming in Scala, 1st Ed*

***
# Contents
1. [Collections](#collections)
2. [Functional Style](#fp)
3. [Classes and Objects](#classes)
4. [Operator Overload](#op-overloading)
5. [Control Structures](#control-structures)
6. [Exceptions](#exceptions)
7. [Match Expressions](#match-exp)
8. [Functions and Closures](#func-and-closures)
9. [Control Abstraction](#control-abstraction)
10. [Composition and Inheritance](#comp-and-inher)
11. [Traits](#traits)
12. [Packages, Imports](#packages-imports)
13. [Access Modifiers](#access-modifiers)

<a id="collections"></a>
## Collections 
  * [Arrays](#arrays") - Mutable sequence of objects that are all the same type
    * `val array = new Array[Int](0,1,2)`
  * [Lists](#lists) - Immutable sequence of objects that are all the same type
    * `val list = List(1,2,3)`
    * you cons onto a list because appending is linear and cons'ing is constant
  * [Tuples](#tuples)
    * `val pair = (99, "Luft Ballons")`
    * immutable
    * can contain different types
    * access elements w/ `._1`, `._2`, ... `._N` (one indexed)
  * [Sets](#sets)
    * `val set = Set(1,2,3)`
    * Mutable and Immutable versions `scala.collection.immutable` and `scala.collection.mutable`
    * `+` or `+=` to add to the set
    * Factory methods to create different implementations
        - `val hashset = HashSet(1,2,3)`
  * [Maps](#maps)
    * `val map = Map(1->"one", 2->"two")`
    * Mutable and Immutable versions `scala.collection.immutable` and `scala.collection.mutable`
    * Factory methods to create different implementations
      * `val hashmap = HashMap(1->"one", 2->"two")`
    * immutable by default
    * `+` or `+=` to add

<a id="arrays"></a>
### Arrays 
``` scala
val greetStrings = new Array[String](3)
greetStrings(0) = "Hello"
greetStrings(1) = ", "
greetStrings(2) = "world!\n"

// Explicitly declare the type:
val greetString: Array[String] = new Array[String](3)

```


Under the hood, Scala is calling a method call apply on the Array companion object that returns a new array (think static method)
``` scala
// syntactic sugar
val numNames = Array("zero", "one", "two")

// Equivalent to:
val numNames2 = Array.apply("zero", "one", "two")

```

<a id="lists"></a>
### Lists 
| Common List Operations                                           | Explanation                          |
| -----------------------------------------------------------------| ------------------------------------ |
| `List()` or `Nil`	                                                   | The empty List
| `List("Cool", "tools", "rule")`                                    | new List |
| `::`	                                                           | Cons operator |
| `:::`	                                                           | Cons 2 lists |
| `myList(2)`	                                                       | element at index 2 (zero based) |
| `count(s => s.length == 4)`                                        | Counts the number of string elements in length 4 |
| `drop(2)`	                                                       | Returns the list without its first 2 elements |
| `dropRight(2)`	                                                   | Returns the list without its rightmost 2 elements |
| `exists(s => s == "until")`                                       | Determines whether a string element exists in that has the value "until" returns Boolean |
| `filter(s => s.length == 4)`	                                   | Returns a list of all elements, in order,  that have length 4 |
| `forall(s => s.endsWith("l"))`           	                       | Indicates whether all elements in the thrill list end with the letter "l" (returns true) |
| `foreach(s => print(s))`	                                       | Executes the print statement on each of the strings in the list |
| `foreach(print)`	                                               | Same as the previous |
| `head`	                                                           | Returns the first element in the list |
| `init`	                                                           | Returns a list of all but the last element in the list |
| `isEmpty`	                                                       | Indicates whether the list is empty, Boolean |
| `last`	                                                           | Returns the last element in the thrill list |
| `length`	                                                       | Returns the number of elements in the list |
| `map(s => s + "y")`	                                               | Returns a list resulting from adding a "y" to each string element in the list |
| `mkString(", ")`	                                               | Makes a string with the elements of the list |
| `remove(s => s.length == 4)`	                                   | Returns a list of all elements, in order, of the list except those that have length 4 |
| `reverse`	                                                       | Returns a list containing all elements of the list in reverse order |
| `sort((s, t) => s.charAt(0).toLowerCase < t.charAt(0).toLowerCase)` | Returns a list containing all elements of the thrill list in alphabetical order of the first character lowercased |
| `tail`	                                                           | Returns the list minus its first element |

<a id="tuples"></a>
### Tuples 

The actual type of a tuple depends on the number of elements it contains and the types of those elements. Thus, the type of (99, "Luftballons")
is Tuple2\[Int, String]. The type of ('u', 'r', "the", 1, 4, "me") is Tuple6\[Char, Char, String, Int, Int, String]

<a id="sets"></a>
### Sets 

Although the default set implementations produced by the mutable and immutable Set factory methods shown thus far will
likely be sufficient for most situations, occasionally you may want an explicit set class. Fortunately, the syntax is
similar. Simply import that class you need, and use the factory method on its companion object. For example, if you
need an immutable HashSet, you could do this:

``` scala

import scala.collection.immutable.HashSet

val hashSet = HashSet("Tomatoes", "Chilies")
println(hashSet + "Coriander")

```

<a id="maps"></a>
### Maps 

``` scala

import scala.collection.mutable.Map

val treasureMap = Map[Int, String]()
treasureMap += (1 -> "Go to island.")
treasureMap += (2 -> "Find big X on ground.")
treasureMap += (3 -> "Dig.")

```

### File reading example

```scala

import scala.io.Source

val lines = Source.fromFile(args(0)).getLines.toList
def widthOfLength(s: String) = s.length.toString.length

//calculate max line width
val longestLine = lines.reduceLeft(a,b) => if(a.length > b.length) a else b
val maxWidth = widthOfLength(longestLine)

//print everything out, formatted
for (line <- lines) {
    val numSpaces = maxWidth - widthOfLength(line)
    val padding = " " * numSpaces
    print(padding + line.length +" | "+ line)
}

```

<a id="classes"></a>
## Classes and Objects 

``` scala

class ChecksumAccumulator {
    var sum = 0
    //stuff
}

```

create an instance with `new` keyword

``` scala

val acc = new ChecksumAccumulator

```

you can access the `sum` field with the `.` operator

`acc.sum`

like Java, there are access modifiers

``` scala

//public <= this is the default access modifier
private
protected
//default <= this one I don't really know about for Scala.

```

<a id="methods"></a>
### Methods 
##### method parameters are vals not vars
``` scala

class ChecksumAccumulator {
    private var sum = 0

    def add(b: Byte): Unit = {
      sum += b
    }

    def checksum(): Int = {
      return ~(sum & 0xFF) + 1
    }
}

```

#####  Because all checksum does is calculate a value, it does not need an explicit return.

``` scala

class ChecksumAccumulator {
  private var sum = 0
  def add(b: Byte): Unit = sum += b
  def checksum(): Int = ~(sum & 0xFF) + 1
}

```

**One puzzler to watch out for is that whenever you leave off the equals sign before the body of a function, its result 
type will definitely be Unit.**

*Note: `;` is optional. Scala will infer it.*

>The rules of semicolon inference

>1. The precise rules for statement separation are surprisingly simple for how well they work. In short, a line ending is 
treated as a semicolon unless one of the following conditions is true:
>2. The line in question ends in a word that would not be legal as the end of a statement, such as a period or an infix operator.
>3. The next line begins with a word that cannot start a statement.
>4. The line ends while inside parentheses (...) or brackets [...], because these cannot contain multiple statements anyway.

### Singleton objects

**Classes in Scala cannot have static members. Instead, Scala has singleton objects. A singleton object definition looks 
like a class definition, except instead of the keyword class you use the keyword `object`.**

``` scala

 import scala.collection.mutable.Map

 object ChecksumAccumulator {

   private val cache = Map[String, Int]()

   def calculate(s: String): Int =
     if (cache.contains(s))
       cache(s)
     else {
       val acc = new ChecksumAccumulator
       for (c <- s)
         acc.add(c.toByte)
       val cs = acc.checksum()
       cache += (s -> cs)
       cs
     }
 }

```

The singleton object in this figure is named ChecksumAccumulator, the same name as the class in the previous example. 
When a singleton object shares the same name with a class, it is called that class's companion object. You must define 
both the class and its companion object in the same source file. The class is called the companion class of the singleton
object. A class and its companion object can access each other's private members. One way to think of singleton objects is as the home for any static methods you might have written in Java

<a id="op-overloading"></a>
## Operator Overloading 
**Yes, you can overload operators. But, only do it where it would makes sense and will make the code more readable (This is not C++).**

``` scala

class Rational(n: Int, d: Int) {

  require(d != 0)

  private val g = gcd(n.abs, d.abs)
  val numer = n / g
  val denom = d / g

  def this(n: Int) = this(n, 1)

  def + (that: Rational): Rational =
    new Rational(
      numer * that.denom + that.numer * denom,
      denom * that.denom
    )

  def * (that: Rational): Rational =
    new Rational(numer * that.numer, denom * that.denom)

  override def toString = numer +"/"+ denom

  private def gcd(a: Int, b: Int): Int =
    if (b == 0) a else gcd(b, a % b)
}

```

<a id="control-structures"></a>
## Control Structures 

|  Syntax                   |                                                                 |
| ----------------------- | --------------------------------------------------- |
| `if else`                   | test conitions |
| `while` / `do while`  | repetition structure |
| `for`                       | blocking iteration  `for(i <- 1 to 4)` inculde upper bound and `for(i <- 1 until 4)` exclude upper bound |
| filtering                  | add an `if` clause inside a `for` clause's paraens `for(i <- 1 to 5 if i % 2 == 0)` |
| multiple `<-`           | will yield nested loops |
| variable bindings     | use `=` to bind save from performing expensive operations multiple times |
| `yield`                    | produce a value to remember each iteration |

``` scala

// example of yield
def scalaFiles =
  for {
    file <- filesHere
    if file.getName.endsWith(".scala")
  } yield file

//Transforming an Array[File] to Array[Int] with a for.
val forLineLengths =
  for {
    file <- filesHere
    if file.getName.endsWith(".scala")
    line <- fileLines(file)
    trimmed = line.trim
    if trimmed.matches(".*for.*")
  } yield trimmed.length

```

<a id="exceptions"></a>
## Exceptions 

* throw like Java exceptions
  * `throw new IllegalArgumentException`
* have type `Nothing`
* Scala doesn't require you to catch exceptions
  * use `@throws` annotation to declare a `throws` clause
* `catch` uses pattern-matching
* has `finally` clause

``` scala

import java.io.FileReader
import java.io.FileNotFoundException
import java.io.IOException

try {
  val f = new FileReader("input.txt")
  // Use and close file
} catch {
  case ex: FileNotFoundException => // Handle missing file
  case ex: IOException => // Handle other I/O error
}

```

You can yield a value from a catch:

``` scala

import java.net.URL
import java.net.MalformedURLException

def urlFor(path: String) =
  try {
    new URL(path)
  } catch {
    case e: MalformedURLException =>
      new URL("http://www.scala-lang.org")
  }

```

<a id="match-exp"></a>
## Match Expressions 
``` scala
val firstArg = if (args.length > 0) args(0) else ""

firstArg match {
  case "salt" => println("pepper")
  case "chips" => println("salsa")
  case "eggs" => println("bacon")
  case _ => println("huh?")
}
```

### Looping without break or continue

*Java code*
``` java

int i = 0;                // This is Java
boolean foundIt = false;
while (i < args.length) {
  if (args[i].startsWith("-")) {
    i = i + 1;
    continue;
  }
  if (args[i].endsWith(".scala")) {
    foundIt = true;
    break;
  }
  i = i + 1;
}

```

*Scala w/ recursion*
``` scala

def searchFrom(i: Int): Int =
      if (i >= args.length) -1
      else if (args(i).startsWith("-")) searchFrom(i + 1)
      else if (args(i).endsWith(".scala")) i
      else searchFrom(i + 1)

val i = searchFrom(0)

```

_**the Scala compiler will tail-call optimize this recursive code**_

<a id="func-and-closures"></a>
## Functions and Closures 
  * [Local functions](#local-func)
  * [First-class functions](#first-class-func)
  * [Placeholder syntax](#placeholder)
  * [Repeated parameters](#repeated-params)
  * [Tail recursion](#tail-rec)

<a id="local-func"></a>
### Local functions 

Better than private methods in Java

``` scala

def processFile(filename: String, width: Int) {

  def processLine(filename: String,
      width: Int, line: String) {

    if (line.length > width)
      print(filename +": "+ line)
  }

  val source = Source.fromFile(filename)
  for (line <- source.getLines) {
    processLine(filename, width, line)
  }
}

```

Here, `processLine` is scoped to inside `processFile` and no one else can access it. But we can do better. Since `width` 
and `fileName` are scoped to `processFile` we don't need to pass those in. A child def has access to it's parent's scope.

``` scala

import scala.io.Source

object LongLines {

  def processFile(filename: String, width: Int) {

    def processLine(line: String) {
      if (line.length > width)
        print(filename +": "+ line)
    }

    val source = Source.fromFile(filename)
    for (line <- source.getLines)
      processLine(line)
  }
}

```

<a id="first-class-func"></a>
### First-class functions 
Scala has first-class functions. A function literal is compiled into a class that when instantiated at runtime is a function 
value. The difference is, literals exist in the source code and function values are objects at runtime. e.g.

``` scala

(x: Int) => x + 1

```

The `=>` designates that this function converts the thing on the left (any integer x) to the thing on the right (x + 1). 
So, this is a function mapping any integer x to x + 1.

``` scala

// For multi-line functions surround with `{ }`
increase = (x: Int) => {
   println("We")
   println("are")
   println("here!")
   x + 1
}

```

<a id="placeholder"> </a>
### Placeholder syntax `_` 

To make a function literal even more concise, you can use underscores as placeholders for one or more parameters, so 
long as each parameter appears only one time within the function literal.

`someNumbers.filter(_ > 0)`

Sometimes the compiler doesn't have enough information to infer syntax. Consider `_ + _`. Instead we can say

``` scala

val f = (_: Int) + (_:Int)

```

You can also replace an entire parameter list with an `_`

``` scala

someNumbers.foreach(println _)

```

*when you use an `_` like this you are writing a partially applied function*

Consider:

``` scala

def sum(a: Int, b: Int, c: Int) = a + b + c

```

results in `sum: (Int,Int,Int)Int`

`sum(1,2,3)` results in `Int = 6`

`val a = sum _` results in `a: (Int, Int, Int) => Int = <function>`
now we can say `a(1,2,3)` and we get `Int = 6`. The Scala compliler instantiated a function value that takes the 3 integer 
parameters missing from the partially applied function expression, `sum _`, and assigns a reference to that new function 
value to the variable a. The variable name `a` refers to a function value object that is generated by the Scala compiler 
from the partially applied function expression, `sum _`.the compiler translates the expression `a(1,2,3)` into an invocation 
of the function value's `apply` method. The `apply` method generated takes 3 args b/c 3 is the number of missing args in 
the `sum _` expression. The Scala compiler translates the expression `a(1, 2, 3)` into an invocation of the function value's 
`apply` method, passing in the three arguments 1, 2, and 3. Thus, `a(1, 2, 3)` is a short form for

`a.apply(1, 2, 3)`

This apply method, defined in the class generated automatically by the Scala compiler from the expression `sum _`, 
simply forwards those three missing parameters to `sum`, and returns the result. In this case apply invokes `sum(1, 2, 3)`, 
and returns what sum returns, which is 6. Here, the `_` is used to represent the enitre parameter list. Now, although 
`sum _` is indeed a partially applied function, it may not be obvious to you why it is called this. It has this name 
because you are not applying that function to all of its arguments. In the case of `sum _`, you are applying it to none 
of its arguments. But you can also express a partially applied function by supplying some but not all of the required 
arguments. Here's an example:

``` scala

val b = sum(1, _: Int, 3)

b: (Int) => Int = <function>

```

Now we have a partially applied function, `b` that is only missing the middle argument

If you are writing a partially applied function expression in which you leave off all parameters, such as println _ 
or sum _, you can express it more concisely by leaving off the underscore if a function is required at that point in 
the code.

`someNumbers.foreach(println)`

<a id="closures"></a>
### Closures 

So far we've only looked at function literals that refer to passed in parameters. You can also refer to variables defined
elsewhere.

``` scala

var more = 1
// ... a little ways does the source
val addMore = (x: Int) => x + more
// results in `addMore: (Int) => Int = <function>`
addMore(10)

```

The function value (the object) that's created at runtime from this function literal is called a closure. The name 
arises from the act of "closing" the function literal by "capturing" the bindings of its free variables. A function 
literal with no free variables, such as `(x: Int) => x + 1`, is called a closed term, where a term is a bit of source 
code. Thus a function value created at runtime from this function literal is not a closure in the strictest sense, 
because `(x: Int) => x + 1` is already closed as written. But any function literal with free variables, such as 
`(x: Int) => x + more`, is an open term. Therefore, any function value created at runtime from `(x: Int) => x + more` 
will by definition require that a binding for its free variable, more, be captured. The resulting function value, which 
will contain a reference to the captured more variable, is called a closure, therefore, because the function value is 
the end product of the act of closing the open term, `(x: Int) => x + more`.

**Scala's closures capture variables themselves, not the value to which variables refer.**

What if a closure accesses some variable that has several different copies as the program runs? For example, what if a 
closure uses a local variable of some function, and the function is invoked many times? Which instance of that variable 
gets used at each access? **the instance used is the one that was active at the time the closure was created.**

Each time this function is called it will create a new closure. Each closure will access the more variable that was 
active when the closure was created.

``` scala

def makeIncreaser(more: Int) = (x: Int) => x + more

val inc1 = makeIncreaser(1)
// inc1: (Int) => Int = <function>

val inc9999 = makeIncreaser(9999)
// inc9999: (Int) => Int = <function>

inc1(10)
// result Int = 11

inc9999(10)
// result Int = 10009

```

<a id="repeated-params"></a>
### Repeated parameters 

``` scala

def echo(args: String*) = for (arg <- args) println(arg)
// echo: (String*)Unit

echo()
echo("one")
// => one

echo("hello", "world!")
// => hello
// => world!

val arr = Array("What's", "up", "doc?")
arr: Array[java.lang.String] = Array(What's, up, doc?)

echo(arr)
// error: type mismatch;
// found   : Array[java.lang.String]
// required: String
//      echo(arr)

```

To accomplish this, you'll need to append the array argument with a colon and an `_*` symbol, like this

``` scala

echo(arr: _*)
// What's
// up
// doc?

```

<a id="tail-rec"> </a><a id="rec-tail"></a>
### Tail recursion 

A tail of 2 functions:

``` scala

//recursive
def approximate(guess: Double): Double =
    if (isGoodEnough(guess)) guess
    else approximate(improve(guess))

//iterative
def approximateLoop(initialGuess: Double): Double = {
      var guess = initialGuess
      while (!isGoodEnough(guess))
        guess = improve(guess)
      guess
    }

```

You might think that the iterative approach would be faster, but actually, they both incur the same overhead. The Scala 
compiler is able to transform the recursive call into an iterative one using *tail-call optimization*. If the recursive 
call is not in the tail position, the compiler can not optimize it.

``` scala

// Blowin' up da stack
def boom(x: Int): Int =
   if (x == 0) throw new Exception("boom!")
   else boom(x - 1) + 1

```

**This function is NOT tail recursive because it performs an increment AFTER the call**

``` scala

def bang(x: Int): Int =
    if (x == 0) throw new Exception("bang!")
    else bang(x - 1)

```

_**now it is**_

Scala only optimizes directly recursive calls back to the same function making the call. If the recursion is indirect, 
as in the following example of two mutually recursive functions, no optimization is possible:

``` scala

def isEven(x: Int): Boolean =
    if (x == 0) true else isOdd(x - 1)
def isOdd(x: Int): Boolean =
  if (x == 0) false else isEven(x - 1)

```

You also won't get a tail-call optimization if the final call goes to a function value. Consider for instance the following 
recursive code:

``` scala

val funValue = nestedFun _
def nestedFun(x: Int) {
  if (x != 0) { println(x); funValue(x - 1) }
}

```
The funValue variable refers to a function value that essentially wraps a call to nestedFun. When you apply the function 
value to an argument, it turns around and applies nestedFun to that same argument, and returns the result. You might hope, 
therefore, the Scala compiler would perform a tail-call optimization, but in this case it would not. Thus, tail-call 
optimization is limited to situations in which a method or nested function calls itself directly as its last operation, 
without going through a function value or some other intermediary.

If you still don't get it, see this awesome writeup in [Tail recursion](#rec-tail)

<a id="control-abstraction"></a>
## Control Abstraction 

* [Currying](#currying)

*How to apply function values to create new control abstractions*

**Reducing code duplication through first class functions**
Functions can be thought of as having 2 different parts, *common* and *uncommon*. Common parts are those parts that do not 
change from one invocation to the next. uncommon are their dual (those that change). e.g. *common* parts - function body, 
*uncommon* - function args. In Scala, we can easily define functions that take other functions as arguments (*Higher-Order Functions*). 
These higher-order functions let us create new control abstractions that can reduce code duplication. below we're working 
on a file browser that provides an API for users to search for files matching some criterion.

``` scala

object FileMatcher {
  private def filesHere = (new java.io.File(".")).listFiles

  //looking for files that end with some String
  def filesEnding(query: String) =
    for (file <- filesHere; if file.getName.endsWith(query))
      yield file
}

```

Looks good. Users can search for files that end with what they want. But later...

``` scala

//files who's name has some String we're interested in
def filesContaining(query: String) =
  for (file <- filesHere; if file.getName.contains(query))
    yield file

```

Still looking good. But later, we want something else

``` scala

//files by some regex
def filesRegex(query: String) =
  for (file <- filesHere; if file.getName.matches(query))
    yield file

```

There's a lot of duplication of logic going on here. Function values to the rescue

``` scala
def filesMatching(query: String,
    matcher: (String, String) => Boolean) = {

  for (file <- filesHere; if matcher(file.getName, query))
    yield file
}
```

Now we can just say:

``` scala

def filesEnding(query: String) =
  filesMatching(query, _.endsWith(_))

def filesContaining(query: String) =
  filesMatching(query, _.contains(_))

def filesRegex(query: String) =
  filesMatching(query, _.matches (_))

```

That's all nice, but notice that query gets passed to `filesMatching`, but `fileMatching` does nothing with the query except hand it off to the passed matcher function. The passing is unnecessary, b/c the caller knew the query to begin with. Let's factor out the query param from `filesMatching` and `matcher`.

``` scala

object FileMatcher {
  private def filesHere = (new java.io.File(".")).listFiles

  private def filesMatching(matcher: String => Boolean) =
    for (file <- filesHere; if matcher(file.getName))
      yield file

  def filesEnding(query: String) =
    filesMatching(_.endsWith(query))

  def filesContaining(query: String) =
    filesMatching(_.contains(query))

  def filesRegex(query: String) =
    filesMatching(_.matches(query))
}

```

**Simplify client code by by placing higher-order functions in your API**

``` scala

def containsNeg(nums: List[Int]): = nums.exists(_ <  0)

```

Here, the `exists` method is a control abstraction that is on `List`. It is a special purpose looping construct provided 
by the Scala library rather than being built into the language like while or for.

<a id="currying"></a>
### Currying 

We've touched on this previously, but currying helps us make control structures that feel more like native language support. 
Let's look at some examples.

``` scala

//I think we can see what this does
def plainSum(x: Int, y: Int) = x + y

//The same thing, but curried
def curriedSum(x: Int)(y: Int) = x + y

//curriedSum(1)(2) => 3

```

What you're getting with `curriedSum` is actually *two* traditional function invocations back to back. The first function 
takes an `Int` value named `x`, and returns a function value for the second function. The second takes an `Int` param `y`.  
Below you can see something we Java folks might find a little more familiar:

``` scala

def first(x: Int) = (y: Int) => x + y
//first: (Int)(Int) => Int

val second = first(1)
//second: (Int) => Int = <function>

second(2)
//Int = 3

```

These `first` and `second` just illustrate the process. They are not directly connected to the `curriedSum` function. We can get a reference to the `curriedSum`'s second arg by saying

``` scala

val plusOne = curriedSum(1)_

```

**Writing new control structures - easy as methods that take functions as args**
*Any time you find a control pattern repeating in your code, you should think about implementing it as a new control structure*

Consider the common pattern of: *open a resources, operate on it, and then close it* You can capture this logic in a control structure.

``` scala

def withPrintWriter(file: File, op: PrintWriter => Unit) {
  val writer = new PrintWriter(file)
  try {
    op(writer)
  } finally {
    writer.close()
  }
}

//then later you want to use it...
myCoolThing.withPrintWriter(
  new File("data.txt")
  writer => writer.println(new java.util.Date)
)

```

The cool thing here is that 'withPrintWriter' ensures that the resource is closed at the end! This technique is called 
the **loan pattern**, because a control-abstraction function, such as `withPrintWriter`, opens a resource and "loans" 
it to a function. For instance, `withPrintWriter` in the previous example loans a `PrintWriter` to the function, op. 
When the function completes, it signals that it no longer needs the "borrowed" resource. The resource is then closed 
in a finally block, to ensure it is indeed closed, regardless of whether the function completes by returning normally 
or throwing an exception.

To make it look nicer, Scala lets you wrap you args in `{ }` instead of `( )` for passing in **ONE** arg. Sadly our 
`withPrintWriter` method takes 2. But, does it have to? Let's revisit currying...

``` scala

def withPrintWriter(file: File)(op: PrintWriter => Unit) {
  val writer = new PrintWriter(file)
  try {
    op(writer)
  } finally {
    writer.close()
  }
}

```

Take the withPrintWriter method defined previously as an example. In its most recent form, withPrintWriter takes two arguments, so you can't use curly braces. Nevertheless, because the function passed to withPrintWriter is the last argument in the list, you can use currying to pull the first argument, the File, into a separate argument list. Now we can say:

``` scala

val file = new File("date.txt")

withPrintWriter(file) {
  writer => writer.prinln(new java.util.Date)
}

```

*the first argument list, which contains one `File` argument, is written surrounded by parentheses. The second argument 
list, which contains one function argument, is surrounded by curly braces.*

That's awesome, but what if you want to implement something more like if or while, however, where there is no value to 
pass into the code between the curly braces? To help with such situations, Scala provides **by-name** parameters.

``` scala

var assertionsEnabled = true

def myAssert(predicate: () => Boolean) =
  if (assertionsEnabled && !predicate())
    throw new AssertionError

//you can use it by calling...
myAssert( () => 5 > 3)

//but that's weird, let's change it up

def  byNameAssert(predicate: => Boolean) =
  if(assertionsEnabled && ! predicate)
    throw new AssertionError

//much better, now we can just say
byNameAssert( 5  > 3)

//you could also define
def boolAssert(predicate: Boolean) =
  if(assertionsEnabled && ! predicate)
    throw new AssertionError
//but this is less than ideal b/c the expression `5 > 3` will be eval'd before the call to boolAssert
//you can see it here:

var assertionsEnabled = false

boolAssert(x / 0 == 0)
// => java.lang.ArithmeticException: / by zero
//           at .<init>(<console>:8)
//          at .<clinit>(<console>)
//          at RequestResult$.<init>(<console>:3)
//          at RequestResult$.<clinit>(<console>)...

byNameAssert(x / 0 == 0)
//doesn't yield an error b/c the inner predicate is never eval'd, assertionsEnabled is false!

```

<a id="comp-and-inher"></a>
## Composition and Inheritance 
*Making a 2D layout library*

What we'll be working towards here is something like:

``` scala

val column1 = elem("hello") above elem("***")
val column2 = elem("***") above elem("world")
column1 beside column2

//hello ***
// *** world

```

#### Source available [here][ch10Source]

[ch10Source]: https://github.com/jimador/programming-in-scala/tree/master/src/main/scala/programming/in/scala/ch10

#### Abstract Classes

``` scala

abstract class

```

just like a Java `abstract class`, can have unimplemented (abstract) methods, and can not be instantiated.

``` scala

abstract class Element {
  def contents: Array[String]
  def height: Int = contents.length
  def width: Int = if (height == 0) 0 else contents(0).length
}

```

#### Parameterless methods

*parameterless methods* - methods like `height` are called *parameterless methods*, because they take no args. This is common in Scala.
Methods defined with an empty `()` are called *empty-paren methods*. (`height()`) The recommended convention is:

**use a parameterless method whenever there are no parameters AND the method accesses mutable state ONLY by reading fields
of the containing object (DOES NOT CHANGE MUTABLE STATE).**

This convention supports the _**Uniform Access Principle (UAP)**_, which says *which says that client code should not be affected
by a decision to implement an attribute as a field or method*

That seems a little convoluted to me, so let's look at an example. The *UAP* says that the client shouldn't care where the
values come from, so let's change our `height` and `width` to `val`

``` scala

abstract class Element {
  def contents: Array[String]
  val height: Int = contents.length
  val width: Int = if (height == 0) 0 else contents(0).length
}

```

The two definitions of `Element` should be functionally identical, and indeed they are. The only differences could be that
accessing the fields as a `val` should be faster because they are precomputed when the `class` is initialized. But, they
will require extra memory to store those `val`'s instead of lazily loading them on the fly.

**Note: ** *Java does not implement the uniform access principle. So Java declares string.length(), not string.length
and array.length, not array.length()).*

**The main point is the client SHOULD NOT CARE if the implementation changes**

Scala is very liberal when it comes to mixing parameterless and empty-paren methods. In particular, you can override a
parameterless method with an empty-paren method, and vice versa. You can also leave off the empty parentheses on an
invocation of any function that takes no arguments. e.x.

``` scala

Array(1, 2, 3).toString
"abc".length

```

#### Extending classes

Just like Java:

``` scala

class ArrayElement(conts: Array[String]) extends Element {
      def contents: Array[String] = conts
    }

```

A caveat: *If you leave out an extends clause, the Scala compiler implicitly assumes your class extends from `scala.AnyRef`,
which on the Java platform is the same as class `java.lang.Object`. Thus, class `Element` **implicitly** extends class `AnyRef`.*

Scala only has 2 namespaces, as opposed to Java's 4:

Java - _**fields**, **methods**, **types**, and **packages**_

Scala - _**types** (classes and trait names), and **values** (fields, methods, packages, and singleton objects)_

**Note: The reason Scala places fields and methods into the same namespace is precisely so you can override a parameterless method with a val.**

####Defining parametric fields

Notice in the `ArrayElement` class we have to define `contents`. This is redundant work. Since Scala defines fields and methods as *types*,
we can say:

``` scala

class ArrayElement(val contents: Array[String]) extends Element

```

we prefixed `contents` with `val`, but we could have used `var` or made it `private`, etc. Take a look at:

``` scala

class Cat {
  val dangerous = false
}
class Tiger(
  override val dangerous: Boolean,
  private var age: Int
) extends Cat

```

Tiger's definition here is short-hand for:

``` scala

//param1 and param2 are, of course, arbitrary names. Just make sure they don't clash with other names in the scope!
class Tiger(param1: Boolean, param2: Int) extends Cat {
  override val dangerous = param1
  private var age = param2
}

```

#### Calling a superclass constructor

Pass the arg(s) to the superclass

```` scala

class LineElement(s: String) extends ArrayElement(Array(s)) {
  override def width = s.length
  override def height = 1
}

```

#### Overriding

*The modifier is optional if a member implements an abstract member with the same name. The modifier is forbidden if a
member does not override or implement some other member in a base class.*

**Problems: The fragile base class** -  *if you add new members to base classes (which we usually call superclasses) in
a class hierarchy, you risk breaking client code.* If you add a method to a superclass, and there is no override
modifier on a subclass for a method with that same name, Scala will give a compiler error.

#### Dynamicly Bound Methods

*method invocations on variables and expressions are dynamically bound. This means that the actual method implementation
invoked is determined at run time based on the class of the object, not the type of the variable or expression.*

#### *final* members

* If you declare a *value* final, a subclass can not override it.
* A *final* class may not have a subclass

<a id="traits"></a>
## Traits 

Defined using the keyword `Trait`

``` scala

// traits have the default superclass of `AnyRef` just like classes
trait Philosophical {
  def philosophize() {
    println("I consume memory, therefore I am!")
  }
}

```

A trait encapsulates method and field definitions, which can then be reused by mixing them into classes.
Unlike class inheritance, in which each class must inherit from just one superclass, a class can mix in any number of traits.
Once a trait is defined, it can be mixed in to a class using either the extends or with keywords. Generally we "mix in"
traits rather than inherit from them, because mixing in a trait has important differences from the multiple
inheritance found in many other languages. Mix in traits with the `extends` keyword and use multiple traits with the `with`
keyword.

``` scala

// a single trait `extends`
class Frog extends Philosophical {
  override def toString = "green"
}

// multiple traits `extends` and `with`
class Animal
  trait HasLegs

class Frog extends Animal with Philosophical with HasLegs {
  override def toString = "green"
}

```

*Traits* are not just "*Java interfaces with concrete methods*". They can also declare fields and maintain state. Traits
can contain anything a class definition can, with 2 exceptions:

1. They can not take class parameters. `trait ThisDoesntWork(x: Int, y: Int)`
2. Super calls are dynamically bound. If you call `super.toString` in a `trait` the call to `toString` is determined
 each time the trait is mixed into a concrete class. This behavior allows traits to work as a *stackable modifications*.

#### Stackable modifications to a class

Let's say you want to make a queue of Integers. The queue has 2 methods `put` and `get`

``` scala

abstract class IntQueue {
  def get(): Int
  def put(x: Int)
}


// somewhere else in the code base we declare
import scala.collection.mutable.ArrayBuffer

class BasicIntQueue extends IntQueue {
  private val buf = new ArrayBuffer[Int]
  def get() = buf.remove(0)
  def put(x: Int) { buf += x }
}
```

Looks nice enough. Now let's say that we wanted some optional modifications to our Queue. Depending on the situation, we might
want an `IntQueue` that can Double the values in it, Increment the values in it, or Filter values out of it. We can do this with
Traits. We can define:

```scala

// Double
trait Doubling extends IntQueue {
  abstract override def put(x: Int) { super.put(2 * x) }
}

// Inc
trait Incrementing extends IntQueue {
  abstract override def put(x: Int) { super.put(x + 1) }
}

// Filter
trait Filtering extends IntQueue {
  abstract override def put(x: Int) {
    if (x >= 0) super.put(x)
  }
}

// now later in the code, we can say...
val queue = new BasicIntQueue with Doubling //WAT? This is awesome

// the order is significant with mixins. Here Incrementing will happen first
val queue = (new BasicIntQueue
             with Filtering with Incrementing)

```

**Basic Guidelines for using traits**

  * If the behavior will not be reused - make it a concrete class
  * If the behavior will be reused across multiple, unrelated, classes - make it a trait
  * If you want to inherit from it in Java code - use an abstract class
  * If you plan to distribute it in compiled for, and you expect outside groups to write classes inheriting from it -
    lean towards using an abstract class. *if a trait gains or loses a member, it must be recompiled even if the have
    not changed*. If clients will only call into it, trait is fine.
  * If efficiency is very important, use a class. Invoking a virtual method on a class is usually faster on a class in
    Java.
  * If you don't know what to make it, start off with a trait. It has the most flexibility, and you can always change it
    later.

<a id="packages-imports"></a>
## Packages and Imports 

Scala allows for 2 types of package declaration:

Java Style:

``` scala

package com.foo.bar
class Baz

```

and a more C/#-esque namespace convention:

``` scala

//syntactic sugar for the nested form (mixing Java and C# styles)
package com.foo {
  package bar {

    // in package com.foo.bar
    class Baz

    package tests {

      //in package com.foo.bar.tests
      class BazSuite
    }
  }
}

```

the main difference between Scala and Java here is that Scala packages "truly" nest.

In Java, you might have 2 different classes `com.foo.bar.Baz` and `com.foo.bar.Qux`, and you would have to declare the full
package structure in each class. e.g.

``` java
package com.foo.bar.baz;

public class Biz {
  // epic codez
  ...
}

```

----

``` java
package com.foo.bar.qux;

public class Quux {
    // legendary javas
    ...
}

```

Where as in Scala, you can say

``` scala

package foo.bar {
  package baz {
    class Missile
  }
  package qux {
    class Launch {
      // No need to five fully qualified name (`foo.bar.baz.Missile`)
      val nav = new baz.Missile
    }
  }
}

```

This makes it easier to refer to packages that have the same name, in the same tree, too.

``` scala

package baz {
  class Missile3
}

package foo {
  bar {
    package baz {
      class Missile1
    }
    package qux {
      class Launch {
        // No need to five fully qualified name (`foo.bar.baz.Missile`)
        val missileOne = new baz.Missile1
        val missileTwo = new foo.baz.Missile2
        val missileThree = new _root_.baz.Missile3
      }
    }
  }
  package baz {
    class Missile2
  }
}

```

**Import clauses**, Scala has these too

``` scala
// access Missile 3
import baz.Missile3

// access all of foo
import foo._

// all members of foo.bar
import foo.bar._

/**
  * Scala imports can appear anywhere!
  */
def fireTheMissiles(target: Target) {
  import foo.bar._
  target.killAllWith(new Missile1)
}

```

> #### Scala's flexible imports

> Scala's import clauses are quite a bit more flexible than Java's. There are three principal differences. In Scala, imports:

> * may appear anywhere
> * may refer to objects (singleton or regular) in addition to packages
> * let you rename and hide some of the imported members

also import Java packages!

``` scala
import java.util.regex

class AStarB {
  // Accesses java.util.regex.Pattern
  val pat = regex.Pattern.compile("a*b")
}

// Don't stop there though. You can rename or hide members with the import selector
// enclosed in braces
import baz.{Missle3 => MegaNuke}

// import and rename a package
import java.{sql => S}

// given:
abstract class Fruit(
  val name: String,
  val color: String
)

// and:
object Fruits {
  object Apple extends Fruit("apple", "red")
  object Orange extends Fruit("orange", "orange")
  object Pear extends Fruit("pear", "yellowish")
  val menu = List(Apple, Orange, Pear)
}

// you can import all of the members of an object like...
import Fruit.{_}

// same as saying
import Fruit._

// import all members from Fruit, but rename apple to McIntosh
import Fruits.{Apple => McIntosh, _}

// import everything BUT pear
import Fruits.{Pear => _, _}

//This is useful if you have naming collisions, like an Apple notebook and Fruit
import Notebooks._
import Fruits.{Apple => _, _}


```

> ##### In summary, a selector can consist of the following:

> * A simple name x. This includes x in the set of imported names.
> * A renaming clause x => y. This makes the member named x visible under the name y.
> * A hiding clause x => _. This excludes x from the set of imported names.
> * A catch-all `_'. This imports all members except those members mentioned in a preceding clause. If a catch-all is given, it must come last in the list of import selectors

Scala has some implicit imports, like Java

``` scala

//included for you
import java.lang._ // everything in the java.lang package
import scala._     // everything in the scala package
import Predef._    // everything in the Predef object

```

<a id="access-modifiers"></a>
## *Access Modifiers* 

1. [Protection Scoping](#protection-scoping)
2. [Visibility](#visibility)

#### *Private*

``` scala

class Outer {
class Inner {
  private def f() { println("f") }
    class InnerMost {
      f() // OK
    }
  }
  (new Inner).f() // error: f is not accessible
}

```

#### *Protected*

``` scala

package p {
  class Super {
    protected def f() { println("f") }
  }
  class Sub extends Super {
    f()
  }
  class Other {
    (new Super).f()  // error: f is not accessible
  }
}

```

#### *Public* - default for Scala

Everything is public unless you specify otherwise

<a id="protection-scoping"></a>
### Protection Scoping 

Access modifiers in Scala can be augmented with qualifiers. A modifier of the form private\[X\] or protected\[X\] means
that access is private or protected "up to" X, where X designates some enclosing **package**, **class** or **singleton object**.

``` scala

package bobsrockets {
  package navigation {

    // private to package bobsrockets
    private[bobsrockets] class Navigator {

      // accessible in all subclasses of Navigator and in all code contained in
      // the enclosing package 'navigation' exactly like Java's 'protected' modifier
      protected[navigation] def useStarChart() {}

      class LegOfJourney {

        // private to the Navigator class. It is visible from everywhere in the
        // Navigator class
        private[Navigator] val distance = 100

      }

      // most restrictive. Access restricted not just on from Navigator, but
      // it must also be made from the very same instance of Navigator.
      private[this] var speed = 200

    }
  }
  package launch {
    import navigation._
    object Vehicle {
      // private to 'launch'
      private[launch] val guide = new Navigator
    }
  }
}

```

The access to `Navigator` in object `Vehicle` is permitted, because `Vehicle` is contained in package `launch`,
which is contained in `bobsrockets`. On the other hand, all code outside the package `bobsrockets` cannot access class
`Navigator`.

<a id="visibility"></a>
### Visibility and companion objects 

In Java static members and instance members belong to the same class, so access modifiers apply uniformly to them. Scala
doesn't have static members, but it does have companion objects that contain members that only exist once. e.g.
the `object Rocket` is a companion of `class Rocket`

``` scala

class Rocket {
  import Rocket.fuel
  private def pnr = fuel > 20
}

object Rocket {
  private def fuel = 10
  def chooseStrategy(rocket: Rocket) {
    if (rocket.pnr)
      goHome()
    else
      pickAStar()
  }
  def goHome() {}
  def pickAStar() {}
}

```

Scala's access rules privilege companion objects and classes when it comes to private or protected accesses. A class
shares all its access rights with its companion object and vice versa. In particular, an object can access all private
members of its companion class, just as a class can access all private members of its companion object.

**Note**: One exception where the similarity between Scala and Java breaks down concerns protected static members.
A protected static member of a Java class C can be accessed in all subclasses of C. By contrast, a protected member in
a companion object makes no sense, as singleton objects don't have any subclasses.
