package problem.knapsack

import problem.knapsack.Knapsack_lahc.genereVoisin
import types.Type.{Data, Weight}

object Knapsack_rs {

  // Evaluation Function
  def f(X: Data): Long = {X.foldLeft(0.toLong)((acc, e) => acc + e._1)} // return the sum of value
  def fw(X: Data): Long = {X.foldLeft(0.toLong)((acc, e) => acc + e._2)} // return the sum of weight

  def perturbation(X: Data, c: Weight, data: Data): Data = {
    genereVoisin(X, c, data)
  }


}
