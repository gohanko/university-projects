package com.example.sidewayloan.ui.screens.history_screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.example.sidewayloan.ui.composables.loan_list.LoanList

@Composable
fun HistoryScreen(
    historyViewModel: HistoryViewModel,
    openCalculator: () -> Unit
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { openCalculator() }) {
                Icon(Icons.Filled.Add, "Calculate Loans")
            }
        }
    ) { padding ->
        val loanList = historyViewModel.getLoansOrderedByID().collectAsState(initial = emptyList())

        LoanList(
            modifier = Modifier.padding(padding),
            loanList = loanList
        )
    }
}