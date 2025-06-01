package com.example.sidewayqr.ui.screens.authentication

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.sidewayqr.data.api.GenericAPIResponse
import com.example.sidewayqr.data.datastore.CookieRepository
import com.example.sidewayqr.ui.composables.PasswordOutlinedTextField
import com.example.sidewayqr.viewmodel.AuthenticationViewModel
import kotlinx.coroutines.runBlocking
import retrofit2.Call
import retrofit2.Response

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChangePasswordScreen(
    authenticationViewModel: AuthenticationViewModel,
    navHostController: NavHostController,
    cookieRepository: CookieRepository
) {
    val context = LocalContext.current

    fun handleResponse(call: Call<GenericAPIResponse>, response: Response<GenericAPIResponse>) {
        if (response.code() == 200) {
            authenticationViewModel.logout()
            runBlocking {
                cookieRepository.saveCookie("")
            }
            navHostController.navigate("onboarding_screen")
            Toast.makeText(context, "Password changed successfully", Toast.LENGTH_LONG).show()
        }
    }

    var newPassword by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Change Password")
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navHostController.popBackStack()
                    }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back to account settings"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 50.dp, vertical = 50.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text(
                text = "New Password",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )

            PasswordOutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = newPassword,
                onValueChange = {
                    newPassword = it
                }
            )

            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    authenticationViewModel.changePassword(
                        newPassword = newPassword,
                        handleResponse = ::handleResponse
                    )
                }
            ) {
                Text("Change Password")
            }
        }
    }
}