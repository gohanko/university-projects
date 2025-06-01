package com.example.sidewayqr.data.api.event

import com.example.sidewayqr.data.model.Event
import com.google.gson.annotations.SerializedName

data class GetEventResponse(
    @SerializedName("status")
    var status: String,

    @SerializedName("code")
    var code: Int,

    @SerializedName("data")
    var data: Collection<Event>,

    @SerializedName("message")
    var message: String,
)
