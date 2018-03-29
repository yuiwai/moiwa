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
