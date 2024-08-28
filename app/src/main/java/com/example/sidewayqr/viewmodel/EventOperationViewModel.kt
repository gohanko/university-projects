package com.example.sidewayqr.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sidewayqr.data.api.GenericAPIResponse
import com.example.sidewayqr.data.api.event.AttendEventRequest
import com.example.sidewayqr.data.api.event.GetEventResponse
import com.example.sidewayqr.data.model.Event
import com.example.sidewayqr.network.SidewayQRAPIService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EventOperationViewModel(
    private val sidewayQRAPIService: SidewayQRAPIService,
): ViewModel() {
    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    fun setIsLoading(newIsLoading: Boolean) {
        _isLoading.value = newIsLoading
    }

    private var _eventsList = mutableStateListOf<Event>()
    val eventsList: List<Event>
        get() = _eventsList

    init {
        getEvents()
    }

    fun attendEvent(
        eventId: Int,
        eventCode: String,
        onResponse: (call: Call<GenericAPIResponse>, response: Response<GenericAPIResponse>) -> Unit
    ) {
        viewModelScope.launch {
            val call = sidewayQRAPIService.attendEvent(
                eventId=eventId,
                AttendEventRequest(eventCode = eventCode)
            )

            call.enqueue(object : Callback<GenericAPIResponse> {
                override fun onResponse(
                    call: Call<GenericAPIResponse>,
                    response: Response<GenericAPIResponse>
                ) {
                    onResponse(call, response)
                }

                override fun onFailure(response: Call<GenericAPIResponse>, t: Throwable) {
                    t.printStackTrace()
                }
            })
        }
    }

    fun getEvents() {
        setIsLoading(true)

        viewModelScope.launch {
            try {
                _eventsList.clear()
                val call = sidewayQRAPIService.getEvents()

                call.enqueue(object : Callback<GetEventResponse> {
                    override fun onResponse(call: Call<GetEventResponse>, response: Response<GetEventResponse>) {
                        setIsLoading(false)
                        response.body()?.let { _eventsList.addAll(it.data) }
                    }

                    override fun onFailure(response: Call<GetEventResponse>, t: Throwable) {
                        setIsLoading(false)
                        t.printStackTrace()
                    }
                })
            } catch (_: Exception) {
            } finally {
                setIsLoading(false)
            }
        }
    }
}