package com.yuiwai.moiwa.battle

import com.yuiwai.moiwa.command.{CommandLike, CommandSourceLike, CommandTargetLike}

trait BattleCommandLike extends CommandLike
trait SingleSource extends CommandSourceLike
trait MultipleSource extends CommandSourceLike
trait SingleTarget extends CommandTargetLike
trait MultipleTarget extends CommandTargetLike
