package test

import algorithms.sortAlgorithm.{SortAlgorithms, SortType}


object Test {
  def main(args: Array[String]): Unit = {
      val arr = Array(3, 2, 1, 5, 10, 8, 8, 7, -1, -10)
      println(s"Original Array is ${arr.mkString(", ")}")
      SortAlgorithms.show(SortType.QUICK, arr, showSteps = true)
  }
}