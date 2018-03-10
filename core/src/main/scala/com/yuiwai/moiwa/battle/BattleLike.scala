package com.yuiwai.moiwa.battle

import com.yuiwai.moiwa.time.TurnLike

trait BattleLike

trait VersusMode
trait PvP extends VersusMode
trait PvE extends VersusMode

trait TurnBasedBattleLike extends BattleLike {
  val turnLimit: Option[Int]
}
trait BattleTurn extends TurnLike
