package com.example.calculatorapp.calculatorScreen.component

data class CalculatorState(
    val number1: String = "",
    val number2: String = "",
    val operation: CalculatorOperation? = null
)
