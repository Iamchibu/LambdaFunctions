package com.example.lambdafunctions

import android.os.BatteryManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.lambdafunctions.ui.theme.LambdaFunctionsTheme

const val RANGE = 10000

class MainActivity : ComponentActivity() {
    private val batteryManager by lazy {
        getSystemService(BATTERY_SERVICE) as BatteryManager
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LambdaFunctionsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainActivityContent()
                }
            }
        }
    }

    @Composable
    fun MainActivityContent() {
        var result by remember { mutableStateOf("") }

        LaunchedEffect(Unit) {
            result = executeFunctions()
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Button(onClick = { result = executeFunctions() }) {
                    Text(text = "Run Functions")
                }
            }

            item {
                Text(text = result)
            }
        }
    }

    private fun executeFunctions(): String {
        val logMessages = StringBuilder()

        // Function 1: Higher-order functions with lambda
        logMessages.append(executeAndLog("Higher-order function with lambda", {
            higherOrderFunction { it }
        },::measureBatteryUsage))

        // Function 2: Function types with lambda
        logMessages.append(executeAndLog("Function types with lambda", {
            functionTypes(1)
        },::measureBatteryUsage))

        // Function 3: Instantiating function type
        logMessages.append(executeAndLog("Instantiating function type with lambda", {
            instantiatingFunctionType(1)
        },::measureBatteryUsage))

        // Function 4: Invoking function type instance
        logMessages.append(executeAndLog("Invoking function type instance with lambda", {
            invokingFunctionTypeInstance(1)
        },::measureBatteryUsage))

        // Function 5: Inline function
        logMessages.append(executeAndLog("Inline function with lambda", {
            inlineFunctions { it * 1 }
        },::measureBatteryUsage))

        // Function 6: Lambda expression syntax
        logMessages.append(executeAndLog("Lambda expression syntax with lambda", {
            lambdaExpressionSyntax()
        },::measureBatteryUsage))

        // Function 7: Passing trailing lambda
        logMessages.append(executeAndLog("Passing trailing lambda with lambda", {
            higherOrderFunctionWithTrailingLambda({ it * 1 }){}
        }, ::measureBatteryUsage))

        // Function 8: It: implicit name of a single parameter
        logMessages.append(executeAndLog("It: implicit name of a single parameter with lambda", {
            itImplicitParameter(RANGE)
        }, ::measureBatteryUsage))

        // Function 9: Returning value from a lambda
        logMessages.append(executeAndLog("Returning value from a lambda with lambda", {
            lambdaWithReturn(1)
        }, ::measureBatteryUsage))

        // Function 10: Underscore for unused variables
        logMessages.append(executeAndLog("Underscore for unused variables with lambda", {
            unusedVariable(1)
        }, ::measureBatteryUsage))

        // Function 11: Function literals with receiver with lambda
        logMessages.append(executeAndLog("Function literals with receiver with lambda", {
            1.functionLiteralWithReceiver()
        }, ::measureBatteryUsage))

        logMessages.append(executeAndLog("-------------------------------------------",{}, ::measureBatteryUsage))

        // Function 1: Higher-order function without lambda
        logMessages.append(executeAndLog("Higher-order function without lambda", {
            higherOrderFunctionWithoutLambda()
        }, ::measureBatteryUsage))

        // Function 2: Function types without lambda
        logMessages.append(executeAndLog("Function types without lambda", {
            functionTypesWithoutLambda(1)
        }, ::measureBatteryUsage))

        // Function 3: Instantiating function type without lambda
        logMessages.append(executeAndLog("Instantiating function type without lambda", {
            instantiatingFunctionTypeWithoutLambda(1)
        }, ::measureBatteryUsage))

        // Function 4: Invoking function type instance without lambda
        logMessages.append(executeAndLog("Invoking function type instance without lambda", {
            invokingFunctionTypeInstanceWithoutLambda()
        }, ::measureBatteryUsage))

        // Function 5: Inline function without lambda
        logMessages.append(executeAndLog("Inline function without lambda", {
            inlineFunctionWithoutLambda()
        }, ::measureBatteryUsage))

        // Function 6: Lambda expression syntax without lambda
        logMessages.append(executeAndLog("Lambda expression syntax without lambda", {
            lambdaExpressionSyntaxWithoutLambda
        }, ::measureBatteryUsage))

        // Function 7: Passing trailing lambda without lambda
        logMessages.append(executeAndLog("Passing trailing lambda without lambda", {
            higherOrderFunctionWithTrailingLambdaWithoutLambda()
        }, ::measureBatteryUsage))

        // Function 8: It: implicit name of a single parameter without lambda
        logMessages.append(executeAndLog("It: implicit name of a single parameter without lambda", {
            itImplicitParameterWithoutLambda(RANGE)
        }, ::measureBatteryUsage))

        // Function 9: Returning value from a lambda without lambda
        logMessages.append(executeAndLog("Returning value from a lambda without lambda", {
            lambdaWithReturnWithoutLambda()
        }, ::measureBatteryUsage))

        // Function 10: Underscore for unused variables without lambda
        logMessages.append(executeAndLog("Underscore for unused variables without lambda", {
            unusedVariableWithoutLambda()
        }, ::measureBatteryUsage))

        // Function 11: Function literals with receiver without lambda
        logMessages.append(executeAndLog("Function literals with receiver without lambda", {
            functionLiteralWithReceiverWithoutLambda(RANGE)
        }, ::measureBatteryUsage))

//         Log the combined information
        val logMessage = logMessages.toString()
        displayResult(logMessage)

        // Log the information to Logcat
        Log.d("FunctionExecution", logMessage)

        return logMessage
    }

    // Add battery usage measurement function
    private fun measureBatteryUsage(): Double {
        val startBatteryStatus = batteryManager.getIntProperty(BatteryManager.BATTERY_PROPERTY_ENERGY_COUNTER)
        val startTime = System.currentTimeMillis()

        // Run a placeholder function to simulate energy consumption
        val placeholderFunction = { (1..RANGE).sumBy { it * 1 } }
        placeholderFunction()

        val endBatteryStatus = batteryManager.getIntProperty(BatteryManager.BATTERY_PROPERTY_ENERGY_COUNTER)
        val endTime = System.currentTimeMillis()

        // Calculate energy spent during function execution
        val energySpent = maxOf((endBatteryStatus - startBatteryStatus).toDouble() / 1000000.0, 0.0) // Ensure it's above 0.0

        // Calculate time spent during function execution
        val executionTime = maxOf(endTime - startTime, 1) // Ensure executionTime is at least 1 to avoid division by zero

        return energySpent / executionTime.toDouble() // Energy consumption per millisecond
    }

    // Higher-order functions
    private fun higherOrderFunction(operation: (Int) -> Int): Int {
        val result = (1..RANGE).sumBy(operation)

        return result
    }

    // Function types
    val functionTypes: (Int) -> Int = { value ->
        val result = (1..RANGE).sumBy { it * value }
        result
    }

    // Instantiating a function type
    val instantiatingFunctionType: (Int) -> Int = { value ->
        val result = higherOrderFunction { it * value }
        result
    }

    // Invoking a function type instance
    val invokingFunctionTypeInstance: (Int) -> Int = { it ->
        val result = higherOrderFunction { it * 1 }
        result
    }

    // Inline functions
    private inline fun inlineFunctions(operation: (Int) -> Int): Int {
        val result = (1..RANGE).sumBy(operation)
        return result
    }

    // Lambda expression syntax
    val lambdaExpressionSyntax: () -> Int = { (1..RANGE).sum() }

    // Passing trailing lambdas
    private fun higherOrderFunctionWithTrailingLambda(
        operation: (Int) -> Int,
        trailing: () -> Unit
    ): Int {
        trailing()
        val result = (1..RANGE).sumBy(operation)
        return result
    }

    // It: implicit name of a single parameter
    val itImplicitParameter: (Int) -> Int = { (1..it).sum() }

    // Returning a value from a lambda expression
    val lambdaWithReturn: (Int) -> Int = {
        val result = (1..RANGE).sumBy { it * 1 }
        result
    }

    // Underscore for unused variables
    val unusedVariable: (Int) -> Int = { _ ->
        val result = (1..RANGE).sum()
        result
    }

    // Function literals with receiver
    val functionLiteralWithReceiver: Int.() -> Int = {
        val result = (this..this + (RANGE - 1)).sum()
        result
    }


    // 1. Higher-order functions without lambda
    private fun higherOrderFunctionWithoutLambda(): Int {
        return (1..RANGE).sum()
    }

    // 2. Function types without lambda
    private fun functionTypesWithoutLambda(value: Int): Int {
        return (1..RANGE).sumBy { value * 1 }
    }

    // 3. Instantiating a function type without lambda
    private fun instantiatingFunctionTypeWithoutLambda(value: Int): Int {
        return higherOrderFunctionWithoutLambda() * value
    }

    // 4. Invoking a function type instance without lambda
    private fun invokingFunctionTypeInstanceWithoutLambda(): Int {
        return higherOrderFunctionWithoutLambda() * higherOrderFunctionWithoutLambda()
    }

    // 5. Inline functions without lambda
    private inline fun inlineFunctionWithoutLambda(): Int {
        return (1..RANGE).sum()
    }

    // 6. Lambda expression syntax without lambda
    private val lambdaExpressionSyntaxWithoutLambda: Int = (1..RANGE).sum()

    // 7. Passing trailing lambdas without lambda
    private fun higherOrderFunctionWithTrailingLambdaWithoutLambda(): Int {
        val trailingLambda: () -> Unit = {}
        trailingLambda()
        return (1..RANGE).sum()
    }

    // 8. It: implicit name of a single parameter without lambda
    private fun itImplicitParameterWithoutLambda(value: Int): Int {
        return (1..value).sum()
    }

    // 9. Returning a value from a lambda expression without lambda
    private fun lambdaWithReturnWithoutLambda(): Int {
        return (1..RANGE).sumBy { it * 1 }
    }

    // 10. Underscore for unused variables without lambda
    private fun unusedVariableWithoutLambda(): Int {
        return (1..RANGE).sum()
    }

    // 11. Function literals with receiver without lambda
    private fun functionLiteralWithReceiverWithoutLambda(receiver: Int): Int {
        return (receiver..receiver + (RANGE - 1)).sum()
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

    // Helper function to execute and log the information for a specific function
    private fun <T> executeAndLog(
        functionName: String,
        function: () -> T,
        batteryUsage: () -> Double
    ): String {
        // Measure battery usage before executing the function
        val initialBatteryUsage = batteryUsage()

        val (time, _, memory) = measureExecution {
            function()
        }

        // Measure battery usage after executing the function
        val finalBatteryUsage = batteryUsage()

        // Calculate energy reduction correctly
        val energyReduction = maxOf(finalBatteryUsage - initialBatteryUsage, 0.0)

        val logMessage =
            "$functionName:\nExecution Time: $time ms\nMemory: $memory MB\nBattery: $energyReduction mAh\n\n"
        return logMessage
    }

    private fun displayResult(result: String) {
        // Display the result, you can use TextView or Toast as per your UI design
        Toast.makeText(this, result, Toast.LENGTH_LONG).show()
    }
}