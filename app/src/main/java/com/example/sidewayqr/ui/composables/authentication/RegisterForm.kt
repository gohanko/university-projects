package com.example.sidewayqr.ui.composables.authentication

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
import com.example.sidewayqr.data.api.GenericAPIResponse
import com.example.sidewayqr.data.api.authentication.LoginResponse
import com.example.sidewayqr.ui.composables.PasswordOutlinedTextField
import com.example.sidewayqr.viewmodel.AuthenticationViewModel
import retrofit2.Call
import retrofit2.Response

@Composable
fun RegisterForm (
    modifier: Modifier,
    authenticationViewModel: AuthenticationViewModel,
    navHostController: NavHostController
) {
    val context = LocalContext.current

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    fun handleRegisterResponse(call: Call<GenericAPIResponse>, response: Response<GenericAPIResponse>) {
        if (response.code() == 201) {
            Toast.makeText(context, "Register Successful!", Toast.LENGTH_LONG).show()
            navHostController.navigate("login_screen")
        }

        if (response.code() == 400) {
            Toast.makeText(context, response.toString(), Toast.LENGTH_LONG).show()
        }

        if (response.code() == 500) {
            Toast.makeText(context, "Something went wrong with the servers.", Toast.LENGTH_LONG).show()
        }
    }

    fun handleRegisterFailure(
        response: Call<GenericAPIResponse>,
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
                authenticationViewModel.register(
                    email = email,
                    password = password,
                    handleResponse = ::handleRegisterResponse,
                    handleFailure = ::handleRegisterFailure
                )
            }
        ) {
            Text("Register")
        }
    }
}


