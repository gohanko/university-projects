package com.example.sidewayloan.ui.composables

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import com.example.sidewayloan.data.Loan

fun calculatePersonalLoan(principle: Float, rateOfInterest: Float, numberOfInstallments: Int): Float {
    return (principle * (1 + (rateOfInterest * numberOfInstallments))) / numberOfInstallments
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalculatorResultsBottomSheet(loan: Loan) {
    val sheetState = rememberModalBottomSheetState()

    ModalBottomSheet(
        sheetState = sheetState,
        onDismissRequest = {

        },
    ) {

    }
}