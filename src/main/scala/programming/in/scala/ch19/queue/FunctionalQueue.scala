package programming.in.scala.ch19.queue

/**
  * Created by james on 5/31/2016.
  *
  * Representing a queue by two lists, called leading and trailing.
  * The contents of the whole queue are at each instant equal to "leading ::: trailing.reverse".
  * When an initially empty queue is constructed from successive append operations,
  * the trailing list will grow whereas the leading list will stay empty.
  */

// to hide the constructor, we can make it private
class FunctionalQueue[T] private (
  // elements towards the front
  private val leading: List[T],
  // elements towards the back
  private val trailing: List[T]
) {

  private def mirror =
    if (leading.isEmpty)
      new FunctionalQueue(trailing.reverse, Nil)
    else
      this

  def head = mirror.leading.head

  def tail = {
    val q = mirror
    new FunctionalQueue(q.leading.tail, q.trailing)
  }

  // to append an element, cons it to the trailing list, so append is constant time.
  def append(x: T) =
    new FunctionalQueue(leading, x :: trailing)

  // now that the constructor is private, we can make new constructors.
  // an empty one...
  def this() = this(Nil,Nil)

  // one that takes in some elements
  def this(xs: T*) = this(xs.toList, Nil)

}

/**
  * Even better than private constructors, we can make a companion obect to do our
  * making for us.
  */
object FunctionalQueue {
  // constructs a queue with initial elements `xs'
  def apply[T](xs: T*) = new FunctionalQueue[T](xs.toList, Nil)
}