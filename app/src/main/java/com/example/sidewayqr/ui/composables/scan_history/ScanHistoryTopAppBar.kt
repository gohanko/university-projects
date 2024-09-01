package com.example.sidewayqr.ui.composables.scan_history

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScanHistoryTopAppBar(
    navController: NavController,
    refresh: () -> Unit,
) {
    TopAppBar(
        title = {
            Text("Scan History")
        },
        actions = {
            IconButton(
                onClick = {
                    refresh()
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Refresh,
                    contentDescription = "Refresh"
                )
            }

            IconButton(
                onClick = {
                    navController.navigate("settings_screen")
                }
            ) {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "Settings"
                )
            }
        }
    )
}