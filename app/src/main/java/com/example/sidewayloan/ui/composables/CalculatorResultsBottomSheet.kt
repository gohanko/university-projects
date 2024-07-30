package com.example.sidewayloan.ui.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.breens.beetablescompose.BeeTablesCompose
import com.example.sidewayloan.data.Loan
import com.example.sidewayloan.data.LoanType
import com.example.sidewayloan.utils.convertMillisToDate
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDate
import java.time.Period
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import kotlin.math.pow
import com.example.libraries.BeeTableCompose
import com.example.sidewayloan.data.AmortisationRow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalculatorResultsBottomSheet(loan: Loan, onDismissRequest: () -> Unit) {
    val sheetState = rememberModalBottomSheetState()

    fun calculateMonthlyInstallment(
        loanType: LoanType,
        principle: Float,
        rateOfInterest: Float,
        numberOfInstalments: Int
    ): Float {
        return when(loanType) {
            LoanType.PERSONAL -> (principle * (1 + (rateOfInterest * numberOfInstalments))) / numberOfInstalments
            LoanType.HOUSING -> (principle * rateOfInterest * (1 + rateOfInterest).pow(numberOfInstalments)) / (1 + rateOfInterest).pow(numberOfInstalments) - 1
        }
    }

    fun calculateLastPaymentDate(loanDate: Long, numberOfInstallments: Int): String {
        val date = LocalDate.parse(convertMillisToDate(loanDate), DateTimeFormatter.ofPattern("dd/MM/yyyy")).plusYears(numberOfInstallments.toLong())
        return date.toString()
    }

    fun calculateTotalAmountPaid(loanType: LoanType, principle: Float, rateOfInterest: Float, numberOfInstalments: Int): Float {
        return calculateMonthlyInstallment(loanType, principle, rateOfInterest, numberOfInstalments) * numberOfInstalments
    }

    fun generateTableDataset(loanType: LoanType, principle: Float, rateOfInterest: Float, numberOfInstalments: Int) {
        val tableDataset = listOf<AmortisationRow>()

        val monthlyInstalment = calculateMonthlyInstallment(loanType, principle, rateOfInterest, numberOfInstalments)

        repeat(numberOfInstalments) { index ->
            val amortisationRow = AmortisationRow(
                paymentNumber = index,
                beginningBalance =
            )


        }


        return tableDataset
    }

    ModalBottomSheet(
        sheetState = sheetState,
        onDismissRequest = { onDismissRequest() },
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 20.dp),
        ) {
            val monthlyInstallment = calculateMonthlyInstallment(
                loan.type,
                loan.amount.toFloat(),
                loan.interestRate,
                loan.repaymentPeriod.toInt()
            )

            Text(text = "Monthly Instalment: $monthlyInstallment")

            val lastRepaymentDate = calculateLastPaymentDate(loan.startDateUnixTime, loan.repaymentPeriod.toInt())
            Text(text="Final Payment Date: $lastRepaymentDate")

            val totalAmountPaid = calculateTotalAmountPaid(
                loan.type,
                loan.amount.toFloat(),
                loan.interestRate,
                loan.repaymentPeriod.toInt()
            )

            Text(text="Total Amount Paid: $totalAmountPaid")

            val headerTitles = listOf("Payment Number", "Beginning Balance (RM)", "Monthly Repayment (RM)", "Interest Paid (RM)", "Principal Paid (RM)")
            BeeTablesCompose(data = listOf(), headerTableTitles = headerTitles)
        }
    }
}