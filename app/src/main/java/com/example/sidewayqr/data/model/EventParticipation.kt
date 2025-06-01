package com.example.sidewayqr.data.model

import com.google.gson.annotations.SerializedName

data class EventParticipation(
    @SerializedName("id")
    val id: Number,

    @SerializedName("isAttended")
    val isAttended: Boolean,

    @SerializedName("eventId")
    val eventId: Number,
)