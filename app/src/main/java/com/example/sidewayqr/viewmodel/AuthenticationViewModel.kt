package com.example.sidewayqr.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sidewayqr.data.api.GenericAPIResponse
import com.example.sidewayqr.data.api.authentication.ChangePasswordRequest
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
    fun register(
        email: String,
        password: String,
        handleResponse:  (call: Call<GenericAPIResponse>, response: Response<GenericAPIResponse>)
            -> Unit = { _: Call<GenericAPIResponse>, _: Response<GenericAPIResponse> -> },
        handleFailure: (response: Call<GenericAPIResponse>, t: Throwable)
            -> Unit = { _: Call<GenericAPIResponse>, _: Throwable -> },
    ) {
        viewModelScope.launch {
            val call = sidewayQRAPIService.register(
                RegisterLoginRequest(
                    email = email,
                    password = password
                )
            )

            call.enqueue(object : Callback<GenericAPIResponse> {
                override fun onResponse(
                    call: Call<GenericAPIResponse>,
                    response: Response<GenericAPIResponse>
                ) {
                    handleResponse(call, response)
                }

                override fun onFailure(
                    response: Call<GenericAPIResponse>,
                    t: Throwable
                ) {
                    t.printStackTrace()
                    handleFailure(response, t)
                }
            })
        }
    }

    fun login(
        email: String,
        password: String,
        handleResponse:  (call: Call<LoginResponse>, response: Response<LoginResponse>)
            -> Unit = { _: Call<LoginResponse>, _: Response<LoginResponse> -> },
        handleFailure: (response: Call<LoginResponse>, t: Throwable)
            -> Unit = { _: Call<LoginResponse>, _: Throwable -> },
    ) {
        viewModelScope.launch {
            val call = sidewayQRAPIService.login(
                RegisterLoginRequest(
                    email=email,
                    password=password
                )
            )

            call.enqueue(object : Callback<LoginResponse> {
                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ) {
                    handleResponse(call, response)
                }

                override fun onFailure(
                    response: Call<LoginResponse>,
                    t: Throwable
                ) {
                    t.printStackTrace()
                    handleFailure(response, t)
                }
            })
        }
    }

    fun logout(
        handleResponse:  (call: Call<GenericAPIResponse>, response: Response<GenericAPIResponse>)
            -> Unit = { _: Call<GenericAPIResponse>, _: Response<GenericAPIResponse> -> },
        handleFailure: (response: Call<GenericAPIResponse>, t: Throwable)
            -> Unit = { _: Call<GenericAPIResponse>, _: Throwable -> },
    ) {
        viewModelScope.launch {
            val call = sidewayQRAPIService.logout()

            call.enqueue(object : Callback<GenericAPIResponse> {
                override fun onResponse(
                    call: Call<GenericAPIResponse>,
                    response: Response<GenericAPIResponse>
                ) {
                    handleResponse(call, response)
                }

                override fun onFailure(
                    response: Call<GenericAPIResponse>,
                    t: Throwable
                ) {
                    t.printStackTrace()
                    handleFailure(response, t)
                }
            })
        }
    }

    fun changePassword(
        newPassword: String,
        handleResponse:  (call: Call<GenericAPIResponse>, response: Response<GenericAPIResponse>)
            -> Unit = { _: Call<GenericAPIResponse>, _: Response<GenericAPIResponse> -> },
        handleFailure: (response: Call<GenericAPIResponse>, t: Throwable)
            -> Unit = { _: Call<GenericAPIResponse>, _: Throwable -> },
    ) {
        viewModelScope.launch {
            val call = sidewayQRAPIService.changePassword(
                ChangePasswordRequest(
                    newPassword = newPassword
                )
            )

            call.enqueue(object : Callback<GenericAPIResponse> {
                override fun onResponse(
                    call: Call<GenericAPIResponse>,
                    response: Response<GenericAPIResponse>
                ) {
                    handleResponse(call, response)
                }

                override fun onFailure(
                    response: Call<GenericAPIResponse>,
                    t: Throwable
                ) {
                    t.printStackTrace()
                    handleFailure(response, t)
                }
            })
        }
    }
}