package com.yuiwai.moiwa.battle

trait ActorLike {
  type Id <: ActorIdLike
  val id: Id
}
trait ActorIdLike
trait SpeedBasedActorLike extends ActorLike {
  def speed: Int
}
