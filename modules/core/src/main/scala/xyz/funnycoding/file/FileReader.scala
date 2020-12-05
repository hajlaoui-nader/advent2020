package xyz.funnycoding.file

import fs2.Stream
import fs2.io.file.Files
import java.nio.file.Path
import java.nio.file.Paths

import cats.effect.Sync
import fs2.text

trait FileReader[F[_]] {
  def discover(): F[Stream[F, Path]]
  def readFile(path: Path): Stream[F, String]
}

final class LiveFileReader[F[_]: Sync: Files] extends FileReader[F] {

  override def readFile(path: Path): Stream[F, String] =
    Files[F].readAll(path.toAbsolutePath, 4096).through(text.utf8Decode).through(text.lines)

  override def discover(): F[Stream[F, Path]] =
    Sync[F].delay {
      val path = Paths.get("modules/core/src/main/resources/days/")
      Files[F].walk(path).tail
    }
}

object LiveFileReader {
  def make[F[_]: Sync: Files]: F[FileReader[F]] = Sync[F].delay(new LiveFileReader())
}
