package io.scalechain.blockchain.transaction

import io.scalechain.blockchain.proto.{UnspentTranasctionOutput, Transaction}
import io.scalechain.blockchain.transaction.SigHash.SigHash
import io.scalechain.crypto.PrivateKey


object SigHash extends Enumeration {
  type SigHash = Value
  val ALL                    = new Val(nextId, "ALL")
  val NONE                   = new Val(nextId, "NONE")
  val SINGLE                 = new Val(nextId, "SINGLE")
  // BUGBUG : Should we use bitwise OR??
  val ALL_OR_ANYONECANPAY    = new Val(nextId, "ALL|ANYONECANPAY")
  val NONE_OR_ANYONECANPAY   = new Val(nextId, "NONE|ANYONECANPAY")
  val SINGLE_OR_ANYONECANPAY = new Val(nextId, "SINGLE|ANYONECANPAY")
}

/**
  * Created by kangmo on 5/13/16.
  */
object TransactionSigner {

  /** The result of signing a transaction.
    *
    * @param transaction the transaction with signatures.
    * @param complete true if transaction is fully signed; false if more signatures are required
    */
  case class SignedTransaction(transaction : Transaction, complete : Boolean)

  /** Sign a transaction.
    *
    * @param privateKeys a list of private keys.
    * @return true if all inputs are signed, false otherwise.
    */
  def sign(transaction : Transaction,
           dependencies  : List[UnspentTranasctionOutput],
           privateKeys   : List[PrivateKey],
           sigHash       : SigHash
          ) : SignedTransaction = {
    // TODO : implement
    assert(false)
    null
  }
}