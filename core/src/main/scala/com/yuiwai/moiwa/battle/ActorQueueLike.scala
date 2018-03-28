package com.yuiwai.moiwa.battle

trait ActorQueueLike {
  type Actor <: ActorLike
  val actors: Map[Actor, Int]
  def take(n: Int): Seq[Actor]
}
