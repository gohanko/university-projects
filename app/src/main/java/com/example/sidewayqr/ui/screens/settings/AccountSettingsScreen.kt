package com.example.sidewayqr.ui.screens.settings

import android.app.Activity
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.sidewayqr.data.api.GenericAPIResponse
import com.example.sidewayqr.data.api.authentication.LoginResponse
import com.example.sidewayqr.data.datastore.CookieRepository
import com.example.sidewayqr.viewmodel.AuthenticationViewModel
import kotlinx.coroutines.runBlocking
import retrofit2.Call
import retrofit2.Response

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountSettingsScreen(
    navController: NavController,
    authenticationViewModel: AuthenticationViewModel,
    cookieRepository: CookieRepository
) {
    val context = LocalContext.current

    fun handleLogoutResponse(call: Call<GenericAPIResponse>, response: Response<GenericAPIResponse>) {
        if (response.code() == 200) {
            Toast.makeText(context, "Logout Successful!", Toast.LENGTH_LONG).show()
            runBlocking {
                cookieRepository.saveCookie("")
            }

            navController.navigate("onboarding_screen")
        }
    }

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

            Row (
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Button(
                    modifier = Modifier.weight(1f),
                    onClick = {
                        navController.navigate("change_password_screen")
                    },
                ) {
                    Text(text = "Change Password")
                }

                Button(
                    modifier = Modifier.weight(1f),
                    onClick = {
                        authenticationViewModel.logout(
                            handleResponse = ::handleLogoutResponse
                        )
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                ) {
                    Text("Logout", color = Color.White)
                }
            }
        }
    }
}

