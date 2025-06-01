package com.example.sidewayqr.data.api.event

import com.google.gson.annotations.SerializedName
import java.util.Date

data class CreateUpdateEventRequest(
    @SerializedName("name")
    val name: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("startDate")
    val startDate: String,

    @SerializedName("endDate")
    val endDate: String,
)
