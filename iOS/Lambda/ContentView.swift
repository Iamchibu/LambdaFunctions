/**
 * Gaston Longhitano <gastonl@bu.edu> @ Boston University - Research
 *
 * This source code is licensed under the terms found in the
 * LICENSE file in the root directory of this source tree.
 */


import SwiftUI
import MachO
import Foundation


// Global constant
let RANGE = 10000

// Higher-order functions
func higherOrderFunction(operation: (Int64) -> Int64) -> Int64 {
    return (1...RANGE).reduce(0, { $0 + operation(Int64($1)) })
}

// Function types
let functionTypes: (Int64) -> Int64 = { value in
    return (1...RANGE).reduce(0, { $0 + (Int64($1) * value) })
}

// Instantiating a function type
let instantiatingFunctionType: (Int64) -> Int64 = { value in
    return higherOrderFunction(operation: { $0 * value })
}

// Invoking a function type instance
let invokingFunctionTypeInstance: (Int64) -> Int64 = { it in
    return higherOrderFunction(operation: { $0 * 1 })
}

// Inline functions
func inlineFunctions(operation: @escaping (Int64) -> Int64) -> Int64 {
    return (1...RANGE).reduce(0, { $0 + operation(Int64($1)) })
}

// Lambda expression syntax
let lambdaExpressionSyntax: (Int64) -> Int64 = { it in
    return it * 1
}

// Passing trailing lambdas
func higherOrderFunctionWithTrailingLambda(operation: @escaping (Int64) -> Int64, trailing: () -> Void) -> Int64 {
    trailing()
    return (1...RANGE).reduce(0, { $0 + operation(Int64($1)) })
}

// It: implicit name of a single parameter
let itImplicitParameter: (Int64) -> Int64 = { it in
    return it * 1
}

// Returning a value from a lambda expression
let lambdaWithReturn: (Int64) -> Int64 = {_ in
    return Int64((1...RANGE).reduce(0, { $0 + ($1 * 1) }))
}

// Underscore for unused variables
let unusedVariable: (Int64) -> Int64 = { _ in
    return Int64((1...RANGE).reduce(0, +))
}

// Function literals with receiver
let functionLiteralWithReceiver: (Int64) -> Int64 = { receiver in
    return (receiver...(receiver + Int64(RANGE) - 1)).reduce(0, +)
}

// Higher-order functions without lambda
func higherOrderFunctionWithoutLambda() -> Int64 {
    return Int64((1...RANGE).reduce(0, +))
}

// Function types without lambda
func functionTypesWithoutLambda(value: Int64) -> Int64 {
    return (1...RANGE).reduce(0) { accumulatedResult, currentElement in
        accumulatedResult + (Int64(currentElement) * value)
        }
}

// Instantiating a function type without lambda
func instantiatingFunctionTypeWithoutLambda(value: Int64) -> Int64 {
    return higherOrderFunctionWithoutLambda() * value
}

// Invoking a function type instance without lambda
func invokingFunctionTypeInstanceWithoutLambda() -> Int64 {
    return higherOrderFunctionWithoutLambda() &* higherOrderFunctionWithoutLambda()
}

// Inline functions without lambda
func inlineFunctionWithoutLambda() -> Int64 {
    return Int64((1...RANGE).reduce(0, +))
}

// Lambda expression syntax without lambda
let lambdaExpressionSyntaxWithoutLambda: Int64 = Int64((1...RANGE).reduce(0, +))

// Passing trailing lambdas without lambda
func higherOrderFunctionWithTrailingLambdaWithoutLambda() -> Int64 {
    let trailingLambda: () -> Void = {}
    trailingLambda()
    return Int64((1...RANGE).reduce(0, +))
}

// It: implicit name of a single parameter without lambda
func itImplicitParameterWithoutLambda(value: Int64) -> Int64 {
    return value + 5
}

// Returning a value from a lambda expression without lambda
func lambdaWithReturnWithoutLambda() -> Int64 {
    return Int64((1...RANGE).reduce(0, { $0 + ($1 * 1) }))
}

// Underscore for unused variables without lambda
func unusedVariableWithoutLambda() -> Int64 {
    return Int64((1...RANGE).reduce(0, +))
}

// Function literals with receiver without lambda
func functionLiteralWithReceiverWithoutLambda(receiver: Int64) -> Int64 {
    return (receiver...(receiver + Int64(RANGE) - 1)).reduce(0, +)
}


let functionsList: [(String, () -> Int64)] = [
    ("Higher-order function", { higherOrderFunction { $0 } }),
    ("Function types", { functionTypes(1) }),
    ("Instantiating function type", { instantiatingFunctionType(1) }),
    ("Invoking function type instance", { invokingFunctionTypeInstance(1) }),
    ("Inline function", { inlineFunctions { $0 * 1 } }),
    ("Lambda expression syntax", { lambdaExpressionSyntax(1) }),
    ("Passing trailing lambda", { higherOrderFunctionWithTrailingLambda(operation: { $0 * 1 }, trailing: { }) }),
    ("It: implicit name of a single parameter", { itImplicitParameter(1) }),
    ("Returning value from a lambda", { lambdaWithReturn(1) }),
    ("Underscore for unused variables", { unusedVariable(1) }),
    ("Function literals with receiver", { functionLiteralWithReceiver(1) })
]

