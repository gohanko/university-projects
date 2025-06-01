package com.example.sidewayloan.ui.composables.calculation_result

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.breens.beetablescompose.BeeTablesCompose
import com.example.sidewayloan.data.database.loan.Loan
import com.example.sidewayloan.data.database.loan.LoanType
import com.example.sidewayloan.ui.composables.DropdownField
import com.example.sidewayloan.ui.composables.TextAndSwitch

@Composable
fun CalculationResult(
    loan: Loan
) {
    var showAmortisationTable by remember { mutableStateOf(false) }
    var selectedAmortisationItem: String by remember { mutableStateOf("1") }

    Text(text = "Monthly Instalment: ${loan.getMonthlyInstalment()}")
    Text(text = "Final Payment Date: ${loan.getLastPaymentDate()}")
    Text(text="Total Amount Paid: ${loan.getTotalAmountPaid()}")

    val amortisationTableData = loan.getAmortisationTableDataset()
    DropdownField(
        selectedValue = selectedAmortisationItem,
        label = "Calculate Interest on Specific Months",
        options = amortisationTableData.map { it.paymentNumber.toString() },
        onValueChangedEvent = {
            selectedAmortisationItem = it
        }
    )

    val selectedAmountOfInterest = amortisationTableData
        .filter { selectedAmortisationItem.toInt() == it.paymentNumber }[0]
        .interestPaid
    Text(text = "Interest at month: $selectedAmountOfInterest")

    TextAndSwitch(
        label = "Show Amortisation Table",
        checked = showAmortisationTable,
        onCheckChange = { showAmortisationTable = !showAmortisationTable }
    )

    if (showAmortisationTable) {
        val headerTitles = listOf("Payment Number", "Beginning Balance (RM)", "Monthly Repayment (RM)", "Interest Paid (RM)", "Principal Paid (RM)")
        BeeTablesCompose(
            data = amortisationTableData,
            headerTableTitles = headerTitles
        )
    }
}