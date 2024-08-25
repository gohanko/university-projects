package com.example.sidewayqr.data.api.event

import com.google.gson.annotations.SerializedName

data class AttendEventRequest(
    @SerializedName("eventCode")
    val eventCode: String
)
