package com.yuiwai.moiwa.party

import com.yuiwai.moiwa.unit.UnitLike

trait PartyLike[U <: UnitLike] {
  val unitList: Seq[U]
}
