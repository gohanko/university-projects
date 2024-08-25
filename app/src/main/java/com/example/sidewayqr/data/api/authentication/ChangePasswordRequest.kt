package com.example.sidewayqr.data.api.authentication

import com.google.gson.annotations.SerializedName

data class ChangePasswordRequest(
    @SerializedName("new_password")
    val new_password: String
)
