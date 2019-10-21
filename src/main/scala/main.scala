import algorithme.lahc.Lahc
import io.File
import types.Type.{Data, Temperature}
import problem.knapsack.Knapsack_lahc.f
import problem.knapsack.Knapsack_lahc.fw
import algorithme.rs.Rs
import algorithme.stochastique.Stochastique



object Main{

  def main(args: Array[String]): Unit = {

    val myFile =File("knapsack/data/ks_300_0")
    val bufferSize : Int = 500
    val nbIteration : Int = 30000

    val algo_lahc =  Lahc(myFile.getData, bufferSize,nbIteration)

    val X : Data = algo_lahc.res()
    //X.foreach(println)

    println("valeur : " + f(X) + " | poids : "+ fw(X))

/*
    val coeff : Double = 0.1
    val T : Temperature = 1000
    val i : Int = 10000

    val algo = Rs(myFile.getData, coeff , i , T) // coeff / iteration / temperature

    val X1 : Data = algo.res()
    X1.foreach(println)
    println("valeur : " + f(X1) + " | poids : "+ fw(X1))


   val algo = Stochastique() // coeff / iteration / temperature

println(algo.res(-5,5,100 ,nbIteration))
*/
   }

}
