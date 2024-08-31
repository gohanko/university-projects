package com.example.sidewayqr.ui.screens.settings

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationSettingsScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Notification Settings") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            Text(
                text = "Notification Preferences",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            var notificationsEnabled by remember { mutableStateOf(true) }
            var soundEnabled by remember { mutableStateOf(true) }
            var vibrationEnabled by remember { mutableStateOf(false) }

            // Toggle for enabling/disabling notifications
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Enable Notifications")
                Spacer(modifier = Modifier.weight(1f))
                Switch(
                    checked = notificationsEnabled,
                    onCheckedChange = { notificationsEnabled = it }
                )
            }

            // Toggle for enabling/disabling sound
            if (notificationsEnabled) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Notification Sound")
                    Spacer(modifier = Modifier.weight(1f))
                    Switch(
                        checked = soundEnabled,
                        onCheckedChange = { soundEnabled = it }
                    )
                }

                // Toggle for enabling/disabling vibration
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Vibration")
                    Spacer(modifier = Modifier.weight(1f))
                    Switch(
                        checked = vibrationEnabled,
                        onCheckedChange = { vibrationEnabled = it }
                    )
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = {
                    // Save the notification settings here
                    saveNotificationSettings(notificationsEnabled, soundEnabled, vibrationEnabled, context = navController.context)
                    navController.popBackStack() // Go back after saving settings
                },
                modifier = Modifier.align(Alignment.End)
            ) {
                Text("Save")
            }
        }
    }
}

fun saveNotificationSettings(notificationsEnabled: Boolean, soundEnabled: Boolean, vibrationEnabled: Boolean, context: Context) {
    val preferences = context.getSharedPreferences("AppPreferences", Context.MODE_PRIVATE)
    preferences.edit().apply {
        putBoolean("notifications_enabled", notificationsEnabled)
        putBoolean("sound_enabled", soundEnabled)
        putBoolean("vibration_enabled", vibrationEnabled)
        apply()
    }
}

fun loadNotificationSettings(context: Context): Triple<Boolean, Boolean, Boolean> {
    val preferences = context.getSharedPreferences("AppPreferences", Context.MODE_PRIVATE)
    val notificationsEnabled = preferences.getBoolean("notifications_enabled", true)
    val soundEnabled = preferences.getBoolean("sound_enabled", true)
    val vibrationEnabled = preferences.getBoolean("vibration_enabled", false)
    return Triple(notificationsEnabled, soundEnabled, vibrationEnabled)
}
