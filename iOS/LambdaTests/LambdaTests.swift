/**
 * Gaston Longhitano <gastonl@bu.edu> @ Boston University - Research
 *
 * This source code is licensed under the terms found in the
 * LICENSE file in the root directory of this source tree.
 */

import XCTest
@testable import Lambda

final class LambdaTests: XCTestCase {
    
    var functionalExamples: FunctionalExperiments!
    var range: Int64 =  10000 //Int(ProcessInfo.processInfo.environment["TEST_RANGE"]!)
    
    
    override func setUpWithError() throws {
        try super.setUpWithError()
        functionalExamples = FunctionalExperiments(range: range)
    }

    override func tearDownWithError() throws {
        functionalExamples = nil
        try super.tearDownWithError()
    }
    
    // Measure performance
    private func measurePerformance(block: @escaping () -> Void) {
        let clockMetric = XCTClockMetric()
        let memoryMetric = XCTMemoryMetric()
        let cpuMetric = XCTCPUMetric()
        
        // Setup the custom metrics
        let energyMetric = EnergyMetric()
        let batteryMetric = BatteryLevelMetric()
        batteryMetric.range = range
        
        let measureOptions = XCTMeasureOptions()
        measureOptions.iterationCount = 10  // Run the test 10 times
        
        let metrics: [any XCTMetric] = [clockMetric, memoryMetric, cpuMetric, energyMetric]
        
        measure(metrics: metrics, options: measureOptions) {
            block()
        }
    }
    
    func testNothing(){
        measurePerformance {
           _ = 0
        }
    }
    
    // MARK: - Lambda tests
    func testHigherOrderFunction1() {
        measurePerformance {
            _ = self.functionalExamples.higherOrderFunction { $0 + 1 }
        }
    }
    
    // Test for function types
    func testFunctionTypes() {
        measurePerformance {
            _ = self.functionalExamples.functionTypes(self.range)
        }
    }

    // Test for instantiating a function type
    func testInstantiatingFunctionType() {
        measurePerformance {
            _ = self.functionalExamples.instantiatingFunctionType(self.range)
        }
    }

    // Test for invoking a function type instance
    func testInvokingFunctionTypeInstance() {
        measurePerformance {
            _ = self.functionalExamples.invokingFunctionTypeInstance(self.range)
        }
    }

    // Test for inline functions
    func testInlineFunctions() {
        measurePerformance {
            _ = self.functionalExamples.inlineFunctions { $0 + 1 }
        }
    }

    // Test for lambda expression syntax
    func testLambdaExpressionSyntax() {
        measurePerformance {
            _ = self.functionalExamples.lambdaExpressionSyntax()
        }
    }

    // Test for passing trailing lambdas
    func testHigherOrderFunctionWithTrailingLambda() {
        measurePerformance {
            _ = self.functionalExamples.higherOrderFunctionWithTrailingLambda(operation: { $0 + 1 }) { }
        }
    }

    // Test for 'it' implicit name of a single parameter
    func testItImplicitParameter() {
        measurePerformance {
            _ = self.functionalExamples.itImplicitParameter(self.range)
        }
    }

    // Test for returning a value from a lambda expression
    func testLambdaWithReturn() {
        measurePerformance {
            _ = self.functionalExamples.lambdaWithReturn(self.range)
        }
    }

    // Test for underscore for unused variables
    func testUnusedVariable() {
        measurePerformance {
            _ = self.functionalExamples.unusedVariable(self.range)
        }
    }

    // Test for function literals with receiver
    func testFunctionLiteralWithReceiver() {
        measurePerformance {
            _ = self.functionalExamples.functionLiteralWithReceiver(self.range)
        }
    }

    
    // MARK: - Non-Lambda tests
    
    // Tests for functionalities without lambda expressions:
    func testHigherOrderFunctionWithoutLambda1() {
        measurePerformance {
            _ = self.functionalExamples.higherOrderFunctionWithoutLambda()
        }
    }
    
    
    // Test for function types without lambda
    func testFunctionTypesWithoutLambda() {
        measurePerformance {
            _ = self.functionalExamples.functionTypesWithoutLambda(value: self.range)
        }
    }

    // Test for instantiating a function type without lambda
    func testInstantiatingFunctionTypeWithoutLambda() {
        measurePerformance {
            _ = self.functionalExamples.instantiatingFunctionTypeWithoutLambda(value: self.range)
        }
    }

    // Test for invoking a function type instance without lambda
    func testInvokingFunctionTypeInstanceWithoutLambda() {
        measurePerformance {
            _ = self.functionalExamples.invokingFunctionTypeInstanceWithoutLambda()
        }
    }

    // Test for inline functions without lambda
    func testInlineFunctionWithoutLambda() {
        measurePerformance {
            _ = self.functionalExamples.inlineFunctionWithoutLambda()
        }
    }

    // Test for lambda expression syntax without lambda
    func testLambdaExpressionSyntaxWithoutLambda() {
        measurePerformance {
            _ = self.functionalExamples.lambdaExpressionSyntaxWithoutLambda
        }
    }

    // Test for passing trailing lambdas without lambda
    func testHigherOrderFunctionWithTrailingLambdaWithoutLambda() {
        measurePerformance {
            _ = self.functionalExamples.higherOrderFunctionWithTrailingLambdaWithoutLambda()
        }
    }

    // Test for 'it' implicit name of a single parameter without lambda
    func testItImplicitParameterWithoutLambda() {
        measurePerformance {
            _ = self.functionalExamples.itImplicitParameterWithoutLambda(value: self.range)
        }
    }

    // Test for returning a value from a lambda expression without lambda
    func testLambdaWithReturnWithoutLambda() {
        measurePerformance {
            _ = self.functionalExamples.lambdaWithReturnWithoutLambda()
        }
    }

    // Test for underscore for unused variables without lambda
    func testUnusedVariableWithoutLambda() {
        measurePerformance {
            _ = self.functionalExamples.unusedVariableWithoutLambda()
        }
    }

    // Test for function literals with receiver without lambda
    func testFunctionLiteralWithReceiverWithoutLambda() {
        measurePerformance {
            _ = self.functionalExamples.functionLiteralWithReceiverWithoutLambda(receiver: self.range)
        }
    }
}
