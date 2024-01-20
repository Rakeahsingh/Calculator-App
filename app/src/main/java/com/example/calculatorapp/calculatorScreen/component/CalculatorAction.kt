package com.example.calculatorapp.calculatorScreen.component



sealed class CalculatorAction {

    data class Number(val number: Int): CalculatorAction()

    data object Clear: CalculatorAction()

    data object Delete: CalculatorAction()

    data object Decimal: CalculatorAction()

    data object Calculation: CalculatorAction()

    data class Operation(val operation: CalculatorOperation): CalculatorAction()

}

enum class CalculatorOperation(val symbol: String){
    ADD( "+"),
    SUBTRACT("-"),
    MULTIPLY("*"),
    DIVIDE("/")
}