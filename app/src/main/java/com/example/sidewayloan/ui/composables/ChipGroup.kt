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
fun ChipGroup(enumEntries: EnumEntries<LoanType>, onValueChange: (Int) -> Unit) {
    var selectedId by remember { mutableIntStateOf(0) }

    Row(
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        for (entry in enumEntries) {
            FilterChip(
                selected = selectedId == entry.ordinal,
                onClick = {
                    selectedId = entry.ordinal
                    onValueChange(selectedId)
                },
                label = { Text(entry.getPrintable()) }
            )
        }
    }
}