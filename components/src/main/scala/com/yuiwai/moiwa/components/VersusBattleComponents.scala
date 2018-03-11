package com.yuiwai.moiwa.components

import com.yuiwai.moiwa.battle._

trait VersusBattleComponents {
  self: VersusMode =>
  val playerSide: PlayerSide
  val enemySide: EnemySide
  val currentSide: SideLike
}
trait PvEBattleComponents extends VersusBattleComponents
  with PvE {

}
