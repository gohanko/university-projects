package com.example.sidewayloan.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.sidewayloan.ui.composables.CalculatorTopAppBar

@Preview
@Composable
fun CalculatorScreen(navHostController: NavHostController) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CalculatorTopAppBar { navHostController.popBackStack() }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 20.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            // Loan Type Dropdown

            // Number Field (Currency)
            OutlinedTextField(
                label = { Text("Amount") },
                value = "",
                onValueChange = {}
            )
            // Interest Rate (Float)

            OutlinedTextField(
                label = { Text("Interest Rate") },
                value = "",
                onValueChange = {}
            )

            // Number of Payment (Integer)
            OutlinedTextField(
                label = { Text("Repayment Period") },
                value = "",
                onValueChange = {}
            )
        }
    }
}