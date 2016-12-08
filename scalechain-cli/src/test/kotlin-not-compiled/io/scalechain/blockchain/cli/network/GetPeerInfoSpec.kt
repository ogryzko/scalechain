package io.scalechain.blockchain.cli.network

import io.scalechain.blockchain.api.command.network.{GetPeerInfoResult, GetPeerInfo}
import io.scalechain.blockchain.cli.APITestSuite
import org.scalatest.*

/**
  * Created by kangmo on 11/2/15.
  */

// The test does not pass yet. Will make it pass soon.
@Ignore
class GetPeerInfoSpec : FlatSpec with BeforeAndAfterEach with APITestSuite {
  this: Suite =>

  override fun beforeEach() {
    // set-up code
    //

    super.beforeEach()
  }

  override fun afterEach() {
    super.afterEach()

    // tear-down code
    //
  }

  // The test does not pass yet. Will make it pass soon.

  "GetPeerInfo" should "should get the bitcoind as a peer." {
    val response = invoke(GetPeerInfo)
    val result = response.right.get.get.asInstanceOf<GetPeerInfoResult>

    // We should have only one peer.
    result.peerInfos.size shouldBe 1
  }
}
