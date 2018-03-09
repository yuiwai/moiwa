package com.yuiwai.moiwa.scene

import com.yuiwai.moiwa.battle.{BattleLike, EnemySide, PlayerSide, SideLike}

trait TurnBasedBattle extends BattleLike {
  val playerSide: PlayerSide
  val enemySide: EnemySide
  val currentSide: SideLike
}
