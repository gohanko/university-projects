package com.example.sidewayloan.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.sidewayloan.data.LoanType
import com.example.sidewayloan.ui.composables.CalculatorTopAppBar
import com.example.sidewayloan.ui.composables.ChipGroup
import com.example.sidewayloan.ui.composables.DateTextField
import com.example.sidewayloan.utils.convertMillisToDate
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun CalculatorScreen(navHostController: NavHostController) {
    var type by remember { mutableStateOf(LoanType.PERSONAL) }
    var amount by remember { mutableStateOf("") }
    var interestRate by remember { mutableStateOf("") }
    var repaymentPeriod by remember { mutableStateOf("") }

    val currentTime = System.currentTimeMillis()
    var startDate by remember { mutableStateOf(convertMillisToDate(currentTime)) }

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

            ChipGroup(
                LoanType.entries,
                onValueChange = {
                    type = LoanType.valueOf(it)
                }
            )

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Amount") },
                value = amount,
                onValueChange = {
                    amount = it
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Interest Rate") },
                value = interestRate,
                onValueChange = {
                    interestRate = it
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Repayment Period") },
                value = repaymentPeriod,
                onValueChange = {

                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            DateTextField(
                modifier = Modifier.fillMaxWidth(),
                initialSelectedDate = startDate,
                onSelectDate = {
                    startDate = it
                }
            )

            Spacer(modifier = Modifier.weight(1f))

            Box(
                modifier = Modifier.padding(vertical = 10.dp, horizontal = 10.dp)
            ) {
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {}
                ) {
                    Text(text = "Calculate")
                }
            }
        }
    }
}
