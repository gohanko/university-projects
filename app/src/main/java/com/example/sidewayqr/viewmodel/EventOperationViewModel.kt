package com.example.sidewayqr.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sidewayqr.data.api.GenericAPIResponse
import com.example.sidewayqr.data.api.event.AttendEventRequest
import com.example.sidewayqr.data.api.event.CreateUpdateEventRequest
import com.example.sidewayqr.data.api.event.GetEventResponse
import com.example.sidewayqr.data.model.Event
import com.example.sidewayqr.network.SidewayQRAPIService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.Locale

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

    fun createEvent(
        name: String,
        description: String,
        startDate: String,
        endDate: String,
        handleResponse:  (call: Call<GenericAPIResponse>, response: Response<GenericAPIResponse>)
            -> Unit = { _: Call<GenericAPIResponse>, _: Response<GenericAPIResponse> -> },
        handleFailure: (response: Call<GenericAPIResponse>, t: Throwable)
            -> Unit = { _: Call<GenericAPIResponse>, _: Throwable -> },
    ) {
        viewModelScope.launch {
            val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
            val parsedStartDate = dateFormat.parse(startDate)
            val parsedEndDate = dateFormat.parse(endDate)

            val call = sidewayQRAPIService.createEvent(CreateUpdateEventRequest(
                name = name,
                description = description,
                startDate = parsedStartDate,
                endDate = parsedEndDate
            ))

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

    fun readEvent(
        eventId: Int,
        handleResponse:  (call: Call<GenericAPIResponse>, response: Response<GenericAPIResponse>)
            -> Unit = { _: Call<GenericAPIResponse>, _: Response<GenericAPIResponse> -> },
        handleFailure: (response: Call<GenericAPIResponse>, t: Throwable)
            -> Unit = { _: Call<GenericAPIResponse>, _: Throwable -> },
    ) {
        viewModelScope.launch {
            val call = sidewayQRAPIService.readEvent(eventId)

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

    fun updateEvent(
        eventId: Int,
        name: String,
        description: String,
        startDate: String,
        endDate: String,
        handleResponse:  (call: Call<GenericAPIResponse>, response: Response<GenericAPIResponse>)
            -> Unit = { _: Call<GenericAPIResponse>, _: Response<GenericAPIResponse> -> },
        handleFailure: (response: Call<GenericAPIResponse>, t: Throwable)
            -> Unit = { _: Call<GenericAPIResponse>, _: Throwable -> },
    ) {
        viewModelScope.launch {
            val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
            val parsedStartDate = dateFormat.parse(startDate)
            val parsedEndDate = dateFormat.parse(endDate)

            val call = sidewayQRAPIService.updateEvent(
                eventId,
                CreateUpdateEventRequest(
                    name = name,
                    description = description,
                    startDate = parsedStartDate,
                    endDate = parsedEndDate
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

    fun deleteEvent(
        eventId: Int,
        handleResponse:  (call: Call<GenericAPIResponse>, response: Response<GenericAPIResponse>)
            -> Unit = { _: Call<GenericAPIResponse>, _: Response<GenericAPIResponse> -> },
        handleFailure: (response: Call<GenericAPIResponse>, t: Throwable)
            -> Unit = { _: Call<GenericAPIResponse>, _: Throwable -> },
    ) {
        viewModelScope.launch {
            val call = sidewayQRAPIService.deleteEvent(eventId)

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

    fun joinEvent(
        eventId: Int,
        handleResponse:  (call: Call<GenericAPIResponse>, response: Response<GenericAPIResponse>)
            -> Unit = { _: Call<GenericAPIResponse>, _: Response<GenericAPIResponse> -> },
        handleFailure: (response: Call<GenericAPIResponse>, t: Throwable)
            -> Unit = { _: Call<GenericAPIResponse>, _: Throwable -> },
    ) {
        viewModelScope.launch {
            val call = sidewayQRAPIService.joinEvent(eventId)

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

    fun leaveEvent(
        eventId: Int,
        handleResponse:  (call: Call<GenericAPIResponse>, response: Response<GenericAPIResponse>)
            -> Unit = { _: Call<GenericAPIResponse>, _: Response<GenericAPIResponse> -> },
        handleFailure: (response: Call<GenericAPIResponse>, t: Throwable)
            -> Unit = { _: Call<GenericAPIResponse>, _: Throwable -> },
    ) {
        viewModelScope.launch {
            val call = sidewayQRAPIService.leaveEvent(eventId)

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

    fun attendEvent(
        eventId: Int,
        eventCode: String,
        handleResponse:  (call: Call<GenericAPIResponse>, response: Response<GenericAPIResponse>)
            -> Unit = { _: Call<GenericAPIResponse>, _: Response<GenericAPIResponse> -> },
        handleFailure: (response: Call<GenericAPIResponse>, t: Throwable)
            -> Unit = { _: Call<GenericAPIResponse>, _: Throwable -> },
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

    fun getEvents(
        handleResponse:  (call: Call<GetEventResponse>, response: Response<GetEventResponse>)
            -> Unit = { _: Call<GetEventResponse>, _: Response<GetEventResponse> -> },
        handleFailure: (response: Call<GetEventResponse>, t: Throwable)
            -> Unit = { _: Call<GetEventResponse>, _: Throwable -> },
    ) {
        setIsLoading(true)

        viewModelScope.launch {
            _eventsList.clear()
            val call = sidewayQRAPIService.getEvents()

            call.enqueue(object : Callback<GetEventResponse> {
                override fun onResponse(call: Call<GetEventResponse>, response: Response<GetEventResponse>) {
                    setIsLoading(false)
                    response.body()?.let { _eventsList.addAll(it.data) }
                    handleResponse(call, response)
                }

                override fun onFailure(
                    response: Call<GetEventResponse>,
                    t: Throwable
                ) {
                    setIsLoading(false)
                    t.printStackTrace()
                    handleFailure(response, t)
                }
            })
        }
    }
}