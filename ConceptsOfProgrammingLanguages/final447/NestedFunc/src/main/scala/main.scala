object NestedFunc {
  def foo1 [X] (xs:List[X]) : List[X] = {
    def aux (us:List[X], vs:List[X]) : List[X] = {
      us match {
        case Nil => vs
        case w::ws => aux (ws, w::vs)
      }
    }
    aux (xs, Nil)
  }

  def foo2 [X] (xs:List[X], k:X=>X) : List[X] = {
    def aux (us:List[X], vs:List[X]) : List[X] = {
      us match {
        case Nil => vs
        case w::ws => aux (ws, (k (w)) :: vs)
      }
    }
    aux (xs, Nil)
  }

  def foo3 [X] (xs:List[X]) : Int=>List[X] = {
    def aux (n:Int) : List[X] = {
      if (n <= 0) {
        Nil
      } else {
        xs ::: aux (n - 1)
      }
    }
    aux
  }

  def foo4 [X] (xs:List[X]) : Unit = {
    xs.foreach ((x:X) => println (x))
  }

  def foo5 (xs:List[Int]) : Int = {
    xs.foldLeft (0) ((x:Int,y:Int) => x + y)
  }

  def foo6 (xs:List[Int]) : Int=>Int = {
    (n:Int) => (xs.foldLeft (0) ((x:Int,y:Int) => x + y))
  }

  var f : Int=>Int = x=>x
  def foo7 (x:Int) : Unit = {
    f = ((y:Int) => x+y)
  }
  
  def main(args: Array[String]): Unit = {
    println(foo1(List(1,2,3)))
    println(foo2(List(1,2,3),(x: Int) => x * 2))
    println(foo3(List(1,2,3)))
    println(foo4(List(1,2,3)))
    println(foo5(List(1,2,3)))
    println(foo6(List(1,2,3)))
    println(foo7(7))
  }
}