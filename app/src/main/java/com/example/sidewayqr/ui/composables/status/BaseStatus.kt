package com.example.sidewayqr.ui.composables.status

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun BaseStatus(
    kaomoji: String,
    statusText: String,
) {
    Box (
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Text(
                text = kaomoji,
                fontWeight = FontWeight.Medium
            )
            Text(
                text = statusText,
                fontWeight = FontWeight.Medium
            )
        }
    }
}