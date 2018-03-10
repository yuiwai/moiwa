package com.yuiwai.moiwa.scene

import com.yuiwai.moiwa.battle._

trait TurnBasedBattle extends TurnBasedBattleLike {
  val playerSide: PlayerSide
  val enemySide: EnemySide
  val currentSide: SideLike
}
