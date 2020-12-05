package xyz.funnycoding.solution

import xyz.funnycoding.domain.data.Solution

object Day01 {
  def solve(input: List[String]): Solution = {
    val xs                      = input.map(_.toInt)
    val cples: List[(Int, Int)] = xs.flatMap(e => matchs2000(e, xs.filterNot(_ == e)))
    cples.headOption match {
      case Some(value) => Solution(value = s"${value._1 * value._2}")
      case None        => Solution("error day 1")
    }
  }

  private def matchs2000(ys: Int, xs: List[Int]) =
    xs.find(x => x + ys == 2020).map(x => (ys, x))

}

object Day01B {
  def solve(input: List[String]): Solution = {
    val xs                      = input.map(_.toInt)
    val cples: List[(Int, Int)] = xs.flatMap(e => under2020(e, xs.filterNot(_ == e)))

    cples.flatMap {
      case (i, i1) =>
        equal2020(i, i1, xs)

    }.headOption match {
      case Some(value) => Solution(value = s"${value._1 * value._2 * value._3}")
      case None        => Solution("Error Day01b")
    }
  }

  private def under2020(ys: Int, xs: List[Int]): List[(Int, Int)] =
    xs.filter(x => x + ys < 2020).map(x => (ys, x))

  private def equal2020(x1: Int, x2: Int, ys: List[Int]): Option[(Int, Int, Int)] = {
    val filtered = ys.filterNot(el => el == x1 || el == x2)
    filtered
      .find { y =>
        x1 + x2 + y == 2020
      }
      .map(y => (y, x1, x2))
  }
}
