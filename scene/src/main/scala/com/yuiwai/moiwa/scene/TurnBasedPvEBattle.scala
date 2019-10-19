package com.yuiwai.moiwa.scene

import com.yuiwai.moiwa.battle.{EnemySide, PlayerSide, SideLike, TurnBasedBattleLike}
import com.yuiwai.moiwa.components.PvEBattleComponents

case class TurnBasedPvEBattle(
  turnLimit: Option[Int],
  playerSide: PlayerSide,
  enemySide: EnemySide,
  currentSide: SideLike) extends PvEBattleComponents
  with TurnBasedBattleLike {

}
