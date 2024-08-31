package com.example.sidewayqr.ui.screens.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Brightness4
import androidx.compose.material.icons.filled.DataUsage
import androidx.compose.material.icons.filled.Language
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.sidewayqr.ui.theme.ThemeManager

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GeneralSettingsScreen(navController: NavController) {
    var showThemeDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("General Settings") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            item {
                SettingsOption("Language", Icons.Default.Language) {
                    navController.navigate("language_selection")
                }
            }
            item { Divider() }
            item {
                SettingsOption("Theme", Icons.Default.Brightness4) {
                    showThemeDialog = true
                }
            }
            item { Divider() }
            item {
                SettingsOption("Data Usage", Icons.Default.DataUsage) {
                    navController.navigate("data_usage")
                }
            }
        }
        if (showThemeDialog) {
            ThemeSelectionDialog(onDismiss = { showThemeDialog = false })
        }
    }
}

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