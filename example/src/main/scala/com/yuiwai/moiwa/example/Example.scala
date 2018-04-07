package com.yuiwai.moiwa.example

import com.yuiwai.moiwa.battle.{ActorIdLike, ActorQueueLike, SpeedBasedActorLike}
import com.yuiwai.moiwa.level.{ExpBasedLevel, ExpBasedLevelCompanionLike, ExpBasedLevelSpec, LevelCatalogLike}

import scala.collection.SortedSet

object Example extends App {
  LevelExample.run()
  ActorQueueExample.run()
}

object LevelExample {
  case class Level(
    levelId: Int, nextLevelId: Option[Int],
    nextLevelExp: Option[Long],
    totalExp: Long,
    value: Int
  ) extends ExpBasedLevel[Int, Level, LevelSpec] {
    override val companion: ExpBasedLevelCompanionLike[Level] = Level
  }
  object Level extends ExpBasedLevelCompanionLike[Level] {
    override def build(): Level = Level(0, None, None, 0, 0)
  }
  case class LevelSpec(
    nextLevelId: Option[Int],
    nextLevelExp: Option[Long],
    levelId: Int,
    value: Int
  ) extends ExpBasedLevelSpec[Int]
  class LevelCatalog extends LevelCatalogLike[Int, LevelSpec] {
    override def resolve(levelId: Int): Option[LevelSpec] = ???
  }
  def run(): Unit = {
    val level = Level.build()
    implicit val levelCatalog = new LevelCatalog
    level.addExp(10)
  }
}

object ActorQueueExample {
  case class ActorId(id: Int) extends ActorIdLike
  case class Actor(id: ActorId, speed: Int) extends SpeedBasedActorLike {
    override type Id = ActorId
  }
  object Actor {
    def apply(id: Int, speed: Int): Actor = new Actor(ActorId(id), speed)
  }
  case class ActorQueue(actors: Map[Actor, Int], selected: Seq[Actor]) extends ActorQueueLike {
    private val threshold = 200
    private val delta = 5
    override type Self = ActorQueue
    override type SpeedBasedActor = Actor
    type Element = (SpeedBasedActor, Int)
    override def take(n: Int): Self =
      if (actors.isEmpty) this
      else take(n, SortedSet(actors.toSeq: _*)(Ordering.by(_._2 * -1)), selected)
    private def take(n: Int, xs: SortedSet[Element], current: Seq[SpeedBasedActor]): Self =
      if (xs.isEmpty) this
      else if (selected.size >= n) copy(selected = current ++ selected.take(n))
      else {
        n match {
          case 0 => this
          case _ =>
            if (xs.head._2 > threshold) take(n - 1, xs.tail + (xs.head._1 -> 0), current :+ xs.head._1)
            else map(e => (e._1, e._2 + delta), current).take(n)
        }
      }
    def map(f: Element => Element, current: Seq[SpeedBasedActor]): Self = ActorQueue(actors.map(f), current)
  }
  object ActorQueue {
    def apply(actors: Seq[Actor]): ActorQueue =
      ActorQueue(actors.map(actor => actor -> actor.speed).toMap, Seq.empty)
  }
  def run(): Unit = {
    val actors = Seq(Actor(1, 10), Actor(2, 8))
    val aq = ActorQueue(actors)
    assert(aq.take(10).size == 10)
    assert(aq.take(10).selected.forall(actors.toSet))
    assert(actors.forall(aq.take(10).selected.toSet))
  }
}
