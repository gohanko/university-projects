package com.example.sidewayloan.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.sidewayloan.ui.composables.CalculatorTopAppBar

@Composable
fun CalculatorScreen(navHostController: NavHostController) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CalculatorTopAppBar { navHostController.popBackStack() }
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            // Loan Type Dropdown
            // Number Field (Currency)
            // Interest Rate (Float)
            // Number of Payment (Integer)
            //
        }
    }
}