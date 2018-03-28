package com.yuiwai.moiwa.battle

trait ActorQueueLike {
  type SpeedBasedActor <: SpeedBasedActorLike
  type ActorId = SpeedBasedActor#Id
  val actors: Map[ActorId, Int]
  def take(n: Int): Seq[ActorId]
  def resolveActor(actorId: ActorId): SpeedBasedActor
}
