package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import scala.collection.mutable.ListBuffer
import computation.Fibonacci


@Singleton
class HomeController @Inject()(cc: ControllerComponents, fib:Fibonacci) extends AbstractController(cc) {
																																																			
  def fib_sequence(count: Int) = Action {
    var res = if (count > fib.cashed_elems.cnt) fib.fib_compute(count, fib.cashed_elems.cnt) else 0
    var s = fib.print_n(count)
   Ok(views.html.index("First " + count + " elements: " + s))
  }

  def index() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.index("Pass the count of fibonacci numbers as a parameter:   /fibonacci?count=YOUR_NUMBER"))
  }
}



