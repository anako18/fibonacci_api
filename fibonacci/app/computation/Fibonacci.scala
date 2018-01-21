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
  def fib_compute(n: Int) : Unit = {
    if (cashed_elems.cnt > n) return
    var a : Long = cashed_elems.elems(cashed_elems.cnt-2) 
    var b : Long = cashed_elems.elems(cashed_elems.cnt-1)
    var i = cashed_elems.cnt-1	  
 
    while( i < n-1 ) {
      var c : Long = a + b
      cashed_elems.elems+=c
      a = b
      b = c
      i = i + 1
    } 
  }

  //returns a list with N first elems
  def take_n_elems(n: Int) : ListBuffer[Long] = cashed_elems.elems.take(n)

}
