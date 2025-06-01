package com.example.sidewayqr.ui.screens.authentication

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.sidewayqr.ui.composables.authentication.RegisterForm
import com.example.sidewayqr.viewmodel.AuthenticationViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    authenticationViewModel: AuthenticationViewModel,
    navHostController: NavHostController
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Please register",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navHostController.navigate("onboarding_screen")
                        }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Default.ArrowBack,
                            contentDescription = "Back to onboarding screen."
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        RegisterForm(
            modifier = Modifier.fillMaxSize().padding(innerPadding),
            authenticationViewModel,
            navHostController
        )
    }
}
