>#Notes for *Programming in Scala, 1st Ed*
***

# Contents
1. [Collections](#collections)
2. [Functional Style](#fp)
3. [Classes and Objects](#classes)

##Collections <a id="collections"></a>
  * [Arrays](#arrays") - Mutable sequence of objects that are all the same type
    * `val array = new Array[Int](0,1,2)`
  * [Lists](#lists) - Immutable sequence of objects that are all the same type
    * `val list = List(1,2,3)`
    * you cons onto a list because appending is linear and cons'ing is constant
  * [Tuples](#tuples)
    * `val pair = (99, "Luft Ballons")`
    * immutable
    * can contain different types
    * access elements w/ ._1, ._2, ... ._N (one indexed)
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
            - `val hashmap = HashMap(1->"one", 2->"two")`
    * immutable by default
    * `+` or `+=` to add


### Arrays <a id="arrays"></a>

    val greetStrings = new Array[String](3)
    greetStrings(0) = "Hello"
    greetStrings(1) = ", "
    greetStrings(2) = "world!\n"

Explicitly declare the type:

    val greetString: Array[String] = new Array[String](3)

    val numNames = Array("zero", "one", "two")

What is is actually doing is calling a method call apply on the Array companion object that returns a new array (think static method)

    val numNames2 = Array.apply("zero", "one", "two")


### Lists <a id="lists"></a>
| Common List Operations                                           | Explanation                          |
| -----------------------------------------------------------------| ------------------------------------ |
| List() or Nil	                                                   | The empty List
| List("Cool", "tools", "rule")                                    | new List |
| ::	                                                           | Cons operator |
| :::	                                                           | Cons 2 lists |
| aList(2)	                                                       | element at index 2 (zero based) |
| count(s => s.length == 4)                                        | Counts the number of string elements in length 4 |
| drop(2)	                                                       | Returns the list without its first 2 elements |
| dropRight(2)	                                                   | Returns the list without its rightmost 2 elements |
| exists(s => s == "until")	                                       | Determines whether a string element exists in that has the value "until" returns Boolean |
| filter(s => s.length == 4)	                                   | Returns a list of all elements, in order,  that have length 4 |
| forall(s => s.endsWith("l"))           	                       | Indicates whether all elements in the thrill list end with the letter "l" (returns true) |
| foreach(s => print(s))	                                       | Executes the print statement on each of the strings in the list |
| foreach(print)	                                               | Same as the previous |
| head	                                                           | Returns the first element in the list |
| init	                                                           | Returns a list of all but the last element in the list |
| isEmpty	                                                       | Indicates whether the list is empty, Boolean |
| last	                                                           | Returns the last element in the thrill list |
| length	                                                       | Returns the number of elements in the list |
| map(s => s + "y")	                                               | Returns a list resulting from adding a "y" to each string element in the list |
| mkString(", ")	                                               | Makes a string with the elements of the list |
| remove(s => s.length == 4)	                                   | Returns a list of all elements, in order, of the list except those that have length 4 |
| reverse	                                                       | Returns a list containing all elements of the list in reverse order |
| sort((s, t) => s.charAt(0).toLowerCase < t.charAt(0).toLowerCase)| Returns a list containing all elements of the thrill list in alphabetical order of the first character lowercased |
| tail	                                                           | Returns the list minus its first element |


### Tuples <a id="tuples"></a>

The actual type of a tuple depends on the number of elements it contains and the types of those elements. Thus, the type of (99, "Luftballons")
is Tuple2\[Int, String]. The type of ('u', 'r', "the", 1, 4, "me") is Tuple6\[Char, Char, String, Int, Int, String]

### Sets <a id="sets"></a>

Although the default set implementations produced by the mutable and immutable Set factory methods shown thus far will
likely be sufficient for most situations, occasionally you may want an explicit set class. Fortunately, the syntax is
similar. Simply import that class you need, and use the factory method on its companion object. For example, if you
need an immutable HashSet, you could do this:

    import scala.collection.immutable.HashSet

    val hashSet = HashSet("Tomatoes", "Chilies")
    println(hashSet + "Coriander")

### Maps <a id="maps"></a>

    import scala.collection.mutable.Map

    val treasureMap = Map[Int, String]()
    treasureMap += (1 -> "Go to island.")
    treasureMap += (2 -> "Find big X on ground.")
    treasureMap += (3 -> "Dig.")

## Functional Style <a id="fp"></a>

### File reading example

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

## Classes and Objects <a id="classes"></a>

    class ChecksumAccumulator {
        var sum = 0
        //stuff
    }

create an instance with `new`

    val acc = new ChecksumAccumulator

you can access the `sum` field with the `.`

    acc.sum

like Java, there are access modifiers

    public
    private
    protected
    default

### Methods <a id="methods"></a>
##### method parameters are vals not vars

    class ChecksumAccumulator {
        private var sum = 0

        def add(b: Byte): Unit = {
          sum += b
        }

        def checksum(): Int = {
          return ~(sum & 0xFF) + 1
        }
    }

#####  Because all checksum does is calculate a value, it does not need an explicit return.

    class ChecksumAccumulator {
        private var sum = 0
        def add(b: Byte): Unit = sum += b
        def checksum(): Int = ~(sum & 0xFF) + 1
      }

##### One puzzler to watch out for is that whenever you leave off the equals sign before the body of a function, its result type will definitely be Unit.

##### `;` is optional. Scala will infer it.

>The rules of semicolon inference

>1. The precise rules for statement separation are surprisingly simple for how well they work. In short, a line ending is treated as a semicolon unless one of the following conditions is true:
>2. The line in question ends in a word that would not be legal as the end of a statement, such as a period or an infix operator.
>3. The next line begins with a word that cannot start a statement.
>4. The line ends while inside parentheses (...) or brackets [...], because these cannot contain multiple statements anyway.

### Singleton objects
##### classes in Scala cannot have static members. Instead, Scala has singleton objects. A singleton object definition looks like a class definition, except instead of the keyword class you use the keyword `object`.
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

The singleton object in this figure is named ChecksumAccumulator, the same name as the class in the previous example. When a singleton object shares the same name with a class, it is called that class's companion object. You must define both the class and its companion object in the same source file. The class is called the companion class of the singleton object. A class and its companion object can access each other's private members.
One way to think of singleton objects is as the home for any static methods you might have written in Java
