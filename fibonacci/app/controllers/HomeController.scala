package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import scala.collection.mutable.ListBuffer

object cashed_elems{
  var elems = ListBuffer[Long](1,1)
  def cnt = elems.length //count of cached elems
}

@Singleton
class HomeController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {
																																																			
  def fib_sequence(count: Int) = Action {
    var res = if (count > cashed_elems.cnt) fib(count, cashed_elems.cnt) else 0
    var s = print_n(count)
    Ok("First " + count + " elements: " + s)
  }

  def fib(n: Int, from : Int) : Long = {
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

  def print_n(n: Int) : String = {
    var s = ""
    for (i <- 0 to n-1) {
      s = s + cashed_elems.elems(i) + " "
    }
    s
  }  

  def index() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.index())
  }
}



