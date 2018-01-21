package controllers

import org.scalatestplus.play._
import org.scalatestplus.play.guice._
import play.api.test._
import play.api.test.Helpers._
import computation.Fibonacci

class HomeControllerSpec extends PlaySpec with GuiceOneAppPerTest with Injecting {

  "HomeController GET" should {

    "render the index page from a new instance of controller" in {
      val fibonacci = new Fibonacci()
      val controller = new HomeController(stubControllerComponents(),fibonacci)
      val home = controller.index().apply(FakeRequest(GET, "/"))

      status(home) mustBe OK
      contentType(home) mustBe Some("text/html")
    }

    "render the index page from the application" in {
      val controller = inject[HomeController]
      val home = controller.index().apply(FakeRequest(GET, "/"))

      status(home) mustBe OK
      contentType(home) mustBe Some("text/html")
    }

    "render the index page from the router" in {
      val request = FakeRequest(GET, "/")
      val home = route(app, request).get

      status(home) mustBe OK
      contentType(home) mustBe Some("text/html")
    }

    "cashing" in {
       val fib = new Fibonacci()
       fib.cashed_elems.cnt mustBe 2
       fib.cashed_elems.elems(0) mustBe 1
       fib.fib_compute(10)
       fib.cashed_elems.cnt mustBe 10
       fib.fib_compute(3)
       fib.cashed_elems.cnt mustBe 10
       fib.cashed_elems.elems(9) mustBe 55
       fib.cashed_elems.elems(8) mustBe 34	
     }
	
     "computing" in {
       val fib = new Fibonacci()
       fib.fib_compute(10)
       fib.cashed_elems.elems.count(x => x <= 0) mustBe 0
       fib.take_n_elems(5)(4) mustBe 5
       fib.fib_compute(10)
       fib.take_n_elems(10).last mustBe 55
       fib.fib_compute(30)
       fib.cashed_elems.elems.last mustBe 832040
    }
  }
}
