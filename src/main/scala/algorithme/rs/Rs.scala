package algorithme.rs // recuit simulé

import problem.knapsack.Knapsack_rs.perturbation
import problem.knapsack.Knapsack_rs.f
import problem.knapsack.Knapsack_rs.fw
import types.Type.{Data, Temperature, Value, Weight}

//  a = Coefficient pour le Refroidissement

case class Rs(myData : Data, a : Double, nbIt : Int, T : Temperature) {
  val (nbItem, capacity) :: data = myData
  val r = scala.util.Random


  def res(): Data = {
    val XMax: Data = perturbation(Nil, capacity, data);


    def critereMetropolis (deltaF : Double , T : Temperature) : Boolean = {
      (deltaF >= 0) || (r.nextDouble < math.exp(deltaF/T))
    }

    def refroidissement (T : Temperature) : Temperature = {a * T}

    def équilibreThermodynamique (equilibe : Int) : Boolean = {equilibe == 0}


    def loop_rs_equilibrage (nbItEq : Int, X : Data, Xmax : Data, T : Temperature) :(Data,Data) = {
      val XVois : Data = perturbation(X,capacity,data)
      val deltaF : Double = f(XVois) - f(X)
      val cM : Boolean = critereMetropolis(deltaF, T)
      (nbItEq, cM) match {
        case (0,_) => (X,Xmax)
        case (_, true) if deltaF > 0 && f(XVois) > f(Xmax) => loop_rs_equilibrage(nbItEq-1,XVois,XVois,T)
        case (_, true) => loop_rs_equilibrage(nbItEq-1,XVois,Xmax,T)
        case (_,false) => loop_rs_equilibrage(nbItEq-1,X,Xmax,T)
      }
    }

    def loop_rs(XAndXMax: (Data,Data), nbIt : Int, T: Temperature): Data = {
      val X : Data = XAndXMax._1
      val XMax : Data =  XAndXMax._2
      nbIt match {
         case 0 => XMax
         case _ => loop_rs(loop_rs_equilibrage(100,X, XMax,T), nbIt -1, refroidissement(T))
      }
    }
    loop_rs((XMax,XMax), nbIt, T)
  }


}
  /*X, un candidat, f X = f(X) énergie du système, T Température initiale
X min <- X
f min <- f(X)
Tant que T > T min et non critèreConvergence()
Tant que non équilibreThermodynamique()
// palier de température
X vois <- perturbation(X)
Δf = f(X vois ) - f X
Si critèreMetropolis(Δf,T) alors
X <- X vois
f X <- f(X vois )
Si Δf < 0 et f(X vois ) < f min alors
f min <- f(X vois )
X min <- X vois
Fin si
Fin si
Fin tant que
T <- refroidissement(T)
Fin tant que
*/


