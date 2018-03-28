package com.yuiwai.moiwa.scene

import com.yuiwai.moiwa.battle._
import com.yuiwai.moiwa.components.PvEBattleComponents

case class SpeedBasedPvEBattle(
  actorQueue: ActorQueueLike,
  playerSide: PlayerSide,
  enemySide: EnemySide,
  currentSide: SideLike) extends PvEBattleComponents
  with SpeedBasedBattleLike {
}
