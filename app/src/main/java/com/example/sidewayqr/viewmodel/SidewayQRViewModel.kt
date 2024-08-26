package com.example.sidewayqr.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sidewayqr.data.api.GenericAPIResponse
import com.example.sidewayqr.data.api.authentication.LoginResponse
import com.example.sidewayqr.data.api.authentication.RegisterLoginRequest
import com.example.sidewayqr.data.api.event.GetEventResponse
import com.example.sidewayqr.data.model.Event
import com.example.sidewayqr.network.SidewayQRAPIService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SidewayQRViewModel(
    private val sidewayQRAPIService: SidewayQRAPIService,
): ViewModel() {
    private val _errorMessage = MutableStateFlow("")
    val errorMessage = _errorMessage.asStateFlow()

    private var _eventsList = mutableStateListOf<Event>()
    val eventsList: List<Event>
        get() = _eventsList

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
                Log.d("login func inside try", "inside try")
                val call = sidewayQRAPIService.login(
                    RegisterLoginRequest(
                        email=email,
                        password=password
                    )
                )

                call.enqueue(object : Callback<LoginResponse> {
                    override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                        Log.d("Login onResponse outside", "OUTSIDE")
                        if (response.isSuccessful) {
                            Log.d("login onResponse", response.body().toString())
                        } else {
                            _errorMessage.value = response.errorBody().toString()
                        }
                    }

                    override fun onFailure(response: Call<LoginResponse>, t: Throwable) {
                        t.printStackTrace()
                    }
                })

            } catch (e: Exception) {
                Log.d("Login onResponse error", e.message.toString())
                _errorMessage.value = e.message.toString()
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            sidewayQRAPIService.logout()
        }
    }

    fun getEvents() {
        viewModelScope.launch {
            try {
                _eventsList.clear()
                val call = sidewayQRAPIService.getEvents()

                call.enqueue(object : Callback<GetEventResponse> {
                    override fun onResponse(call: Call<GetEventResponse>, response: Response<GetEventResponse>) {
                        Log.d("getEvents onResponse", response.body().toString())
                        response.body()?.let { _eventsList.addAll(it.data) }
                    }

                    override fun onFailure(response: Call<GetEventResponse>, t: Throwable) {
                        t.printStackTrace()
                    }
                })


            } catch (e: Exception) {
                Log.d("ERRRRROR", e.message.toString())
                _errorMessage.value = e.message.toString()
            }
        }
    }
}