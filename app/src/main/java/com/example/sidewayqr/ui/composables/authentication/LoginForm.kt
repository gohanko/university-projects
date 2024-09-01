package com.example.sidewayqr.ui.composables.authentication

import android.graphics.Outline
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.sidewayqr.data.api.authentication.LoginResponse
import com.example.sidewayqr.ui.composables.PasswordOutlinedTextField
import com.example.sidewayqr.viewmodel.AuthenticationViewModel
import retrofit2.Call
import retrofit2.Response
import java.net.SocketTimeoutException

@Composable
fun LoginForm(
    modifier: Modifier,
    authenticationViewModel: AuthenticationViewModel,
    navController:  NavHostController
) {
    val context = LocalContext.current

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    fun handleLoginResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
        if (response.code() == 200) {
            Toast.makeText(context, "Login Successful!", Toast.LENGTH_LONG).show()
            navController.navigate("scan_history_screen")
        }

        if (response.code() == 400) {
            Toast.makeText(context, "Login attempt unsuccessful", Toast.LENGTH_LONG).show()
        }
    }

    fun handleLoginFailure(
        response: Call<LoginResponse>,
        t: Throwable
    ) {
        var message = t.message
        if (t.message == "timeout") {
            message = "Server timed out"
        }

        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    Column(
        modifier = modifier.padding(horizontal = 50.dp, vertical = 50.dp),
        verticalArrangement = Arrangement.spacedBy(
            space = 16.dp,
        )
    ) {
        Row {
            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    text = "Email",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )

                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = email,
                    onValueChange = {
                        email = it
                    }
                )
            }
        }

        Row {
            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    text = "Password",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                PasswordOutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = password,
                    onValueChange = {
                        password = it
                    }
                )
            }
        }

        Button(
            modifier = Modifier.fillMaxWidth(),
            enabled = (email.isNotEmpty() && password.isNotEmpty()),
            onClick = {
                authenticationViewModel.login(
                    email = email,
                    password = password,
                    handleResponse = ::handleLoginResponse,
                    handleFailure = ::handleLoginFailure
                )
            }
        ) {
            Text("Login")
        }
    }
}