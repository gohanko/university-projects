package com.example.sidewayqr.data.api.authentication

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("status")
    var status: String,

    @SerializedName("accessToken")
    var accessToken: String,

    @SerializedName("message")
    var message: String
)