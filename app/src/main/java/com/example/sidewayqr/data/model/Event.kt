package com.example.sidewayqr.data.model

import java.util.Date
import java.util.UUID

data class Event(
    val id: Number,
    val name: String,
    val description: String,
    val startDate: Date,
    val endDate: Date,
    val code: UUID,
    val childEvents: List<Event>?,
    val createdByUserId: Number
)