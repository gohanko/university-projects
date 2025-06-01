package com.example.sidewayloan.ui.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun BottomButton(
    label: String,
    onClick: () -> Unit,
) {
    Column {
        Spacer(modifier = Modifier.weight(1f))

        Button(
            modifier = Modifier.padding(vertical = 10.dp, horizontal = 10.dp).fillMaxWidth(),
            onClick = { onClick() }
        ) {
            Text(text = label)
        }
    }
}