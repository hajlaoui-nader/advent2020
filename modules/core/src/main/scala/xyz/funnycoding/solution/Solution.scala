package xyz.funnycoding.solution

case class Solution[F[_]](solve: F[String => ???])
