package com.example.sidewayqr.data.api

import com.google.gson.annotations.SerializedName

data class GenericAPIResponse(
    @SerializedName("status")
    var status: String,

    @SerializedName("code")
    var code: Int,

    @SerializedName("data")
    var data: Any,

    @SerializedName("message")
    var message: String,

    @SerializedName("accessToken")
    var accessToken: String?,
)
