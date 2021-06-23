package test

import SortAlg._

import scala.collection.mutable.ArrayBuffer


object Test {
  def main(args: Array[String]): Unit = {
    //        val arr = Array(3, 2, 1, 5, 10, 8, 8, 7, -1, -10)
    //        println(s"Original Array is ${arr.mkString(", ")}")
    //        SortAlgorithms.show(SortType.QUICK, arr, showSteps = true)
  }
}

object CompanionTestObject {
  var objectVal: Int = 1
  val objectVar: Int = 2

  def objectFunc: Int = objectVar + objectVal
}

class CompanionTestClass extends Father {
  var classVal: Int = 1
  val classVar: Int = 2

  def classFunc: Int = classVar + classVal
}

object CompanionTestClass {
  def classCompanionDef(): Unit = println("this is a companion for a class")
}

case class CompanionTestCaseClass(var caseVar: Int = 1, caseVal: Int = 2) extends Father {
  def caseFunc: Int = caseVar + caseVal
}

class Father {
  def printString = println("this is father")
}
object Single extends Father {
  def fun(): Unit = println("this is a singleton.")
}
class Mother {
  def printStringMo = println("this is mother")
}
class Single(val int: Int) extends Mother