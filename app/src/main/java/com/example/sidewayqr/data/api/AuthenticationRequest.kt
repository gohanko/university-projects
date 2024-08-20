package com.example.sidewayqr.data.api

import com.google.gson.annotations.SerializedName

data class AuthenticationRequest(
    @SerializedName("email")
    val email: String,

    @SerializedName("password")
    val password: String
)