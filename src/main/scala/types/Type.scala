package types

object Type
{
  type Weight = Long
  type Value = Long
  type Data = List[(Value, Weight)]
  //type Data = List[(String, String)]

  // Recruit Simulé r(s)
  type Temperature = Double

  // Stochatisque
  type Position = Double
  type Vitesse = Double
  // Position actuel, meilleur position par laquelle elle est passé, vitesse actuel
  type Particule = (Position, Position, Vitesse)
}

