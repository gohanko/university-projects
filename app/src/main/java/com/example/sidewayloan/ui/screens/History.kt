package com.example.sidewayloan.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.sidewayloan.ui.composables.CalculatorFAB

@Composable
fun HistoryScreen(openCalculator: () -> Unit) {
    Scaffold(
        floatingActionButton = { CalculatorFAB(openCalculator) }
    ) { padding ->
        Column(modifier = Modifier.padding((padding))) {

        }
    }
}