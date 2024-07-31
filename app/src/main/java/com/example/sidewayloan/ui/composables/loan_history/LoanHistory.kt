package com.example.sidewayloan.ui.composables.loan_history

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
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sidewayloan.R
import com.example.sidewayloan.data.database.loan.Loan
import com.example.sidewayloan.ui.theme.outlineVariantLight
import com.example.sidewayloan.ui.theme.primaryLight
import kotlinx.coroutines.flow.Flow

@Composable
fun LoanHistory(
    modifier: Modifier = Modifier,
    loanList: State<List<Loan>>
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Row (modifier = Modifier.padding(start = 20.dp, top = 20.dp, end = 20.dp)) {
            Text(
                text = "Loan History",
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp
            )
        }

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            items(loanList.value) { item ->
                LoanHistoryItem(item)
            }
        }
    }
}