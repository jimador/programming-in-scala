package programming.in.scala.ch10

/**
  * Created by james on 5/24/2016.
  */
abstract class Element {
  // notice, no body, therefore abstract
  def contents: Array[String]

  // notice both of these methods are parameterless. This is common in Scala
  def height: Int = contents.length
  def width: Int = if(height == 0) 0 else contents(0).length
}
