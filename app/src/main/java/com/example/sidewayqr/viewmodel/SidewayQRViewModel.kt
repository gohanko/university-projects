package com.example.sidewayqr.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sidewayqr.data.api.GenericAPIResponse
import com.example.sidewayqr.data.api.authentication.RegisterLoginRequest
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
                val call = sidewayQRAPIService.login(
                    RegisterLoginRequest(
                        email=email,
                        password=password
                    )
                )

                call.enqueue(object : Callback<GenericAPIResponse> {
                    override fun onResponse(call: Call<GenericAPIResponse>, response: Response<GenericAPIResponse>) {
                        if (response.isSuccessful) {
                            Log.d("login onResponse", response.body().toString())
                        } else {
                            _errorMessage.value = response.errorBody().toString()
                        }
                    }

                    override fun onFailure(response: Call<GenericAPIResponse>, t: Throwable) {
                        t.printStackTrace()
                    }
                })

            } catch (e: Exception) {
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

                call.enqueue(object : Callback<GenericAPIResponse> {
                    override fun onResponse(call: Call<GenericAPIResponse>, response: Response<GenericAPIResponse>) {
                        response.body()?.let { _eventsList.addAll(it.data) }
                    }

                    override fun onFailure(response: Call<GenericAPIResponse>, t: Throwable) {
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