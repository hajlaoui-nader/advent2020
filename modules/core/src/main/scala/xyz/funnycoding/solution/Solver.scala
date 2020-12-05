package xyz.funnycoding.solution

import java.nio.file.Path

import cats.MonadError
import cats.effect.Sync
import fs2.Stream
import xyz.funnycoding.domain.data._
import xyz.funnycoding.effects._
import xyz.funnycoding.file._
import cats.implicits._

trait Solver[F[_]] {
  def lookup(input: String): F[Stream[F, String]]

  def solve(input: String, stream: Stream[F, String]): F[Solution]
}

final class LiveSolver[F[_]: Sync: MonadThrow](paths: List[Path], fileReader: FileReader[F]) extends Solver[F] {

  override def lookup(input: String): F[Stream[F, String]] =
    paths.find(path => path.getFileName.toString == input) match {
      case Some(p) => Sync[F].delay(fileReader.readFile(p))
      case None    => MonadError[F, Throwable].raiseError(PuzzleNotFound(s"$input not found"))
    }

  // TODO fix me bad abstraction
  override def solve(input: String, stream: Stream[F, String]): F[Solution] = {
    val list: F[List[String]] = stream.compile.toList
    input match {
      case "01"  => list.map(Day01.solve)
      case "01b" => list.map(Day01B.solve)
      case "02"  => list.map(Day02.solve)
      case "02b" => list.map(Day02B.solve)
      case _     => MonadError[F, Throwable].raiseError(DayNotFound(s"$input not found"))
    }

  }
}

object LiveSolver {
  def make[F[_]: Sync: MonadThrow](paths: List[Path], fileReader: FileReader[F]): F[LiveSolver[F]] =
    Sync[F].delay(new LiveSolver[F](paths, fileReader))
}
