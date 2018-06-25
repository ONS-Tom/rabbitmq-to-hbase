package main.scala

import scala.util.Try

case class Event(userId: String, service: String, traceId: String, eventType: String, timestamp: String)

object Event {
  def validate(s: String): Option[Event] = {
    val split = s.trim.split(",").toList.map(_.trim)
    Try(Event(split.head, split(1), split(2), split(3), split(4))).toOption
  }
}