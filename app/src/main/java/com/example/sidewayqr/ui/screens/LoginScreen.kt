package com.example.sidewayqr.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.sidewayqr.ui.composables.LoginForm
import com.example.sidewayqr.viewmodel.AuthenticationViewModel

@Composable
fun LoginScreen(
    authenticationViewModel: AuthenticationViewModel,
    navHostController: NavHostController

) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp), // optional padding for spacing
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Please log in",
            fontSize = 24.sp, // Make the text bigger
            fontWeight = FontWeight.Bold, // Optional: Make the text bold
            modifier = Modifier.padding(bottom= 16.dp) // Optional: Add spacing below the text
        )

        LoginForm(authenticationViewModel, navHostController)
    }
}


