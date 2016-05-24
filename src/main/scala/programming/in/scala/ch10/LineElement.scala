package programming.in.scala.ch10

/**
  * Created by james on 5/24/2016.
  *
  * Since LineElement extends ArrayElement, and ArrayElement's constructor takes a parameter (an Array[String]),
  * LineElement needs to pass an argument to the primary constructor of its superclass. To invoke a superclass
  * constructor, you simply place the argument or arguments you want to pass in parentheses
  * following the name of the superclass.
  */
class LineElement(s: String) extends Element {
  val contents = Array(s)
  // notice both of these methods are parameterless. This is common in Scala
  override def height: Int = 1
  override def width: Int = s.length

  override def demo() {
    println("LineElement's implementation invoked")
  }
}
