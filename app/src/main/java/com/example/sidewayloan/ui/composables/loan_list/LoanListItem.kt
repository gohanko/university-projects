package com.example.sidewayloan.ui.composables.loan_list

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sidewayloan.R
import com.example.sidewayloan.data.database.loan.Loan
import com.example.sidewayloan.ui.composables.CalculationResultSheet
import com.example.sidewayloan.ui.theme.outlineVariantLight
import com.example.sidewayloan.ui.theme.primaryLight

@Composable
fun LoanListItem(
    loan: Loan
) {
    var showLoanResultBottomSheet by remember { mutableStateOf(false) }

    Row (
        modifier = Modifier
            .clickable(onClick={
                showLoanResultBottomSheet = true
            })
            .padding(horizontal = 20.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(45.dp)
                    .background(
                        color = primaryLight,
                        shape = RoundedCornerShape(5.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.cash_flow),
                    contentDescription = ""
                )
            }

            Column {
                Text(
                    text = loan.type.toString(),
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                )
                Text(
                    text = loan.amount.toString(),
                    fontWeight = FontWeight.Normal,
                    fontSize = 12.sp,
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        Icon(
            imageVector = Icons.Default.ChevronRight,
            contentDescription = "",
        )
    }

    HorizontalDivider(
        modifier = Modifier.padding(horizontal = 20.dp),
        color = outlineVariantLight
    )

    if (showLoanResultBottomSheet) {
        CalculationResultSheet(
            content = {},
            loan = loan,
            onDismissRequest = {
                showLoanResultBottomSheet = false
            }
        )
    }
}