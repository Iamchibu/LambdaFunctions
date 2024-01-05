import kotlin.system.measureTimeMillis

class WithoutLambda {
    // 1. Higher-order functions without lambda
    fun higherOrderFunctionWithoutLambda(): Int {
        return (1..10).sum()
    }

    // 2. Function types without lambda
    fun functionTypesWithoutLambda(value: Int): Int {
        return (1..10).sumBy { value * 2 }
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
        return (1..10).sum()
    }

    // 6. Lambda expression syntax without lambda
    val lambdaExpressionSyntaxWithoutLambda: Int = 1 * 3

    // 7. Passing trailing lambdas without lambda
    fun higherOrderFunctionWithTrailingLambdaWithoutLambda(): Int {
        val trailingLambda: () -> Unit = {}
        trailingLambda()
        return (1..10).sum()
    }

    // 8. It: implicit name of a single parameter without lambda
    fun itImplicitParameterWithoutLambda(value: Int): Int {
        return value + 5
    }

    // 9. Returning a value from a lambda expression without lambda
    fun lambdaWithReturnWithoutLambda(): Int {
        return (1..10).sumBy { it * 2 }
    }

    // 10. Underscore for unused variables without lambda
    fun unusedVariableWithoutLambda(): Int {
        return (1..10).sum()
    }

    // 11. Function literals with receiver without lambda
    fun functionLiteralWithReceiverWithoutLambda(receiver: Int): Int {
        return (receiver..receiver + 9).sum()
    }


    fun main() {
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
    fun measureExecution(function: () -> Unit): Triple<Long, String, Long> {
        val startMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()
        val startTime = 1000 * System.currentTimeMillis()
        function.invoke()
        val endTime = 1000 * System.currentTimeMillis()
        val endMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()

        val executionTime = endTime - startTime
        val memoryUsage = (endMemory - startMemory) / (1024 * 1024) // Convert bytes to megabytes

        // Simulating energy use with a placeholder string
        val energyUse = "High"

        return Triple(executionTime, energyUse, memoryUsage)
    }
}