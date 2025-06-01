package com.example.sidewayloan.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import androidx.datastore.core.DataStore
import androidx.navigation.NavHostController
import com.example.sidewayloan.data.database.loan.Loan
import com.example.sidewayloan.data.database.loan.LoanType
import com.example.sidewayloan.navigation.MainRoute
import com.example.sidewayloan.ui.composables.calculation_result.CalculationResultSheet
import com.example.sidewayloan.ui.composables.ChipGroup
import com.example.sidewayloan.ui.composables.TopAppBarWithReturn
import com.example.sidewayloan.ui.composables.date_picker_field.DatePickerField
import com.example.sidewayloan.data.database.loan.LoanViewModel
import com.example.sidewayloan.data.datastore.user_settings.UserSettings
import com.example.sidewayloan.ui.composables.BottomButton
import com.example.sidewayloan.ui.composables.CurrencyField
import com.example.sidewayloan.ui.composables.OutlinedNumberField
import com.example.sidewayloan.utils.convertDateToMillis
import com.example.sidewayloan.utils.convertMillisToDate
import java.time.Instant
import java.time.LocalDate
import java.time.Period
import java.time.ZoneId
import java.time.ZoneOffset

fun calculateMaximumTenure(type: LoanType, birthday: Long): Int {
    val instant = Instant.ofEpochMilli(birthday)
    val age = Period.between(
        instant.atZone(ZoneOffset.UTC).toLocalDate(),
        LocalDate.now()
    ).years

    val defaultMaxTenure = when(type) {
        LoanType.PERSONAL -> 10
        LoanType.HOUSING -> 35
    }

    val defaultMaxAge = when(type) {
        LoanType.PERSONAL -> 60
        LoanType.HOUSING -> 75
    }

    val ageDifference = defaultMaxAge - age
    if (ageDifference <= 0 || age > defaultMaxAge) {
        return 0
    }

    if (ageDifference < defaultMaxTenure) {
        return ageDifference * 12
    }

    if (ageDifference > defaultMaxTenure) {
        return defaultMaxTenure * 12
    }

    return 0
}

@Composable
fun CalculatorScreen(
    navHostController: NavHostController,
    loanViewModel: LoanViewModel,
    userSettingsDataStore: DataStore<UserSettings>
) {
    var type by remember { mutableStateOf(LoanType.PERSONAL) }
    var amount by remember { mutableStateOf("") }
    var interestRate by remember { mutableStateOf("") }
    var numberOfInstalment by remember { mutableStateOf("") }

    val currentTime = System.currentTimeMillis()
    var startDate by remember {
        mutableStateOf(convertMillisToDate(currentTime))
    }

    var showBottomSheet by remember { mutableStateOf(false) }

    val storedBirthday = userSettingsDataStore
        .data
        .collectAsState(
            initial = UserSettings()
        )
        .value
        .birthday

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBarWithReturn(
                title = "Loan Calculator",
                description = "Loan Calculator TopAppBar",
                navigateBack = {
                    navHostController.popBackStack()
                },
            )
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
            ChipGroup(
                label = "Select Loan Type",
                options = LoanType.entries,
                onValueChange = { type = LoanType.entries.toTypedArray()[it] }
            )

            CurrencyField(
                label = "Loan Amount",
                initialValue = amount,
                onValueChange = { amount = it }
            )

            CurrencyField(
                label = "Interest Rate (% per annum)",
                initialValue = interestRate,
                onValueChange = { interestRate = it }
            )

            val maximumTenure = storedBirthday?.let { calculateMaximumTenure(type, it) }
            OutlinedNumberField(
                label="Loan Tenure (months - maximum = $maximumTenure)",
                value = numberOfInstalment,
                onValueChange = {
                    if (it.isDigitsOnly()) {
                        val inputToInt = if (it.isNotEmpty()) it.toInt() else 0

                        if (maximumTenure != null) {
                            if (maximumTenure >= inputToInt) {
                                numberOfInstalment = it
                            }
                        }
                    }
                }
            )

            DatePickerField(
                modifier = Modifier.fillMaxWidth(),
                label = "Loan Start Date",
                selectedDate = startDate,
                onSelectDate = { startDate = it }
            )

            BottomButton(
                label = "Calculate",
                onClick = {
                    showBottomSheet = true
                }
            )
        }

        if (showBottomSheet) {
            val loan = Loan(
                type = type,
                amount = amount.toBigDecimal(),
                interestRate = interestRate.toBigDecimal(),
                numberOfInstalment = numberOfInstalment.toInt(),
                startDateUnixTime = convertDateToMillis(startDate)
            )

            CalculationResultSheet(
                loan = loan,
                onDismissRequest = {
                    showBottomSheet = false
                }
            ) {
                Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    Button(
                        modifier = Modifier.weight(0.5f),
                        onClick = {
                            loanViewModel.upsertLoan(loan)
                            showBottomSheet = false
                            navHostController.navigate(MainRoute)
                        }
                    ) {
                        Text("Save Loan")
                    }

                    Button(
                        modifier = Modifier.weight(0.5f),
                        onClick = {
                            showBottomSheet = false
                            navHostController.navigate(MainRoute)
                        }
                    ) {
                        Text("Back Home")
                    }
                }
            }
        }
    }
}
