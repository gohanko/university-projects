package com.example.sidewayqr.ui.screens.settings

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sidewayqr.ui.theme.SidewayQRTheme

class SettingsScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SidewayQRTheme {
                val navController = rememberNavController()
                AppNavigation(navController)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(navController: NavController) {
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
        content = { paddingValues -> // Add paddingValues parameter
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues) // Apply paddingValues
                    .padding(8.dp)
            ) {
                //item { ProfileSection() }
                item { Spacer(modifier = Modifier.height(16.dp)) }
                item {
                    SettingsOption(
                        "Account",
                        Icons.Default.Person
                    ) { navController.navigate("account_settings") }
                }
                item { HorizontalDivider() }
                item {
                    SettingsOption(
                        "Notifications",
                        Icons.Default.Notifications
                    ) { navController.navigate("notification_settings") }
                }
                item { HorizontalDivider() }
                item {
                    SettingsOption(
                        "General",
                        Icons.Default.Settings
                    ) { navController.navigate("general_settings") }
                }
                item { Spacer(modifier = Modifier.height(16.dp)) }
                item {
                    SettingsOption(
                        "About",
                        Icons.Default.Info
                    ) { navController.navigate("about_page") }
                }
            }
        }
    )
}

@Composable
fun SettingsOption(title: String, icon: ImageVector, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(imageVector = icon, contentDescription = null, modifier = Modifier.size(24.dp))
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = title, fontSize = 18.sp, modifier = Modifier.weight(1f))
        Icon(imageVector = Icons.Default.ArrowForward, contentDescription = null)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutPage(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("About") },
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
                text = "SidewayQR Code Scanner",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = "This app allows you to scan QR codes quickly and easily.",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "Version: 1.0",
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }
    }
}

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController, startDestination = "settings_screen") {
        composable("account_settings") {
            AccountSettingsScreen(navController) {
                logoutHandler(navController.context)
            }
        }
        composable("settings_screen") { SettingsScreen(navController) }
        composable("about_page") { AboutPage(navController) }
        composable("notification_settings") { NotificationSettingsScreen(navController) }
        composable("general_settings") { GeneralSettingsScreen(navController) }
        composable("language_selection") { LanguageSelectionScreen(navController) }
        composable("data_usage") { DataUsageScreen(navController) }
    }
}