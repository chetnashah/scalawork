// +A means covariant
//data type is polymorphic by (type parameter) A, also called generics
sealed trait List[+A] // List data type parametrized on type, A, a list of As
case object Nil extends List[Nothing] // List data constructor empty list
case class Cons[+A](head: A, tail: List[A]) extends List[A]

//recursive Sum type : List = Nil | Cons A::List

//companion object
object List{

  //recursive static factory to actually make shit,
  // parametrized on type param A
  def apply[A](as: A*): List[A] = {
    if(as.isEmpty) Nil
    else Cons(as.head, apply(as.tail: _*))
  }

  // a tail is a function that takes a list and returns tail list
  def tail[A](xs: List[A]): List[A] = {
    xs match {
      case Nil => sys.error("Cannot tail empty list")
      case Cons(_,t) => t
    }
  }

  // length is num of elements in list

  //foldRight

  //concat : flattens list of lists to List

  // append : append one list to end of another

  // map & filter

  //flat map

  //filterviaflatmap

  //zipwith

  //startswith

  //hasSubsequence

  // both have same serialization
  val example = Cons(1, Cons(2, Cons(3, Nil))) // made by explicit case classes
  val example2 = List(1,2,3) // made by static factory of companion

  println(example)
  println(example2)

}
