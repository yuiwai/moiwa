package com.yuiwai.moiwa.level

trait LevelLike {
  val value: Int
}
trait ExpBasedLevel[Id, Self, Spec <: ExpBasedLevelSpec[Id]] extends LevelLike {
  val companion: ExpBasedLevelCompanionLike[Self]
  val levelId: Id
  val nextLevelId: Option[Id]
  val nextLevelExp: Option[Long]
  val totalExp: Long
  def addExp(exp: Long)(implicit levelRepository: LevelCatalogLike[Id, Spec]): Self = {
    val afterExp = totalExp + exp
    nextLevelExp match  {
      case Some(n) if afterExp >= n =>
        companion.build()
      case _ =>
        companion.build()
    }
  }
}
trait ExpBasedLevelCompanionLike[T] {
  def build(): T
}
trait LevelSpecLike[Id] extends LevelLike {
  val levelId: Id
}
trait ExpBasedLevelSpec[Id] extends LevelSpecLike[Id] {
  val nextLevelId: Option[Id]
  val nextLevelExp: Option[Long]
}
trait LevelCatalogLike[Id, T <: LevelSpecLike[_]] {
  def resolve(levelId: Id): Option[T]
}
