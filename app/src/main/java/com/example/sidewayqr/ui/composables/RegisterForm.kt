package com.example.sidewayqr.ui.composables

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.sidewayqr.data.api.GenericAPIResponse
import com.example.sidewayqr.viewmodel.AuthenticationViewModel
import retrofit2.Call
import retrofit2.Response

@Composable
fun RegisterForm ( authenticationViewModel: AuthenticationViewModel,
                   navController: NavHostController
) {
    val context = LocalContext.current

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    fun handleResponse(call: Call<GenericAPIResponse>, response: Response<GenericAPIResponse>) {
        Log.d("AAAAAAAAAAAAAAAa", response.toString())
    }

    Column(
        modifier = Modifier
            .padding(10.dp),
        verticalArrangement = Arrangement.spacedBy(
            space = 10.dp,
            alignment = Alignment.CenterVertically
        )
    ) {
        TextField(
            label = {
                Text("Email")
            },
            value = email,
            onValueChange = {
                email = it
            }
        )
        TextField(
            label = {
                Text("Password")
            },
            value = password,
            onValueChange = {
                password = it
            }
        )
        Button(
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            onClick = {
                authenticationViewModel.register(
                    email = email,
                    password = password,
                    handleResponse = ::handleResponse
                )
            }
        ) {
            Text("Register")
        }

        Button(
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            onClick = {
                navController.navigate("LoginScreen")
            }
        ) {
            Text("Login here")
        }
    }
}


