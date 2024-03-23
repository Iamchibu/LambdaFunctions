# iOS Experiment Tests

## Prerequisites

Before running the tests, ensure you have the latest version of **Xcode** installed on your machine.

## Running Tests

To execute the tests, use the command below in your terminal:

```bash
xcodebuild TEST_RANGE='10000' test -verbose -scheme Lambda -destination 'platform=iOS,arch=arm64,id=<DEVICE_ID_HERE>,name=<DEVICE_NAME_HERE>' -resultBundlePath TestResults10k
```

**Note:**
- Replace `<DEVICE_ID_HERE>` and `<DEVICE_NAME_HERE>` with the actual device ID and name of your testing device.
- To find your device ID and name, execute: `xcodebuild -showdestinations -scheme Lambda`

### Test Range

- The `TEST_RANGE` environment variable defines the range of numbers for testing, with a default value of `10000`. This value can be adjusted as needed.

### Test Results

- Results are stored in the `TestResults10k` directory.
- Open `TestResults10k/TestSummingNumbers.xcresult` in Xcode to view detailed test outcomes.

## Test Suite

The test suite includes 11 performance test cases, covering various aspects of Lambda function performance:

- `testFunctionLiteralWithReceiver()`
- `testFunctionLiteralWithReceiverWithoutLambda()`
- `testFunctionTypes()`
- `testFunctionTypesWithoutLambda()`
- `testHigherOrderFunction1()`
- `testHigherOrderFunctionWithoutLambda1()`
- `testHigherOrderFunctionWithTrailingLambda()`
- `testHigherOrderFunctionWithTrailingLambdaWithoutLambda()`
- `testInlineFunctions()`
- `testInlineFunctionWithoutLambda()`
- `testInstantiatingFunctionType()`
- `testInstantiatingFunctionTypeWithoutLambda()`
- `testInvokingFunctionTypeInstance()`
- `testInvokingFunctionTypeInstanceWithoutLambda()`
- `testItImplicitParameter()`
- `testItImplicitParameterWithoutLambda()`
- `testLambdaExpressionSyntax()`
- `testLambdaExpressionSyntaxWithoutLambda()`
- `testLambdaWithReturn()`
- `testLambdaWithReturnWithoutLambda()`
- `testUnusedVariable()`
- `testUnusedVariableWithoutLambda()`

### Performance Metrics

Each test case evaluates the following performance metrics:

- **Clock Metric**: Measures the execution time.
- **Memory Metric**: Assesses the memory usage.
- **CPU Metric**: Evaluates the CPU consumption.
- **Energy Metric**: Estimates the energy impact.