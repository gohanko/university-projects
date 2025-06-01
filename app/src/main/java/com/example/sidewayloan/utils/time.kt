package com.example.sidewayloan.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

const val dateFormat = "dd/MM/yyyy"

fun convertMillisToDate(millis: Long): String {
    val formatter = SimpleDateFormat(dateFormat, Locale.getDefault())
    return formatter.format(Date(millis))
}

fun convertDateToMillis(date: String): Long {
    val formatter = SimpleDateFormat(dateFormat, Locale.getDefault())
    return formatter.parse(date)?.time ?: 0
}