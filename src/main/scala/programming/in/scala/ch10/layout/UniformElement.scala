package programming.in.scala.ch10.layout

/**
  * Created by james on 5/24/2016.
  */
class UniformElement(ch: Char,
                     override val width: Int,
                     override val height: Int
                    ) extends Element {
  private val line = ch.toString * width
  def contents = Array(height.toString, line)
}
