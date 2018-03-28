package com.yuiwai.moiwa.example

import com.yuiwai.moiwa.battle.{ActorIdLike, ActorQueueLike, SpeedBasedActorLike}

import scala.collection.SortedSet

object Example extends App {
  ActorQueueExample.run()
}

object ActorQueueExample {
  case class ActorId(id: Int) extends ActorIdLike
  case class Actor(id: ActorId, speed: Int) extends SpeedBasedActorLike {
    override type Id = ActorId
  }
  object Actor {
    def apply(id: Int, speed: Int): Actor = new Actor(ActorId(id), speed)
  }
  case class ActorQueue(actors: Map[ActorId, Int]) extends ActorQueueLike {
    override type SpeedBasedActor = Actor
    override def take(n: Int): Seq[ActorId] =
      if (actors.isEmpty) Seq.empty
      else take(n, SortedSet(actors.toSeq: _*)(Ordering.by(_._2)), Seq.empty)
    private def take(n: Int, xs: SortedSet[(ActorId, Int)], current: Seq[ActorId]): Seq[ActorId] =
      if (xs.isEmpty) current
      else {
        n match {
          case 0 => current
          case _ => take(n - 1, xs.tail, current :+ xs.head._1)
        }
      }
    override def resolveActor(actorId: ActorId): Actor = ???
  }
  object ActorQueue {
    def apply(actors: Seq[Actor]): ActorQueue =
      ActorQueue(actors.map(actor => actor.id -> actor.speed).toMap)
  }
  def run(): Unit = {
    val actors = Seq(Actor(1, 10), Actor(2, 8))
    val aq = ActorQueue(actors)
    assert(aq.take(10).size == 10)
    assert(aq.take(10).forall(actors.map(_.id).toSet))
    assert(actors.map(_.id).forall(aq.take(10).toSet))
  }
}
