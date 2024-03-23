/**
 * @Deprecated: Use FunctionalExperiments instead
 *
 * Gaston Longhitano <gastonl@bu.edu> @ Boston University - Research
 *
 * This source code is licensed under the terms found in the
 * LICENSE file in the root directory of this source tree.
 */

import Foundation

class FunctionalExamples {
    // Instance variable for range
    var range: Int = 10000
    
    // Initializer to set the range
    init(range: Int) {
        self.range = range
    }
    
    // Higher-order functions
    public func higherOrderFunction(operation: (Int64) -> Int64) -> Int64 {
        return (1...Int64(self.range)).reduce(0, { $0 + operation($1) })
    }
    
    // Function types
    public lazy var functionTypes: (Int64) -> Int64 = { [unowned self] value in
        return (1...Int64(self.range)).reduce(0, { $0 + ($1 * value) })
    }
    
    // Instantiating a function type
    public lazy var instantiatingFunctionType: (Int64) -> Int64 = { [unowned self] value in
        return self.higherOrderFunction(operation: { $0 * value })
    }
    
    // Invoking a function type instance
    public lazy var invokingFunctionTypeInstance: (Int64) -> Int64 = { [unowned self] it in
        return self.higherOrderFunction(operation: { $0 * 1 })
    }
    
    // Inline functions
    public func inlineFunctions(operation: @escaping (Int64) -> Int64) -> Int64 {
        return (1...Int64(self.range)).reduce(0, { $0 + operation($1) })
    }
    
    // Lambda expression syntax
    public lazy var lambdaExpressionSyntax: (Int64) -> Int64 = { it in
        return it * 1
    }
    
    // Passing trailing lambdas
    public func higherOrderFunctionWithTrailingLambda(operation: @escaping (Int64) -> Int64, trailing: () -> Void) -> Int64 {
        trailing()
        return (1...Int64(self.range)).reduce(0, { $0 + operation($1) })
    }
    
    // It: implicit name of a single parameter
    public lazy var itImplicitParameter: (Int64) -> Int64 = { it in
        return it * 1
    }
    
    // Returning a value from a lambda expression
    public lazy var lambdaWithReturn: (Int64) -> Int64 = { [unowned self] _ in
        return Int64((1...self.range).reduce(0, { $0 + ($1 * 1) }))
    }
    
    // Underscore for unused variables
    public lazy var unusedVariable: (Int64) -> Int64 = { [unowned self] _ in
        return Int64((1...self.range).reduce(0, +))
    }
    
    // Function literals with receiver
    public lazy var functionLiteralWithReceiver: (Int64) -> Int64 = { [unowned self] receiver in
        return (receiver...(receiver + Int64(self.range) - 1)).reduce(0, +)
    }
    
    // Higher-order functions without lambda
    public func higherOrderFunctionWithoutLambda() -> Int64 {
        return Int64((1...self.range).reduce(0, +))
    }
    
    // Function types without lambda
    public func functionTypesWithoutLambda(value: Int64) -> Int64 {
        return (1...Int64(self.range)).reduce(0) { accumulatedResult, currentElement in
            accumulatedResult + (currentElement * value)
        }
    }
    
    // Instantiating a function type without lambda
    public func instantiatingFunctionTypeWithoutLambda(value: Int64) -> Int64 {
        return higherOrderFunctionWithoutLambda() * value
    }
    
    // Invoking a function type instance without lambda
    public func invokingFunctionTypeInstanceWithoutLambda() -> Int64 {
        return higherOrderFunctionWithoutLambda() &* higherOrderFunctionWithoutLambda()
    }
    
    // Inline functions without lambda
    public func inlineFunctionWithoutLambda() -> Int64 {
        return Int64((1...self.range).reduce(0, +))
    }
    
    // Lambda expression syntax without lambda
    public lazy var lambdaExpressionSyntaxWithoutLambda: Int64 = Int64((1...self.range).reduce(0, +))
    
    // Passing trailing lambdas without lambda
    public func higherOrderFunctionWithTrailingLambdaWithoutLambda() -> Int64 {
        let trailingLambda: () -> Void = {}
        trailingLambda()
        return Int64((1...self.range).reduce(0, +))
    }
    
    // It: implicit name of a single parameter without lambda
    public func itImplicitParameterWithoutLambda(value: Int64) -> Int64 {
        return value + 5
    }
    
    // Returning a value from a lambda expression without lambda
    public func lambdaWithReturnWithoutLambda() -> Int64 {
        return Int64((1...self.range).reduce(0, { $0 + ($1 * 1) }))
    }
    
    // Underscore for unused variables without lambda
    public func unusedVariableWithoutLambda() -> Int64 {
        return Int64((1...self.range).reduce(0, +))
    }
    
    // Function literals with receiver without lambda
    public func functionLiteralWithReceiverWithoutLambda(receiver: Int64) -> Int64 {
        return (receiver...(receiver + Int64(self.range) - 1)).reduce(0, +)
    }
}
