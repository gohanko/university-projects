package com.example.sidewayloan.ui.composables

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable

@Composable
fun CalculatorFAB (openCalculator: () -> Unit) {
    FloatingActionButton(onClick = { openCalculator() }) {
        Icon(Icons.Filled.Add, "Calculate Loans")
    }
}
