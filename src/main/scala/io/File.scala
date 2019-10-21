package io

import types.Type.Data


case class File(path : String) {

  // Read file and put all the file in a List(Long,Long)
  def getData : Data = scala.io.Source.fromResource(path).getLines.toList.map(
    e => (e.split(" ")(0).toLong, e.split(" ")(1).toLong))


}
