package xyz.funnycoding

import cats.effect.IOApp
import cats.implicits._
import cats.effect.IO
import xyz.funnycoding.console.LiveConsole

object Main extends IOApp {
  override def run(args: List[String]): IO[Int] =
    for {
      console <- LiveConsole.make[IO]
      _       <- console.putStrLn("test your might")
    } yield 1
}
