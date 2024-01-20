package com.example.calculatorapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.calculatorapp.calculatorScreen.CalculatorScreen
import com.example.calculatorapp.calculatorScreen.CalculatorViewModel
import com.example.calculatorapp.ui.theme.CalculatorAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalculatorAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val viewModel = viewModel<CalculatorViewModel>()
                    val state by viewModel.state.collectAsState()

                    CalculatorScreen(
                        state = state,
                        onAction = viewModel::onAction,
                        modifier = Modifier
                            .padding(horizontal = 8.dp)
                    )

                }
            }
        }
    }
}

