import sbt._

object Dependencies {
  object Versions {
    val kindProjector = "0.11.0"

    val cats       = "2.3.0-M1"
    val catsEffect = "3.0-8096649"

    val fs2   = "3.0-5795280"
    val fs2io = "3.0-5795280"

    val newtype = "0.4.4"

    val scalaTest = "3.2.2"
  }

  object Libraries {
    val kindProjector = "org.typelevel" % "kind-projector" % Versions.kindProjector

    val cats       = "org.typelevel" %% "cats-core"   % Versions.cats
    val catsEffect = "org.typelevel" %% "cats-effect" % Versions.catsEffect

    val fs2   = "co.fs2" %% "fs2-core" % Versions.fs2
    val fs2io = "co.fs2" %% "fs2-io"   % Versions.fs2io

    val newtype = "io.estatico" %% "newtype" % Versions.newtype

    val scalaTest = "org.scalatest" %% "scalatest" % Versions.scalaTest
  }
}
