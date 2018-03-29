package com.yuiwai.moiwa.battle

trait ActorQueueLike {
  type Self <: ActorQueueLike
  type SpeedBasedActor <: SpeedBasedActorLike
  val actors: Map[SpeedBasedActor, Int]
  val selected: Seq[SpeedBasedActor]
  def size: Int = selected.size
  def take(n: Int): Self
}
