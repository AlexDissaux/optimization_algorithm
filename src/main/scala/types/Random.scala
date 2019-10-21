package types


import scala.annotation.tailrec
import scala.util.Random


object Random {

  /*
   * Pick a random number between to double values, inclusive.
   */
  @tailrec
  def between(low: Double, high: Double): Double = {
    if (low == high) {
      low
    } else {
      val mid = low + (high/2 - low/2)
      if (scala.util.Random.nextBoolean) between(low, mid) else between(mid, high)
    }
  }

}