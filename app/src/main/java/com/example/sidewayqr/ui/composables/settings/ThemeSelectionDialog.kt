package com.example.sidewayqr.ui.composables.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.sidewayqr.ui.theme.ThemeManager

@Composable
fun ThemeSelectionDialog(onDismiss: () -> Unit) {
    val context = LocalContext.current
    val options = listOf(
        ThemeManager.LIGHT_MODE,
        ThemeManager.DARK_MODE,
        ThemeManager.SYSTEM_DEFAULT
    )

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Select Theme") },
        text = {
            Column {
                options.forEach { theme ->
                    Text(
                        text = theme,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                ThemeManager.saveTheme(context, theme)
                                onDismiss()
                            }
                            .padding(16.dp)
                    )
                }
            }
        },
        confirmButton = {
            Button(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}