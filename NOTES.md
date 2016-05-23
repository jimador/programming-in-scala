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

## Collections <a id="collections"></a>
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


### Arrays <a id="arrays"></a>
``` scala
val greetStrings = new Array[String](3)
greetStrings(0) = "Hello"
greetStrings(1) = ", "
greetStrings(2) = "world!\n"
```

Explicitly declare the type:

``` scala
val greetString: Array[String] = new Array[String](3)

val numNames = Array("zero", "one", "two")
```

What is is actually doing is calling a method call apply on the Array companion object that returns a new array (think static method)

``` scala
val numNames2 = Array.apply("zero", "one", "two")
```

### Lists <a id="lists"></a>
| Common List Operations                                           | Explanation                          |
| -----------------------------------------------------------------| ------------------------------------ |
| `List()` or `Nil`	                                                   | The empty List
| `List("Cool", "tools", "rule")`                                    | new List |
| `::`	                                                           | Cons operator |
| `:::`	                                                           | Cons 2 lists |
| `aList(2)`	                                                       | element at index 2 (zero based) |
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


### Tuples <a id="tuples"></a>

The actual type of a tuple depends on the number of elements it contains and the types of those elements. Thus, the type of (99, "Luftballons")
is Tuple2\[Int, String]. The type of ('u', 'r', "the", 1, 4, "me") is Tuple6\[Char, Char, String, Int, Int, String]

### Sets <a id="sets"></a>

Although the default set implementations produced by the mutable and immutable Set factory methods shown thus far will
likely be sufficient for most situations, occasionally you may want an explicit set class. Fortunately, the syntax is
similar. Simply import that class you need, and use the factory method on its companion object. For example, if you
need an immutable HashSet, you could do this:

``` scala
import scala.collection.immutable.HashSet

val hashSet = HashSet("Tomatoes", "Chilies")
println(hashSet + "Coriander")
```

### Maps <a id="maps"></a>

``` scala
import scala.collection.mutable.Map

val treasureMap = Map[Int, String]()
treasureMap += (1 -> "Go to island.")
treasureMap += (2 -> "Find big X on ground.")
treasureMap += (3 -> "Dig.")
```

## Functional Style <a id="fp"></a>

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

