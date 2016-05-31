package programming.in.scala.ch19.queue

/**
  * Created by james on 5/31/2016.
  *
  * The Queue constructor, which is globally accessible, takes two lists as parameters,
  * where one is reversed—hardly an intuitive representation of a queue.
  * What's needed is a way to hide this constructor from client code.
  */

/**
  * FunctionalQueue2 is a trait that reveals the public interface of the class.
  * it declares 3 methods: head, tail, and append.
  * All three methods are implemented in a subclass QueueImpl
  * @tparam T
  */
trait FunctionalQueue2[T] {
  def head: T
  def tail: FunctionalQueue2[T]
  def append(x: T): FunctionalQueue2[T]
}

/**
  * The companion object
  */
object FunctionalQueue2 {

  def apply[T](xs: T*): FunctionalQueue2[T] =
    new FunctionalQueue2Impl[T](xs.toList, Nil)

  /**
    * The actual implementation, hidden from the world.
    * @param leading
    * @param trailing
    * @tparam T
    */
  private class FunctionalQueue2Impl[T](
    private val leading: List[T],
    private val trailing: List[T]
  ) extends FunctionalQueue2[T] {

    def mirror =
      if (leading.isEmpty)
        new FunctionalQueue2Impl(trailing.reverse, Nil)
      else
        this

    def head: T = mirror.leading.head

    def tail: FunctionalQueue2Impl[T] = {
      val q = mirror
      new FunctionalQueue2Impl(q.leading.tail, q.trailing)
    }

    def append(x: T) =
      new FunctionalQueue2Impl(leading, x :: trailing)
  }
}