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
  def demo() {
    println("Element's implementation invoked")
  }

  def above(that: Element): Element =
    new ArrayElement(this.contents ++ that.contents)

  def beside(that: Element): Element =
    new ArrayElement(
      for (
        (line1, line2) <- this.contents zip that.contents
      ) yield line1 + line2
    )

  override def toString = contents mkString "\n"
}

object Element {
  def elem(contents: Array[String]): Element =
    new ArrayElement(contents)

  def elem(chr: Char, width: Int, height: Int): Element =
    new UniformElement(chr, width, height)

  def elem(line: String): Element =
    new LineElement(line)
}