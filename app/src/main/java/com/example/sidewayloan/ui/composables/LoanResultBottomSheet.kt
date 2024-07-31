package com.example.sidewayloan.ui.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.breens.beetablescompose.BeeTablesCompose
import com.example.sidewayloan.data.database.loan.Loan
import com.example.sidewayloan.ui.screens.history_screen.HistoryViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoanResultBottomSheet(
    historyViewModel: HistoryViewModel,
    loan: Loan, onDismissRequest: () -> Unit
) {
    var showAmortisationTable by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState()

    ModalBottomSheet(
        sheetState = sheetState,
        onDismissRequest = { onDismissRequest() },
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 20.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            val monthlyInstallment = loan.getMonthlyInstalment()
            Text(text = "Monthly Instalment: $monthlyInstallment")

            val lastRepaymentDate = loan.getLastPaymentDate()
            Text(text="Final Payment Date: $lastRepaymentDate")

            val totalAmountPaid = loan.getTotalAmountPaid()

            Text(text="Total Amount Paid: $totalAmountPaid")

            if (showAmortisationTable) {
                val headerTitles = listOf("Payment Number", "Beginning Balance (RM)", "Monthly Repayment (RM)", "Interest Paid (RM)", "Principal Paid (RM)")
                BeeTablesCompose(
                    data = loan.getTableDataset(),
                    headerTableTitles = headerTitles
                )
            }

            Row (
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Show Amortisation Table"
                )

                Spacer(Modifier.weight(1f))

                Switch(
                    checked = showAmortisationTable,
                    onCheckedChange = { showAmortisationTable = !showAmortisationTable }
                )
            }

            Row(
                modifier = Modifier,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Button(
                    modifier = Modifier.weight(0.5f),
                    onClick = {
                        historyViewModel.upsertLoan(loan)
                    }
                ) {
                    Text("Save Loan")
                }

                Button(
                    modifier = Modifier.weight(0.5f),
                    onClick = { /*TODO*/ }
                ) {
                    Text("Back Home")
                }
            }
        }
    }
}