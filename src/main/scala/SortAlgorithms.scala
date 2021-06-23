package SortAlg

import scala.collection.mutable.ArrayBuffer

object SortType extends Enumeration {
  val BUBBLE = Value("bubble")
  val SELECTION = Value("selection")
  val INSERTION = Value("insertion")
  val SHELL = Value("shell")
  val MERGE = Value("merge")
  val QUICK = Value("quick")
}

object SortAlgorithms {

  def show(stype: SortType.Value, list: Array[Int], showSteps: Boolean = false): Unit = {
    val sortedList: Array[Int] = stype match {
      case SortType.BUBBLE => bubbleSort(list, showSteps)
      case SortType.SELECTION => selectionSort(list, showSteps)
      case SortType.INSERTION => insertionSort(list, showSteps)
      case SortType.SHELL => shellSort(list, showSteps)
      case SortType.MERGE => mergeSort(list, showSteps)
      case SortType.QUICK => quickSort(list, showSteps)
      case _ =>
        println("Unknown algorithm")
        Array()
    }

    println(s"Sorted Array is ${sortedList.mkString(", ")}")
  }

  private def swap(list: Array[Int], index1: Int, index2: Int) = {
    val tmp = list(index1)
    list(index1) = list(index2)
    list(index2) = tmp
    list
  }

  private def showMiddleSteps(show: Boolean, list: Array[Int], iter: Int): Unit = {
    if (show) {
      println(s"iter $iter : " + list.mkString(", "))
    }
  }

  private def bubbleSort(list: Array[Int], showSteps: Boolean): Array[Int] = {
    for (i <- 0 until (list.length - 1)) {
      for (j <- 0 until (list.length - 1 - i)) {
        val k = j + 1
        if (list(j) > list(k)) {
          swap(list, j, k)
        }
        showMiddleSteps(showSteps, list, j)
      }
    }
    list
  }

  private def selectionSort(list: Array[Int], showSteps: Boolean): Array[Int] = {
    for (i <- list.indices) {
      var minIndex = i
      for (j <- i + 1 until list.length) {
        if (list(j) < list(minIndex)) {
          minIndex = j
        }
      }
      swap(list, i, minIndex)
      showMiddleSteps(showSteps, list, i)
    }
    list
  }

  private def insertionSort(list: Array[Int], showSteps: Boolean): Array[Int] = {
    for (i <- 1 until list.length) {
      var preIndex = i - 1
      val current = list(i) // 记录原先的值
      while (preIndex >= 0 && list(preIndex) > current) { // 当前面的值大于后面的时候，一直需要挪位置；直到第一个元素被比较完成后才停止
        list(preIndex + 1) = list(preIndex)
        preIndex -= 1
      }
      list(preIndex + 1) = current // 当前面的值小于后面的值后，才能走到这里；这时仅需要把当前位置的值改成原先的目标值即可
      showMiddleSteps(showSteps, list, i)
    }
    list
  }

  private def shellSort(list: Array[Int], showSteps: Boolean): Array[Int] = {
    var gap = 1
    while (gap < list.length / 2) {
      gap = gap * 2
    }
    while (gap > 0) {
      for (i <- gap until list.length) {
        if (list(i) < list(i - gap)) {
          swap(list, i, i - gap)
        }
      }
      showMiddleSteps(showSteps, list, gap)
      gap = gap / 2
    }
    list
  }

  private def mergeSort(list: Array[Int], showSteps: Boolean): Array[Int] = {
    /** 输入两个Sorted Array，返回一个合并后的Sorted Array */
    def merge(list1: Array[Int], list2: Array[Int], recur: Boolean = false): Array[Int] = {
      //递归实现
      if (recur) {
        if (list1.isEmpty) {
          list2
        } else if (list2.isEmpty) {
          list1
        } else {
          val (heads1, remains1) = list1 splitAt 1
          val (heads2, remains2) = list2 splitAt 1
          val head1 = heads1.head
          val head2 = heads2.head
          if (head1 < head2) {
            heads1 ++ merge(remains1, list2)
          } else {
            heads2 ++ merge(remains2, list1)
          }
        }
      } else {
        // 动态规划实现
        val ret = ArrayBuffer[Int]()
        val list1buf = ArrayBuffer[Int]() ++ list1
        val list2buf = ArrayBuffer[Int]() ++ list2
        while (list1buf.nonEmpty && list2buf.nonEmpty) {
          if (list1buf.head < list2buf.head) {
            ret += list1buf.head
            list1buf.dropInPlace(1)
          } else {
            ret += list2buf.head
            list2buf.dropInPlace(1)
          }
        }
        list1buf.foreach(ret += _)
        list2buf.foreach(ret += _)
        ret.toArray
      }
    }

    if (list.isEmpty | list.length == 1) {
      return list
    }
    val len = list.length / 2
    val (sublist1, sublist2) = list splitAt len
    merge(mergeSort(sublist1, showSteps), mergeSort(sublist2, showSteps))
  }



  private def quickSort(list: Array[Int], showSteps: Boolean = false, scalaWay: Boolean = false): Array[Int] = {
    /** low用来找小于pivot的数，high用于找大于pivot的数，返回值是pivot应该在的 索引值 */
    def innterQuickSort(list: Array[Int], low: Int, high: Int): Int = {
      val pivot = list(low)
      var lowIndex = low
      var highIndex = high

      if (low >= high)
        return low

      while (lowIndex < highIndex) {
        while (lowIndex < highIndex && list(highIndex) >= pivot) {
          highIndex -= 1
        }
        // 当到这里时，说明high找到了比pivot小的值了
        list(lowIndex) = list(highIndex)
        while (lowIndex < highIndex && list(lowIndex) <= pivot) {
          lowIndex += 1
        }
        // 当到这里时，说明low找到了比pivot大的值了
        list(highIndex) = list(lowIndex)
      }
      // 当到这里时，说明high跟low相遇了，pivot的索引可以确定
      list(lowIndex) = pivot
      innterQuickSort(list, low, lowIndex - 1)
      innterQuickSort(list, lowIndex + 1, high)
      lowIndex
    }

    if (!scalaWay) {
      innterQuickSort(list, 0, list.length - 1)
      list
    } else {
      // Scala way, nice way
      if (list.length < 2) {
        list
      } else {
        quickSort(list.filter(_ < list.head)) ++
          list.filter(_ == list.head) ++
          quickSort(list.filter(_ > list.head))
      }
    }
  }

}
