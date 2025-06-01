package com.example.sidewayqr.data.model

import com.google.gson.annotations.SerializedName
import java.util.Date

data class Event(
    @SerializedName("id")
    val id: Number,

    @SerializedName("name")
    val name: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("startDate")
    val startDate: Date,

    @SerializedName("endDate")
    val endDate: Date,

    @SerializedName("code")
    val code: String,

    @SerializedName("createdBy")
    val createdBy: Number,

    @SerializedName("isAttended")
    val isAttended: Boolean = false
)