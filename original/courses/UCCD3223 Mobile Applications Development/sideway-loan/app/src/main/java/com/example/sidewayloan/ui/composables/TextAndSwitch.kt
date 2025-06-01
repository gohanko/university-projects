package com.example.sidewayloan.ui.composables

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun TextAndSwitch(
    label: String,
    checked: Boolean,
    onCheckChange: () -> Unit,
) {
    Row (
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = label)

        Spacer(Modifier.weight(1f))

        Switch(
            checked = checked,
            onCheckedChange = { onCheckChange() }
        )
    }
}
