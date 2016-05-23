>#Notes for *Programming in Scala, 1st Ed*
***

# Contents
1. [Collections](#collections)
2. [Functional Style](#fp)

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