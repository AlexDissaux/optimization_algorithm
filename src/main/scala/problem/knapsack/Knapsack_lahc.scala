package problem.knapsack

import types.Type.{Data, Value, Weight}

object Knapsack_lahc {
  val r = scala.util.Random


  // Evaluation Function
  def f(X: Data): Long = {X.foldLeft(0.toLong)((acc, e) => acc + e._1)} // return the sum of value
  def fw(X: Data): Long = {X.foldLeft(0.toLong)((acc, e) => acc + e._2)} // return the sum of weight


  def genereVoisin(X: Data, c: Weight, data: Data): Data = {
    val eltToAdd: (Value, Weight) = r.shuffle(data.filter(_._2 < c).diff(X)).head // rom element to add in the backapck
    def dropEltUntilAddeltToAdd(X: Data): Data = {
      (eltToAdd, X.size) match {
        case (_, n) if eltToAdd._2 + fw(X) > c => dropEltUntilAddeltToAdd(r.shuffle(X).tail)
        case (e, _) => e :: X
      }
    }
    dropEltUntilAddeltToAdd(X)
  }

}
















/*
In algorithme.lahc :
      //val XMax : Data = genereX(data, nbItem.toInt, capacity,0) // Minimum Candidate which is generated
In Knnapsack

  def genereX(data: Data, n : Int, c :Weight, acc :Weight) : Data = {
    val noWeight :Weight = 0
    (r.shuffle(data),acc) match {
      case (h::t,`noWeight`) if  h._2 > c => genereX(data,n,c,acc) // prevents to select a first element which are heavier than the capacity
      case (h::t,_) if (acc + h._2) > c => Nil
      case (h::t,_) => h::genereX(t,n-1,c,acc + h._2)
    }
  }

 */