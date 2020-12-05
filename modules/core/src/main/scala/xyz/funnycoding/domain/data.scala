package xyz.funnycoding.domain

import scala.util.control.NoStackTrace

object data {
  case class Solution(value: String) extends AnyVal

  case class PuzzleNotFound(error: String) extends NoStackTrace

  case class DayNotFound(error: String) extends NoStackTrace
}
