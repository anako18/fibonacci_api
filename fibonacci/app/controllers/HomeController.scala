package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import scala.collection.mutable.ListBuffer
import computation.Fibonacci


@Singleton
class HomeController @Inject()(cc: ControllerComponents, fib:Fibonacci) extends AbstractController(cc) {
																																																			
  def fib_sequence(count: Int) = Action {
    if (count < 0) { throw new IllegalArgumentException("Count must be positive.") }
    fib.fib_compute(count)
    val printed_sequence = fib.take_n_elems(count).mkString("", ", ", ".")
  Ok(views.html.index("First " + count + " elements: " + printed_sequence))
  }

  def index() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.index("Pass the positive int count of fibonacci numbers as a parameter: /fibonacci?count=YOUR_NUMBER"))
  }
}



