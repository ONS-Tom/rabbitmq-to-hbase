package main.scala

import akka.actor.{ ActorRef, ActorSystem }
import com.newmotion.akka.rabbitmq.{ ChannelActor, ConnectionActor, CreateChannel, _ }
import com.rabbitmq.client.{ ConnectionFactory, DefaultConsumer }
import com.typesafe.scalalogging.LazyLogging

/**
 * Modified from the following:
 * https://github.com/NewMotion/akka-rabbitmq#publishsubscribe
 */
object Subscriber extends App with LazyLogging {

  implicit val system = ActorSystem()
  val factory = new ConnectionFactory()
  val connection = system.actorOf(ConnectionActor.props(factory), "rabbitmq")
  val exchange = "amq.fanout"

  def setupSubscriber(channel: Channel, self: ActorRef) {
    val queue = channel.queueDeclare().getQueue
    channel.queueBind(queue, exchange, "")
    val consumer = new DefaultConsumer(channel) {
      override def handleDelivery(consumerTag: String, envelope: Envelope, properties: BasicProperties, body: Array[Byte]) {
        val bodyStr = fromBytes(body)
        Event.validate(bodyStr) match {
          case Some(e) => logger.info(s"Successfully parsed Event [$e] into model.")
          case None => logger.error("Error, unable to parse message into Event model.")
        }
        logger.info(s"Received message: $bodyStr")
      }
    }
    channel.basicConsume(queue, true, consumer)
  }
  connection ! CreateChannel(ChannelActor.props(setupSubscriber), Some("subscriber"))

  def fromBytes(x: Array[Byte]) = new String(x, "UTF-8")
}