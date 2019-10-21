package algorithme.lahc  //

import types.Type.{Data, Value, Weight}
import problem.knapsack.Knapsack_lahc.f
import problem.knapsack.Knapsack_lahc.genereVoisin

case class Lahc (myData : Data, bufferSize : Int, nbIteration : Int) {
    val (nbItem, capacity)::data = myData


    def res () : Data = {
      val XMax : Data = genereVoisin(Nil,capacity,data) // Minimum Candidate which is generated
      val tabMemoire : Vector[Long] = Vector.fill(bufferSize)(f(XMax)) // initilization // 500 = buffer size

      def loop_lahc(X : Data, XMax : Data, n : Int,nbIt : Int, tabMemoire : Vector[Long]) : Data = {
        val i : Int = n % bufferSize
        val XVoisin : Data = genereVoisin(X,capacity,data)
        val fMem = tabMemoire(i)
        nbIt match {
          case 0 => XMax
          case _ if (f(XMax) <= f(XVoisin)) & (fMem <= f(XVoisin))  => loop_lahc(X, XVoisin,n+1,nbIt-1,tabMemoire.updated(i,f(XVoisin)))
          case _ if f(XMax) <= f(XVoisin)  => loop_lahc(XVoisin, XVoisin,n+1,nbIt-1,tabMemoire.updated(i,f(X)))
          case _ if fMem <= f(XVoisin)  => loop_lahc(XVoisin, XMax,n+1,nbIt-1,tabMemoire.updated(i,f(XVoisin)))
          case _ => loop_lahc(X, XMax,n+1,nbIt-1,tabMemoire.updated(i,f(X)))
        }
      }
      loop_lahc(XMax, XMax, 1, nbIteration, tabMemoire)


    }

}
