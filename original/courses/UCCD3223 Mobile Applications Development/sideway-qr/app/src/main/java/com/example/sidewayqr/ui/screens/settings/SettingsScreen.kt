package com.example.sidewayqr.ui.screens.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Brightness4
import androidx.compose.material.icons.filled.DataUsage
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
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
import androidx.navigation.NavController
import com.example.sidewayqr.ui.composables.settings.SettingsOption
import com.example.sidewayqr.ui.composables.settings.ThemeSelectionDialog

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    navController: NavController,
) {
    var showThemeDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Settings") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            SettingsOption(
                title = "Account",
                description = "Account Details, Logout, Change Password",
                icon = Icons.Default.Person,
                onClick = {
                    navController.navigate("account_settings")
                }
            )

            SettingsOption(
                title = "Theme",
                description = "Select your theme",
                icon = Icons.Default.Brightness4,
                onClick = {
                    showThemeDialog = true
                }
            )

            SettingsOption(
                title = "Language",
                description = "Choose your language",
                icon = Icons.Default.Language,
                onClick = {
                    navController.navigate("language_selection")
                }
            )

            SettingsOption(
                title = "Notifications",
                description = "Set notifications preferences",
                icon = Icons.Default.Notifications,
                onClick = {
                    navController.navigate("notification_settings")
                }
            )

            SettingsOption(
                title = "Data Usage",
                description = "",
                icon = Icons.Default.DataUsage,
                onClick = {
                    navController.navigate("data_usage")

                }
            )

            SettingsOption(
                title = "About",
                description = "SidewayQR v1.0",
                icon = Icons.Default.Info,
                onClick = {
                    navController.navigate("about_page")
                }
            )
        }
    }

    if (showThemeDialog) {
        ThemeSelectionDialog(
            onDismiss = {
                showThemeDialog = false
            }
        )
    }
}
