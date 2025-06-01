package com.example.sidewayqr.utility

import java.text.SimpleDateFormat
import java.util.Date

fun convertDateTimeToReadable(dateTime: Date): String {
    val formatter = SimpleDateFormat("hh:mm ")
    return formatter.format(dateTime)
}