## Classes and Objects <a id="classes"></a>

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
public
private
protected
default
```

### Methods <a id="methods"></a>
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

##### One puzzler to watch out for is that whenever you leave off the equals sign before the body of a function, its result type will definitely be Unit.

##### `;` is optional. Scala will infer it.

>The rules of semicolon inference

>1. The precise rules for statement separation are surprisingly simple for how well they work. In short, a line ending is treated as a semicolon unless one of the following conditions is true:
>2. The line in question ends in a word that would not be legal as the end of a statement, such as a period or an infix operator.
>3. The next line begins with a word that cannot start a statement.
>4. The line ends while inside parentheses (...) or brackets [...], because these cannot contain multiple statements anyway.

### Singleton objects
##### classes in Scala cannot have static members. Instead, Scala has singleton objects. A singleton object definition looks like a class definition, except instead of the keyword class you use the keyword `object`.

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

The singleton object in this figure is named ChecksumAccumulator, the same name as the class in the previous example. When a singleton object shares the same name with a class, it is called that class's companion object. You must define both the class and its companion object in the same source file. The class is called the companion class of the singleton object. A class and its companion object can access each other's private members.
One way to think of singleton objects is as the home for any static methods you might have written in Java


## Operator Overloading <a id="op-overloading"></a>
##### Yes, you can overload operators. Only do it where it would make sense to do it.
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

## Control Structures <a id="control-structures"></a>

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

## Exceptions <a id="exceptions"></a>

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

you can yield a value from a catch

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

## Match Expressions <a id="match-exp"></a>
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

###### the Scala compiler will tail-call optimize this recursive code

## Functions and Closures <a id="func-and-closures"></a>
  * [Local functions](#local-func)
  * [First-class functions](#first-class-func)
  * [Placeholder syntax](#placeholder)
  * [Repeated parameters](#repeated-params)
  * [Tail recursion](#tail-rec)



### Local functions <a id="local-func"></a>
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
Here, `processLine` is scoped to inside `processFile` and no one else can access it. But we can do better. Since `width` and `fileName` are scoped to `processFile` we don't need to pass those in. A child def has access to it's parent's scope.

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

### First-class functions <a id="first-class-func"></a>
Scala has first-class functions. A function literal is compiled into a class that when instantiated at runtime is a function value. The difference is, literals exist in the source code and function values
are objects at runtime. Example literal:

``` scala
(x: Int) => x + 1
```

The `=>` designates that this function converts the thing on the left (any integer x) to the thing on the right (x + 1). So, this is a function mapping any integer x to x + 1.
For multi-line functions surround with `{ }`
``` scala
increase = (x: Int) => {
   println("We")
   println("are")
   println("here!")
   x + 1
}
```

### Placeholder syntax `_` <a id="placeholder"> </a>
To make a function literal even more concise, you can use underscores as placeholders for one or more parameters, so long as each parameter appears only one time within the function literal.

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
now we can say `a(1,2,3)` and we get `Int = 6`. The Scala compliler instantiated a function value that takes the 3 integer parameters missing from the partially applied function expression, `sum _`, and assigns a reference to that new function value to the variable a. The variable name `a` refers to a function value object that is generated by the Scala compliler from the partially applied function expression, `sum _`.the compiler translates the expression `a(1,2,3)` into an invocation of the function value's `apply` method. The `apply` method generated takes 3 args b/c 3 is the number of missing args in the `sum _` expression. The Scala compiler translates the expression `a(1, 2, 3)` into an invocation of the function value's `apply` method, passing in the three arguments 1, 2, and 3. Thus, `a(1, 2, 3)` is a short form for

`a.apply(1, 2, 3)`

This apply method, defined in the class generated automatically by the Scala compiler from the expression `sum _`, simply forwards those three missing parameters to `sum`, and returns the result. In this case apply invokes `sum(1, 2, 3)`, and returns what sum returns, which is 6. Here, the `_` is used to represent the enitre parameter list. Now, although `sum _` is indeed a partially applied function, it may not be obvious to you why it is called this. It has this name because you are not applying that function to all of its arguments. In the case of `sum _`, you are applying it to none of its arguments. But you can also express a partially applied function by supplying some but not all of the required arguments. Here's an example:
`val b = sum(1, _: Int, 3)`
`b: (Int) => Int = <function>`
Now we have a partially applied function, `b` that is only missing the middle argument

If you are writing a partially applied function expression in which you leave off all parameters, such as println _ or sum _, you can express it more concisely by leaving off the underscore if a function is required at that point in the code.
`someNumbers.foreach(println)`

### Closures <a id="closures"></a>

So far we've only looked at function literals that refer to passed in parameters. You can also refer to variables defined elsewhere.
``` scala
var more = 1
// ... a little ways does the source
val addMore = (x: Int) => x + more
// results in `addMore: (Int) => Int = <function>`
addMore(10)
```

The function value (the object) that's created at runtime from this function literal is called a closure. The name arises from the act of "closing" the function literal by "capturing" the bindings of its free variables. A function literal with no free variables, such as `(x: Int) => x + 1`, is called a closed term, where a term is a bit of source code. Thus a function value created at runtime from this function literal is not a closure in the strictest sense, because `(x: Int) => x + 1` is already closed as written. But any function literal with free variables, such as `(x: Int) => x + more`, is an open term. Therefore, any function value created at runtime from `(x: Int) => x + more` will by definition require that a binding for its free variable, more, be captured. The resulting function value, which will contain a reference to the captured more variable, is called a closure, therefore, because the function value is the end product of the act of closing the open term, `(x: Int) => x + more`.
**Scala's closures capture variables themselves, not the value to which variables refer.**

What if a closure accesses some variable that has several different copies as the program runs? For example, what if a closure uses a local variable of some function, and the function is invoked many times? Which instance of that variable gets used at each access? **the instance used is the one that was active at the time the closure was created.**

Each time this function is called it will create a new closure. Each closure will access the more variable that was active when the closure was created.
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

### Repeated parameters <a id="repeated-params"></a>

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

### Tail recursion <a id="tail-rec"></a>

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

**now it is**
Scala only optimizes directly recursive calls back to the same function making the call. If the recursion is indirect, as in the following example of two mutually recursive functions, no optimization is possible:

``` scala
def isEven(x: Int): Boolean =
    if (x == 0) true else isOdd(x - 1)
def isOdd(x: Int): Boolean =
  if (x == 0) false else isEven(x - 1)
```
You also won't get a tail-call optimization if the final call goes to a function value. Consider for instance the following recursive code:

``` scala
val funValue = nestedFun _
def nestedFun(x: Int) {
  if (x != 0) { println(x); funValue(x - 1) }
}
```
The funValue variable refers to a function value that essentially wraps a call to nestedFun. When you apply the function value to an argument, it turns around and applies nestedFun to that same argument, and returns the result. You might hope, therefore, the Scala compiler would perform a tail-call optimization, but in this case it would not. Thus, tail-call optimization is limited to situations in which a method or nested function calls itself directly as its last operation, without going through a function value or some other intermediary.

If you still don't get it, see [Tail recursion](#tail-rec)
