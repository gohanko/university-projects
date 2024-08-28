package com.example.sidewayqr.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sidewayqr.data.api.authentication.LoginResponse
import com.example.sidewayqr.data.api.authentication.RegisterLoginRequest
import com.example.sidewayqr.network.SidewayQRAPIService
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthenticationViewModel(
    private val sidewayQRAPIService: SidewayQRAPIService
): ViewModel() {
    fun register(email: String, password: String) {
        viewModelScope.launch {
            sidewayQRAPIService.register(
                RegisterLoginRequest(
                    email = email,
                    password = password
                )
            )
        }
    }

    fun login(email: String, password: String) {
        viewModelScope.launch {
            try {
                val call = sidewayQRAPIService.login(
                    RegisterLoginRequest(
                        email=email,
                        password=password
                    )
                )

                call.enqueue(object : Callback<LoginResponse> {
                    override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {

                    }

                    override fun onFailure(response: Call<LoginResponse>, t: Throwable) {
                        t.printStackTrace()
                    }
                })

            } catch (e: Exception) {

            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            sidewayQRAPIService.logout()
        }
    }
}