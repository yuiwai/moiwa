package com.yuiwai.moiwa.level

trait LevelLike
trait ExpBasedLevel[T] {
  val levelId: T
  val nextLevelId: T
  val nextLevelExp: Long
  val currentExp: Long
}
