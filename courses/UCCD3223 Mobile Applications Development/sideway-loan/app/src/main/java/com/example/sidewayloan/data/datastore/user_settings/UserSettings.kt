package com.example.sidewayloan.data.datastore.user_settings

import kotlinx.serialization.Serializable

@Serializable
data class UserSettings(
    var birthday: Long? = null
)

