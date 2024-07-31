package com.example.sidewayloan.ui.screens.history_screen

import android.media.Image
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.Divider
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sidewayloan.R
import com.example.sidewayloan.ui.composables.loan_history.LoanHistory
import com.example.sidewayloan.ui.theme.outlineDark
import com.example.sidewayloan.ui.theme.outlineVariantLight
import com.example.sidewayloan.ui.theme.primaryDark
import com.example.sidewayloan.ui.theme.primaryLight

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

        LoanHistory(
            modifier = Modifier.padding(padding),
            loanList = loanList
        )
    }
}