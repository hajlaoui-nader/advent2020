package xyz.funnycoding.file

import fs2.Stream
import fs2.io.file.Files
import java.nio.file.Path
import fs2.text

trait FileReader[F[_]] {
  def readFile(path: Path): Stream[F, String]
}

final class LiveFileReader[F[_]: Files] extends FileReader[F] {

  override def readFile(path: Path): Stream[F, String] =
    Files[F].readAll(path, 4096).through(text.utf8Decode)

}
