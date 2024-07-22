package com.example.sidewayloan.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.sidewayloan.data.Loan
import com.example.sidewayloan.data.LoanType
import com.example.sidewayloan.ui.composables.CalculatorTopAppBar

@Composable
fun CalculatorScreen(navHostController: NavHostController) {
    var loanState by remember {
        mutableStateOf(Loan(
            type = LoanType.HOUSING,
            amount = 0.0f,
            interestRate = 0.0f,
            repaymentPeriod = 0,
            startDateUnixTime = 0
        ))
    };

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
                .imePadding()
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            // Loan Type Dropdown
            Text(text = "Select Loan Type")

            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                AssistChip(onClick = { /*TODO*/ }, label = { Text("Personal Loan") })
                AssistChip(onClick = { /*TODO*/ }, label = { Text("Housing Loan") })
            }

            // Number Field (Currency)
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Amount") },
                value = "",
                onValueChange = {
                    loanState = loanState.copy(amount = it.toFloat())
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            // Interest Rate (Float)
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Interest Rate") },
                value = "",
                onValueChange = {
                    loanState = loanState.copy(interestRate = it.toFloat())
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            // Number of Payment (Integer)
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Repayment Period") },
                value = "",
                onValueChange = {
                    loanState = loanState.copy(repaymentPeriod = it.toShort())
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            Spacer(modifier = Modifier.weight(1f))

            Box(
                modifier = Modifier.padding(vertical = 10.dp, horizontal = 10.dp)
            ) {
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { /*TODO*/ }
                ) {
                    Text(text = "Calculate")
                }
            }
        }
    }
}

fun calculateLoan(principle: Float, rateOfInterest: Float, numberOfInstallments: Int): Float {
    return (principle * (1 + (rateOfInterest * numberOfInstallments))) / numberOfInstallments
}