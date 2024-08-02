package com.example.sidewayloan.ui.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import com.example.sidewayloan.data.database.loan.Loan

@Composable
fun TotalLoanCard(
    loanList: State<List<Loan>>
) {
    var totalLoan = "0.00".toBigDecimal()

    loanList.value.forEach {
        totalLoan += it.amount
    }

    Card(
        modifier = Modifier
            .padding(top = 20.dp, start = 20.dp, end = 20.dp)
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .padding(20.dp)
        ) {
            Text(
                text = "Total Loan",
                fontSize = 16.sp,
            )
            Text(
                text = "RM $totalLoan",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}