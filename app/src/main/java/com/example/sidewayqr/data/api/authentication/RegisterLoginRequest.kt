package com.example.sidewayqr.data.api.authentication

import com.google.gson.annotations.SerializedName

data class RegisterLoginRequest(
    @SerializedName("email")
    val email: String,

    @SerializedName("password")
    val password: String
)