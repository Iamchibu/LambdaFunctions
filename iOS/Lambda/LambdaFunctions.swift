/**
 * Gaston Longhitano <gastonl@bu.edu> @ Boston University - Research
 *
 * This source code is licensed under the terms found in the
 * LICENSE file in the root directory of this source tree.
 */

import Foundation


private let INTERVAL = 10000

class LambdaFunctions {

    // MARK: - Higher-order functions

    func higherOrderFunction(operation: (Int, Int) -> Int) -> Int {
      let result = (1...INTERVAL).reduce(0, operation)
      return result
    }

    // MARK: - Function types

    let functionTypes: (Int) -> Int = { value in
      let result = (1...INTERVAL).map { $0 * value }.reduce(0, +)
      return result
    }

    // MARK: - Instantiating a function type

    func instantiatingFunctionType(value: Int) -> Int {
        let result = higherOrderFunction(operation: { element, _ in  // Introduce unused argument "_"
            return element * value
        })
        return result
    }

    // MARK: - Invoking a function type instance

    func invokingFunctionTypeInstance() -> Int {
        let result = higherOrderFunction(operation: { element, _ in
            return element * 1
        })
        return result
    }

    // MARK: - Inline functions

    @inline(__always)
    func inlineFunctions(operation: (Int, Int) -> Int) -> Int {
        let result = (1...RANGE).reduce(0, operation)
        return result
    }

    // MARK: - Lambda expression syntax

    let lambdaExpressionSyntax: () -> Int = { (1...INTERVAL).reduce(0, +) }

    // MARK: - Passing trailing lambdas

    func higherOrderFunctionWithTrailingLambda(
        operation: (Int, Int) -> Int,
        trailing: () -> Void
    ) -> Int {
        trailing()
        let result = (1...RANGE).reduce(0, operation)
        return result
    }

    // MARK: - It: implicit name of a single parameter

    func itImplicitParameter(value: Int) -> Int {
      let result = (1...value).reduce(0, +)
      return result
    }

    // MARK: - Returning a value from a lambda expression

    func lambdaWithReturn(value: Int) -> Int {
      let result = (1...INTERVAL).map { $0 * value }.reduce(0, +)
      return result
    }

    // MARK: - Underscore for unused variables

    func unusedVariable(value: Int) -> Int {
      let result = (1...INTERVAL).reduce(0, +)
      return result
    }

    // MARK: - Function literals with receiver

    func functionLiteralWithReceiver(receiver: Int) -> Int {
      let result = (receiver...receiver + (INTERVAL - 1)).reduce(0, +)
      return result
    }

    // MARK: - Non-lambda equivalents

    func higherOrderFunctionWithoutLambda() -> Int {
      let result = (1...INTERVAL).reduce(0, +)
      return result
    }

    func functionTypesWithoutLambda(value: Int) -> Int {
      let result = (1...INTERVAL).reduce(0) { $0 + $1 * value }
      return result
    }

    func instantiatingFunctionTypeWithoutLambda(value: Int) -> Int {
      return higherOrderFunctionWithoutLambda() * value
    }

    func invokingFunctionTypeInstanceWithoutLambda() -> Int {
      return higherOrderFunctionWithoutLambda() * higherOrderFunctionWithoutLambda()
    }

    @inline(__always)
    func inlineFunctionWithoutLambda() -> Int {
      let result = (1...INTERVAL).reduce(0, +)
      return result
    }

    // Lambda expression syntax without lambda
    let lambdaExpressionSyntaxWithoutLambda: Int = (1...INTERVAL).reduce(0, +)

    func higherOrderFunctionWithTrailingLambdaWithoutLambda() -> Int {
      let trailingLambda: () -> Void = {}
      trailingLambda()
      let result = (1...INTERVAL).reduce(0, +)
      return result
    }

    func itImplicitParameterWithoutLambda(value: Int) -> Int {
      let result = (1...value).reduce(0, +)
      return result
    }

    // Returning a value from a lambda expression without lambda

    func lambdaWithReturnWithoutLambda(value: Int) -> Int {
      let result = (1...INTERVAL).reduce(0) { $0 + $1 * 1 }
      return result
    }
    
    func unusedVariableWithoutLambda() -> Int {
      // Use sum() with range() for efficient summation
      return (1...RANGE).reduce(0, +)
    }


    func functionLiteralWithReceiverWithoutLambda(receiver: Int) -> Int {
      let result = (receiver...receiver + (INTERVAL - 1)).reduce(0, +)
      return result
    }
}
