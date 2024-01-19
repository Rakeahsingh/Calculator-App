package com.example.calculatorapp.calculatorScreen

import androidx.lifecycle.ViewModel
import com.example.calculatorapp.calculatorScreen.component.CalculatorAction
import com.example.calculatorapp.calculatorScreen.component.CalculatorOperation
import com.example.calculatorapp.calculatorScreen.component.CalculatorState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CalculatorViewModel: ViewModel() {

    private val _state = MutableStateFlow(CalculatorState())
    val state = _state.asStateFlow()

    fun onAction(action: CalculatorAction){
        when(action){
            is CalculatorAction.Calculation -> {
                performCalculation()
            }
            is CalculatorAction.Clear -> {
                _state.value = CalculatorState()
            }
            is CalculatorAction.Decimal -> {
                enterDecimal()
            }
            is CalculatorAction.Delete -> {
                performDelete()
            }
            is CalculatorAction.Number -> {
                enterNumber(action.number)
            }
            is CalculatorAction.Operation -> {
                enterOperation(action.operation)
            }
        }
    }

    private fun enterNumber(number: Int) {
        if (_state.value.operation == null){
            if (_state.value.number1.length >= MAX_LENGTH_VALUE){
                return
            }
            _state.update {
                it.copy(number1 = _state.value.number1 + number)
            }
            return
        }

        if (_state.value.number2.length >= MAX_LENGTH_VALUE){
            return
        }
        _state.update {
            it.copy(number2 = _state.value.number2 + number)
        }

    }

    private fun enterDecimal(){
        if (_state.value.operation == null && !_state.value.number1.contains(".")
            && _state.value.number1.isNotBlank()
            ){
            _state.update {
                it.copy(number1 = _state.value.number1 + ".")
            }
            return
        }
        if ( !_state.value.number2.contains(".")
            && _state.value.number2.isNotBlank()
        ){
            _state.update {
                it.copy(number2 = _state.value.number2 + ".")
            }
            return
        }
    }

    private fun performDelete(){
        when{
            _state.value.number2.isNotBlank() -> _state.update {
                it.copy(number2 = _state.value.number2.dropLast(1))
            }

            _state.value.operation != null -> _state.update {
                it.copy(operation = null)
            }

            _state.value.number1.isNotBlank() -> _state.update {
                it.copy(number1 = _state.value.number1.dropLast(1))
            }
        }
    }

    private fun performCalculation(){
        val number1 = _state.value.number1.toDoubleOrNull()
        val number2 = _state.value.number2.toDoubleOrNull()
    }

   private fun enterOperation(operation: CalculatorOperation){
       if (_state.value.number1.isNotBlank()){
           _state.update {
               it.copy(operation = operation)
           }
       }
   }

    companion object{
       private const val MAX_LENGTH_VALUE = 8
    }
}