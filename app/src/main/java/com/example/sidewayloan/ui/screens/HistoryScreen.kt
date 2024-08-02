package com.example.sidewayloan.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.sidewayloan.data.database.loan.LoanViewModel
import com.example.sidewayloan.ui.composables.TotalLoanCard
import com.example.sidewayloan.ui.composables.loan_list.LoanList

@Composable
fun HistoryScreen(
    loanViewModel: LoanViewModel,
    openCalculator: () -> Unit
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { openCalculator() }) {
                Icon(Icons.Filled.Add, "Calculate Loans")
            }
        }
    ) { padding ->
        val loanList = loanViewModel.getLoansOrderedByID().collectAsState(initial = emptyList())

        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            TotalLoanCard(loanList = loanList)

            LoanList(
                modifier = Modifier.padding(padding),
                loanList = loanList
            )
        }
    }
}