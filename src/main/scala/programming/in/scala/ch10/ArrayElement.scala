package programming.in.scala.ch10

/**
  * Created by james on 5/24/2016.
  *
  * @param contents is an example of a parametric field definition
  */
class ArrayElement(val contents: Array[String]) extends Element {
  override def demo() {
    println("ArrayElement's implementation invoked")
  }

  // ++ is Array concat
  def above(that: Element): Element =
    new ArrayElement(this.contents ++ that.contents)

  def beside(that: Element): Element = new ArrayElement(
    for (
      (line1, line2) <- this.contents zip that.contents
    ) yield line1 + line2
  )

  override def toString = contents mkString "\n"
}
