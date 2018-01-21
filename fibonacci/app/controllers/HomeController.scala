package controllers

import javax.inject._
import play.api._
import play.api.mvc._


@Singleton
class HomeController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {


  def fib_sequence(count: Int) = Action {
    var res = fib(count) 
    Ok( count + "th element is " + res )
  }


  def fib(n: Int) : Int = {
  var result = 0
  if (n>1) {result = fib(n-1) + fib(n-2)
  } 
  else {
  result = n
  }

  return result			
  }

  def index() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.index())
  }
}
