package io.scalechain.blockchain.net.processor

import scala.concurrent.duration._
import scala.concurrent.Future
import akka.actor._
import akka.testkit._
import org.scalatest._

class TransactionProcessorSpec extends TestKit(ActorSystem("MySpec")) with ImplicitSender
with WordSpecLike with ShouldMatchers with BeforeAndAfterEach with BeforeAndAfterAll {
  this: Suite =>

  var actor : ActorRef = null
  var peerBrokerProbe : TestProbe = null

  override def beforeEach() {
    // set-up code
    //
    peerBrokerProbe = TestProbe()
    actor = system.actorOf(TransactionProcessor.props(peerBrokerProbe.ref))

    super.beforeEach()
  }

  override def afterEach() {
    super.afterEach()

    // tear-down code
    //
  }

  override def afterAll {
    TestKit.shutdownActorSystem(system)
  }

  "actor" should {
    "do something" in {
/*
      val echo = system.actorOf(TestActors.echoActorProps)
      echo ! "hello world"
      expectMsg("hello world")
*/
    }
  }
}