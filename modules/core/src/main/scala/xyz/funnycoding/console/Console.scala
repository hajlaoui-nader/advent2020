package xyz.funnycoding.console

import cats.effect.Sync
import scala.io.StdIn
import cats.Show
import cats.implicits._

trait Console[F[_]] {
  def readLine(): F[String]
  def putStrLn[A: Show](a: A): F[Unit]
}
final class LiveConsole[F[_]: Sync] extends Console[F] {
  def readLine(): F[String] =
    Sync[F].delay(
      StdIn.readLine()
    )

  def putStrLn[A: Show](a: A): F[Unit] =
    Sync[F].delay {
      System.out.println(a.show)
    }

}
object LiveConsole {
  def make[F[_]: Sync]: F[Console[F]] = Sync[F].delay(new LiveConsole[F])
}
