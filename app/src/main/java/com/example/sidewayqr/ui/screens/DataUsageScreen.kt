package com.example.sidewayqr.ui.screens

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
import androidx.compose.material3.Slider
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
fun DataUsageScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Data Usage") },
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
                text = "Data Usage Settings",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            var mobileDataEnabled by remember { mutableStateOf(true) }
            var dataLimitEnabled by remember { mutableStateOf(false) }
            var dataLimit by remember { mutableStateOf(1000) } // Example data limit in MB

            // Toggle for mobile data
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Enable Mobile Data")
                Spacer(modifier = Modifier.weight(1f))
                Switch(
                    checked = mobileDataEnabled,
                    onCheckedChange = { mobileDataEnabled = it }
                )
            }

            // Toggle for data limit
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Enable Data Limit")
                Spacer(modifier = Modifier.weight(1f))
                Switch(
                    checked = dataLimitEnabled,
                    onCheckedChange = { dataLimitEnabled = it }
                )
            }

            if (dataLimitEnabled) {
                // Slider for setting data limit
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                ) {
                    Text("Data Limit: ${dataLimit}MB")
                    Slider(
                        value = dataLimit.toFloat(),
                        onValueChange = { dataLimit = it.toInt() },
                        valueRange = 500f..5000f, // Min: 500MB, Max: 5000MB
                        steps = 9 // 10 steps total
                    )
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            // Save button
            Button(
                onClick = {
                    // Save the data usage settings here
                    saveDataUsageSettings(mobileDataEnabled, dataLimitEnabled, dataLimit, context = navController.context)
                    navController.popBackStack() // Go back after saving settings
                },
                modifier = Modifier.align(Alignment.End)
            ) {
                Text("Save")
            }
        }
    }
}

fun saveDataUsageSettings(mobileDataEnabled: Boolean, dataLimitEnabled: Boolean, dataLimit: Int, context: Context) {
    val preferences = context.getSharedPreferences("AppPreferences", Context.MODE_PRIVATE)
    preferences.edit().apply {
        putBoolean("mobile_data_enabled", mobileDataEnabled)
        putBoolean("data_limit_enabled", dataLimitEnabled)
        putInt("data_limit", dataLimit)
        apply()
    }
}
