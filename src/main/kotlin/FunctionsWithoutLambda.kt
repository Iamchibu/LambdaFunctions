public class FunctionsWithoutLambda {

    // Global constant
    val RANGE = 2000

    // 1. Higher-order functions without lambda
    private fun higherOrderFunctionWithoutLambda():

            Int {
        return (1..RANGE).sum()
    }

    // 2. Function types without lambda
    private fun functionTypesWithoutLambda(value: Int):

            Int {
        return (1..RANGE).sumBy {
            value * 1
        }
    }

    // 3. Instantiating a function type without lambda
    private fun instantiatingFunctionTypeWithoutLambda(value: Int):

            Int {
        return higherOrderFunctionWithoutLambda() * value
    }

    // 4. Invoking a function type instance without lambda
    private fun invokingFunctionTypeInstanceWithoutLambda():

            Int {
        return higherOrderFunctionWithoutLambda() * higherOrderFunctionWithoutLambda()
    }

    // 5. Inline functions without lambda
    private inline fun inlineFunctionWithoutLambda():

            Int {
        return (1..RANGE).sum()
    }

    // 6. Lambda expression syntax without lambda
    val lambdaExpressionSyntaxWithoutLambda: Int = (1..RANGE).sum()

    // 7. Passing trailing lambdas without lambda
    private fun higherOrderFunctionWithTrailingLambdaWithoutLambda():

            Int {
        val trailingLambda: () -> Unit = {}
        trailingLambda()
        return (1..RANGE).sum()
    }

    // 8. It: implicit name of a single parameter without lambda
    private fun itImplicitParameterWithoutLambda(value: Int):

            Int {
        return value + 5
    }

    // 9. Returning a value from a lambda expression without lambda
    private fun lambdaWithReturnWithoutLambda():

            Int {
        return (1..RANGE).sumBy {
            it * 1
        }
    }

    // 10. Underscore for unused variables without lambda
    private fun unusedVariableWithoutLambda():

            Int {
        return (1..RANGE).sum()
    }

    // 11. Function literals with receiver without lambda
    private fun functionLiteralWithReceiverWithoutLambda(receiver: Int):

            Int {
        return (receiver..receiver + (RANGE - 1)).sum()
    }


    fun main() {
        println("\n------------------------------------------------------------------------------------------------------\n")
        val functionsWithoutLambdaList: List<Pair<String, () -> Int>> = listOf(
            "Higher-order function without lambda" to ::higherOrderFunctionWithoutLambda,
            "Function types without lambda" to {
                functionTypesWithoutLambda(1)
            },
            "Instantiating function type without lambda" to {
                instantiatingFunctionTypeWithoutLambda(3)
            },
            "Invoking function type instance without lambda" to ::invokingFunctionTypeInstanceWithoutLambda,
            "Inline function without lambda" to ::inlineFunctionWithoutLambda,
            "Lambda expression syntax without lambda" to {
                lambdaExpressionSyntaxWithoutLambda
            },
            "Passing trailing lambda without lambda" to ::higherOrderFunctionWithTrailingLambdaWithoutLambda,
            "It: implicit name of a single parameter without lambda" to {
                itImplicitParameterWithoutLambda(1)
            },
            "Returning value from a lambda without lambda" to ::lambdaWithReturnWithoutLambda,
            "Underscore for unused variables without lambda" to ::unusedVariableWithoutLambda,
            "Function literals with receiver without lambda" to {
                functionLiteralWithReceiverWithoutLambda(1)
            }
        )

        functionsWithoutLambdaList.forEach { (functionName, function) ->
            val (time, energy, memory) = measureExecution { function.invoke() }
            println("$functionName - Execution Time: $time ms, Energy Use: $energy, Memory: $memory MB")
        }
    }

    // Function to measure execution time, energy use, and memory
    private fun <T> measureExecution(function: () -> T): Triple<Long, String, Long> {
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
}