let functionsWithoutLambdaList: [(String, () -> Int64)] = [
    ("Higher-order function without lambda", higherOrderFunctionWithoutLambda),
    ("Function types without lambda", { functionTypesWithoutLambda(value: 1) }),
    ("Instantiating function type without lambda", { instantiatingFunctionTypeWithoutLambda(value: 3) }),
    ("Invoking function type instance without lambda", invokingFunctionTypeInstanceWithoutLambda),
    ("Inline function without lambda", inlineFunctionWithoutLambda),
    ("Lambda expression syntax without lambda", { lambdaExpressionSyntaxWithoutLambda }),
    ("Passing trailing lambda without lambda", higherOrderFunctionWithTrailingLambdaWithoutLambda),
    ("It: implicit name of a single parameter without lambda", { itImplicitParameterWithoutLambda(value: 1) }),
    ("Returning value from a lambda without lambda", lambdaWithReturnWithoutLambda),
    ("Underscore for unused variables without lambda", unusedVariableWithoutLambda),
    ("Function literals with receiver without lambda", { functionLiteralWithReceiverWithoutLambda(receiver: 1) })
]



struct ContentView: View {
    @State private var resultText = ""
    
    
    var body: some View {
        VStack {
            ScrollView {
                VStack {
                    Group {
                        Button(action: {
                            runAndMeasureFunctions()
                        }) {
                            Text("Run ALL")
                                .padding()
                                .frame(maxWidth: .infinity)
                                .background(Color.yellow)
                                .foregroundColor(Color.white)
                                .cornerRadius(8)
                                .padding(.horizontal)
                        }
                        ForEach(functionsList, id: \.0) { function in
                           Button(action: {
                               printResult(function.0, result: measureExecution {
                                   _ = function.1()
                               })
                           }) {
                               Text(function.0)
                                   .padding()
                                   .frame(maxWidth: .infinity)
                                   .background(Color.blue)
                                   .foregroundColor(Color.white)
                                   .cornerRadius(8)
                                   .padding(.horizontal)
                           }
                       }
                        
                        
                        ForEach(functionsWithoutLambdaList, id: \.0) { function in
                           Button(action: {
                               printResult(function.0, result: measureExecution {
                                   _ = function.1()
                               })
                           }) {
                               Text(function.0)
                                   .padding()
                                   .frame(maxWidth: .infinity)
                                   .background(Color.red)
                                   .foregroundColor(Color.white)
                                   .cornerRadius(8)
                                   .padding(.horizontal)
                           }
                       }
                    }
                }
                .padding()
            }

            Divider()

            ScrollView() {
                // Horizontal scrollable results section
                ScrollView(.horizontal, showsIndicators: true) {
                    HStack {
                        Text(resultText)
                            .padding()
                    }
                }
            }
            
        }
    }
    
    
    private func printResult(_ functionName: String, result: (Double, UInt64, String)) {
        resultText.append("\(functionName), \(RANGE), \(String(format: "%.4f", result.0)), \(result.1), \(result.2)\n")
    }
   
    private func measureExecution(operation: () -> Void ) -> (Double, UInt64, String) {
        let startMemory = MemoryUsage.reportMemory()
        let startTime = CFAbsoluteTimeGetCurrent()

        operation()

        let timeElapsed = CFAbsoluteTimeGetCurrent() - startTime
        let endMemory = MemoryUsage.reportMemory()
        let memoryUsed = endMemory &- startMemory
        
        let energyUsed = "Low"
        return (timeElapsed * 1000, memoryUsed, energyUsed)

    }
    
    
    private func runAndMeasureFunctions() -> Void {
        for function in functionsList {
            let result = measureExecution {
                _ = function.1()
            }
            
            print("\(String(format: "%.4f", result.0))")
        }
        
        print(" ")
        for function in functionsWithoutLambdaList {
            let result = measureExecution {
                _ = function.1()
            }
            print("\(String(format: "%.4f", result.0))")
            
        }
        print("---")
    }
    
}


class MemoryUsage {
    static func reportMemory() -> UInt64 {
        var info = mach_task_basic_info()
        var count = mach_msg_type_number_t(MemoryLayout<mach_task_basic_info>.size) / 4

        let kerr: kern_return_t = withUnsafeMutablePointer(to: &info) {
            $0.withMemoryRebound(to: integer_t.self, capacity: Int(count)) {
                task_info(mach_task_self_, task_flavor_t(MACH_TASK_BASIC_INFO), $0, &count)
            }
        }

        if kerr == KERN_SUCCESS {
            return info.resident_size
        } else {
            print("Error with task_info(): \(kerr)")
            return 0
        }
    }
}


@main
struct LambdaFunctionsApp: App {
    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}


#Preview {
    ContentView()
}
