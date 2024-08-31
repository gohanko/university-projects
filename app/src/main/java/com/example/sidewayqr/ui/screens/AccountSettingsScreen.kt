package com.example.sidewayqr.ui.screens

import android.app.Activity
import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountSettingsScreen(navController: NavController, onLogout: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Account Settings") },
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
                text = "Account Information",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Display account information here (e.g., username, email, etc.)
            Text(text = "Username: Student1")
            Text(text = "Email: student1@email.com")
            // You can fetch these details from a data source or user preferences

            Spacer(modifier = Modifier.height(24.dp))

            // Add more account-related settings here

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = {
                    // Handle logout logic
                    onLogout()
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                modifier = Modifier.align(Alignment.End)
            ) {
                Text("Logout", color = Color.White)
            }
        }
    }
}

fun logoutHandler(context: Context) {
    // Clear user session data (e.g., tokens, user info)
    val preferences = context.getSharedPreferences("AppPreferences", Context.MODE_PRIVATE)
    preferences.edit().clear().apply()

    // Navigate back to login screen or finish the activity
    (context as? Activity)?.apply {
        //startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }
}
