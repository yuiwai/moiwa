package com.yuiwai.moiwa.scene

import com.yuiwai.moiwa.battle.{EnemySide, PlayerSide, SideLike, SpeedBasedBattleLike}
import com.yuiwai.moiwa.components.PvEBattleComponents

case class SpeedBasedPvEBattle () extends PvEBattleComponents with SpeedBasedBattleLike {
  override val playerSide: PlayerSide = _
  override val enemySide: EnemySide = _
  override val currentSide: SideLike = _
}
