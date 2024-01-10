import kotlin.system.measureTimeMillis
/**
 * Chibundom Ejimuda <cejimuda@bu.edu>
 * Created on 11/29/2023
 */
// Global constant
const val RANGE = 10000

// Higher-order functions
fun higherOrderFunction(operation: (Int) -> Int): Int {
    val result = (1..RANGE).sumBy(operation)
//    println("Sum within the function: $result")

    return result
}

// Function types
val functionTypes: (Int) -> Int = { value ->
    val result = (1..RANGE).sumBy { it * value }
//    println("Sum within the function types: $result")
    result
}

// Instantiating a function type
val instantiatingFunctionType: (Int) -> Int = { value ->
    val result = higherOrderFunction { it * value }
//    println("Sum within the instantiating function type: $result")
    result
}

// Invoking a function type instance
val invokingFunctionTypeInstance: (Int) -> Int = { it ->
    val result = higherOrderFunction { it * 1 }
//    println("Sum within the invoking function type instance: $result")
    result
}

// Inline functions
inline fun inlineFunctions(operation: (Int) -> Int): Int {
    val result = (1..RANGE).sumBy(operation)
//    println("Sum within the inline function: $result")
    return result
}

// Lambda expression syntax
val lambdaExpressionSyntax: (Int) -> Int = { it * 1 }

// Passing trailing lambdas
fun higherOrderFunctionWithTrailingLambda(operation: (Int) -> Int, trailing: () -> Unit): Int {
    trailing()
    val result = (1..RANGE).sumBy(operation)
//    println("Sum within the function with trailing lambda: $result")
    return result
}

// It: implicit name of a single parameter
val itImplicitParameter: (Int) -> Int = { it * 1 }

// Returning a value from a lambda expression
val lambdaWithReturn: (Int) -> Int = {
    val result = (1..RANGE).sumBy { it * 1 }
//    println("Sum within the lambda with return: $result")
    result
}

// Underscore for unused variables
val unusedVariable: (Int) -> Int = { _ ->
    val result = (1..RANGE).sum()
//    println("Sum within the function with unused variable: $result")
    result
}

// Function literals with receiver
val functionLiteralWithReceiver: Int.() -> Int = {
    val result = (this..this + (RANGE - 1)).sum()
//    println("Sum within the function literal with receiver: $result")
    result
}


// 1. Higher-order functions without lambda
fun higherOrderFunctionWithoutLambda(): Int {
    return (1..RANGE).sum()
}

// 2. Function types without lambda
fun functionTypesWithoutLambda(value: Int): Int {
    return (1..RANGE).sumBy { value * 1 }
}

// 3. Instantiating a function type without lambda
fun instantiatingFunctionTypeWithoutLambda(value: Int): Int {
    return higherOrderFunctionWithoutLambda() * value
}

// 4. Invoking a function type instance without lambda
fun invokingFunctionTypeInstanceWithoutLambda(): Int {
    return higherOrderFunctionWithoutLambda() * higherOrderFunctionWithoutLambda()
}

// 5. Inline functions without lambda
inline fun inlineFunctionWithoutLambda(): Int {
    return (1..RANGE).sum()
}

// 6. Lambda expression syntax without lambda
val lambdaExpressionSyntaxWithoutLambda: Int = (1..RANGE).sum()

// 7. Passing trailing lambdas without lambda
fun higherOrderFunctionWithTrailingLambdaWithoutLambda(): Int {
    val trailingLambda: () -> Unit = {}
    trailingLambda()
    return (1..RANGE).sum()
}

// 8. It: implicit name of a single parameter without lambda
fun itImplicitParameterWithoutLambda(value: Int): Int {
    return value + 5
}

// 9. Returning a value from a lambda expression without lambda
fun lambdaWithReturnWithoutLambda(): Int {
    return (1..RANGE).sumBy { it * 1 }
}

// 10. Underscore for unused variables without lambda
fun unusedVariableWithoutLambda(): Int {
    return (1..RANGE).sum()
}

// 11. Function literals with receiver without lambda
fun functionLiteralWithReceiverWithoutLambda(receiver: Int): Int {
    return (receiver..receiver + (RANGE - 1)).sum()
}


fun main() {
    val functionsList: List<Pair<String, () -> Int>> = listOf(
        "Higher-order function" to { higherOrderFunction { it } },
        "Function types" to { functionTypes(1) },
        "Instantiating function type" to { instantiatingFunctionType(1) },
        "Invoking function type instance" to { invokingFunctionTypeInstance(1) },
        "Inline function" to { inlineFunctions { it * 1 } },
        "Lambda expression syntax" to { lambdaExpressionSyntax(1) },
        "Passing trailing lambda" to { higherOrderFunctionWithTrailingLambda({ it * 1 }) { } },
        "It: implicit name of a single parameter" to { itImplicitParameter(1) },
        "Returning value from a lambda" to { lambdaWithReturn(1) },
        "Underscore for unused variables" to { unusedVariable(1) },
        "Function literals with receiver" to { 1.functionLiteralWithReceiver() }
    )

    functionsList.forEach { (functionName, function) ->
        val (time, energy, memory) = measureExecution { function.invoke() }
        println("$functionName - Execution Time: $time ms, Energy Use: $energy, Memory: $memory MB")
    }

    println("\n------------------------------------------------------------------------------------------------------\n")
    val functionsWithoutLambdaList: List<Pair<String, () -> Int>> = listOf(
        "Higher-order function without lambda" to ::higherOrderFunctionWithoutLambda,
        "Function types without lambda" to { functionTypesWithoutLambda(1) },
        "Instantiating function type without lambda" to { instantiatingFunctionTypeWithoutLambda(3) },
        "Invoking function type instance without lambda" to ::invokingFunctionTypeInstanceWithoutLambda,
        "Inline function without lambda" to ::inlineFunctionWithoutLambda,
        "Lambda expression syntax without lambda" to { lambdaExpressionSyntaxWithoutLambda },
        "Passing trailing lambda without lambda" to ::higherOrderFunctionWithTrailingLambdaWithoutLambda,
        "It: implicit name of a single parameter without lambda" to { itImplicitParameterWithoutLambda(1) },
        "Returning value from a lambda without lambda" to ::lambdaWithReturnWithoutLambda,
        "Underscore for unused variables without lambda" to ::unusedVariableWithoutLambda,
        "Function literals with receiver without lambda" to { functionLiteralWithReceiverWithoutLambda(1) }
    )

    functionsWithoutLambdaList.forEach { (functionName, function) ->
        val (time, energy, memory) = measureExecution { function.invoke() }
        println("$functionName - Execution Time: $time ms, Energy Use: $energy, Memory: $memory MB")
    }
}

// Function to measure execution time, energy use, and memory
fun <T> measureExecution(function: () -> T): Triple<Long, String, Long> {
    val startMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()
    val startTime = System.currentTimeMillis()
    function.invoke()
    val endTime = System.currentTimeMillis()
    val endMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()

    val executionTime = endTime - startTime
    val memoryUsage = (endMemory - startMemory) / (1024 * 1024) // Convert bytes to megabytes

    // Simulating energy use with a placeholder string
    val energyUse = "High"

    return Triple(executionTime, energyUse, memoryUsage)
}