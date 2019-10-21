package algorithme.stochastique

import types.Type.{Particule, Position, Vitesse}
import types.Random.between

case class Stochastique () {
  val coeffInertie : Double = 0.7298844

  def majBestPostion(x: Particule) : Particule = {
    if (f(x._1) < f(x._2)) {x.copy(_2 = x._1)} else x
  }

  def f(p: Position): Double = scala.math.sin(p)

  def newVitesse (x : Particule, xBest : Position) : Particule = {
    x.copy(_3 = coeffInertie * x._3 + 0.5*(x._2 - x._1 ) + 0.5*(xBest - x._1 ))
  }

  def genereParticule(borneSup: Position, borneInf: Position): Particule = {
    val r : Double = between(borneInf ,borneSup)
    val res : Particule = new Particule(r,r,0)
    res
  }

  def res(borneInf : Double, borneSup : Double, nbParticule : Int, nbIt : Int) : Position = {
    val lp : Vector[Particule] = Vector.fill(nbParticule)(genereParticule(borneSup,borneInf))


    def loop (it : Int, lp : Vector[Particule], bestX : Position, f : (Position => Double)) : Position = {
      val newlp : Vector[Particule] = lp.map(x => newVitesse(x, bestX)).map(x => x.copy(_1 = x._1 + x._3)).map(x => majBestPostion(x))
      it match {
        case 0 => bestX
        case _ => loop(it-1,newlp,(bestX+:(newlp.map(e => e._2))).fold(bestX)((b,e) => if (f(e) < f(b)) e else b ) /*selectBest(newlp,bestX)*/,f)
      }
    }
    loop(nbIt, lp, lp.min._1,f)

  }

}
