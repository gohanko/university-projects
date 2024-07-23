package com.example.sidewayloan.ui.composables

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import com.example.sidewayloan.data.Loan

fun calculateLoan(principle: Float, rateOfInterest: Float, numberOfInstallments: Int): Float {
    return (principle * (1 + (rateOfInterest * numberOfInstallments))) / numberOfInstallments
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalculatorResultsBottomSheet(showBottomSheet: Boolean = false) {
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()

    if (!showBottomSheet) {
        return
    }

    ModalBottomSheet(
        sheetState = sheetState,
        onDismissRequest = {

        },
    ) {
        Text(text = "Sheet!!")
    }
}