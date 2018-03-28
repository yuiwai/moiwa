package com.yuiwai.moiwa.battle

trait ActorLike {
  type Id <: ActorIdLike
}
trait ActorIdLike
trait SpeedBasedActorLike extends ActorLike {
  def speed: Int
}
