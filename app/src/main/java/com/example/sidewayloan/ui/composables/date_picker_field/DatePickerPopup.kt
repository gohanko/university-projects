package com.example.sidewayloan.ui.composables.date_picker_field

import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import com.example.sidewayloan.utils.convertDateToMillis
import com.example.sidewayloan.utils.convertMillisToDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerPopup(
    initialSelectedDate: String,
    onDateSelected: (String) -> Unit,
    onDismiss: () -> Unit
) {
    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = convertDateToMillis(initialSelectedDate),
    )

    val newSelectedDate = datePickerState
        .selectedDateMillis
        ?.let { convertMillisToDate(it) }
        ?: ""

    DatePickerDialog(
        onDismissRequest = { onDismiss() },
        confirmButton = {
            Button(onClick = {
                onDateSelected(newSelectedDate)
                onDismiss()
            }) {
                Text(text = "OK")
            }
        },
        dismissButton = {
            Button(onClick = { onDismiss() }) {
                Text(text = "Cancel")
            }
        }
    ) {
        DatePicker(state = datePickerState)
    }
}