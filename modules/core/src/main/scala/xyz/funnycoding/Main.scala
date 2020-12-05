package xyz.funnycoding

import cats.effect._
import cats.implicits._
import xyz.funnycoding.console.LiveConsole
import xyz.funnycoding.domain.data._
import xyz.funnycoding.file.LiveFileReader
import xyz.funnycoding.solution._

object Main extends IOApp {
  override def run(args: List[String]): IO[ExitCode] =
    for {
      console       <- LiveConsole.make[IO]
      fileReader    <- LiveFileReader.make[IO]
      fileDiscovery <- fileReader.discover()
      paths         <- fileDiscovery.map(p => p.toAbsolutePath).compile.toList
      solver        <- LiveSolver.make[IO](paths, fileReader)
      _             <- console.putStrLn(s"all dispo: ${paths.mkString("\n")}")
      _             <- console.putStrLn("test your might")
      input         <- console.readLine()
      s <- solver.lookup(input).handleErrorWith {
        case PuzzleNotFound(err) =>
          for {
            _      <- console.putStrLn(err)
            stream <- IO(fs2.Stream.empty)
          } yield stream
      }
      res <- solver.solve(input, s)
      _   <- console.putStrLn(s"solution = ${res.value}")
    } yield ExitCode.Success
}
