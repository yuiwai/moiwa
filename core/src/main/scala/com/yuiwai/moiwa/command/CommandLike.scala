package com.yuiwai.moiwa.command

trait CommandLike {
  val source: CommandSourceLike
  val target: CommandTargetLike
  def execute(): CommandResultLike
}
trait CommandSourceLike
trait CommandTargetLike
trait CommandResultLike
