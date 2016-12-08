package io.scalechain.blockchain.script

import java.math.BigInteger

import io.scalechain.blockchain.{ErrorCode, ScriptEvalException}
import io.scalechain.blockchain.script.ops.*
import org.scalatest.*
import org.scalatest.prop.TableDrivenPropertyChecks.*
import org.scalatest.prop.Tables.Table

/** Test bitwise logic operations in BitwiseLogic.scala
  *
  */
class BitwiseLogicSpec : FlatSpec with BeforeAndAfterEach with OperationTestTrait {

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

  val operations =
    Table(
      // column names
      ("inputValues", "operation", "expectedOutputValue"),
      // test cases with input value, script operation, output value
      // The input value is pushed on to the script execution stack from left to right.
      // OP_EQUAL(0x87) : Push TRUE (1) if top two items are exactly equal, push FALSE (0) otherwise
      (stack("", ""),                OpEqual(),      stack(1L)),
      (stack("", "a"),               OpEqual(),      stack(0L)),
      (stack("a", ""),               OpEqual(),      stack(0L)),
      (stack("Hello", "Hello"),      OpEqual(),      stack(1L)),
      (stack("Hello", "World"),      OpEqual(),      stack(0L)),

      // OP_EQUALVERIFY(0x88) : Same as OP_EQUAL, but run OP_VERIFY after to halt if not TRUE
      (stack("", ""),                OpEqualVerify(),  stack()),
      (stack("", "a"),               OpEqualVerify(),  ErrorCode.InvalidTransaction),
      (stack("a", ""),               OpEqualVerify(),  ErrorCode.InvalidTransaction),
      (stack("Hello", "Hello"),      OpEqualVerify(),  stack()),
      (stack("Hello", "World"),      OpEqualVerify(),  ErrorCode.InvalidTransaction)
    )

  "operations" should "run and push expected value on the stack." {
    forAll(operations) { ( inputValues : Array<ScriptValue>, operation : ScriptOp, expectation : AnyRef )  =>
      verifyOperations(inputValues, listOf(operation), expectation);
    }
  }

  val disabledOperations =
    Table(
      // precondition
      ("operation"),
      (OpInvert()),
      (OpAnd()),
      (OpOr()),
      (OpXor())
    )

  "disabled operations" should "throw ScriptEvalException with DisabledScriptOperation error code." {
    forAll(disabledOperations) { (operation: ScriptOp) =>
      verifyOperations(stack(), listOf(operation), ErrorCode.DisabledScriptOperation);
    }
  }
}