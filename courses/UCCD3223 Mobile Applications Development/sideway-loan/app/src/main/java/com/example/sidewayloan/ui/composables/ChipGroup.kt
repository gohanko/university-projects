package com.example.sidewayloan.ui.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import com.example.sidewayloan.data.database.loan.LoanType
import kotlin.enums.EnumEntries

@Composable
fun ChipGroup(
    label: String,
    options: EnumEntries<LoanType>,
    onValueChange: (Int) -> Unit
) {
    var selectedId by remember { mutableIntStateOf(0) }

    if (label.isNotEmpty()) {
        Text(text = label)
    }

    Row(
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        for (option in options) {
            FilterChip(
                selected = selectedId == option.ordinal,
                onClick = {
                    selectedId = option.ordinal
                    onValueChange(selectedId)
                },
                label = { Text(option.getPrintable()) }
            )
        }
    }
}