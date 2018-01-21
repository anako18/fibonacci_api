package computation

import javax.inject._
import play.api._
import play.api.mvc._
import scala.collection.mutable.ListBuffer


@Singleton 
class Fibonacci{

  object cashed_elems{
    var elems = ListBuffer[Long](1,1)
    def cnt = elems.length //count of cached elems
  }

  //main computation function
  def fib_compute(n: Int, from : Int) : Long = {
    var a : Long = cashed_elems.elems(from-2) 
    var b : Long = cashed_elems.elems(from-1)
    var i = from-1	  
 
    while( i < n-1 ) {
      var c : Long = a + b

      cashed_elems.elems+=c

      a = b
      b = c
      i = i + 1
    } 
    a = b
    a
  }

  //creates a string to print
  def print_n(n: Int) : String = {
    var s = ""
    for (i <- 0 to n-2) {
      s = s + cashed_elems.elems(i) + ", "
    }
    s = s + cashed_elems.elems(n-1) + "."
    s
  }  

}
