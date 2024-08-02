package com.example.sidewayloan.ui.composables.loan_list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sidewayloan.data.database.loan.Loan

@Composable
fun LoanList(
    modifier: Modifier = Modifier,
    loanList: State<List<Loan>>
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Row (modifier = Modifier
            .padding(
                start = 20.dp,
                top = 20.dp,
                end = 20.dp
            )
        ) {
            Text(
                text = "Loan History",
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp
            )
        }

        LazyColumn {
            items(loanList.value) { item ->
                LoanListItem(item)
            }
        }
    }
}