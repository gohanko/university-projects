package com.example.sidewayloan.ui.composables

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.EditCalendar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.sidewayloan.utils.convertDateToMillis
import com.example.sidewayloan.utils.convertMillisToDate
import java.text.SimpleDateFormat
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateTextField(
    modifier: Modifier = Modifier,
    selectedUnixTime: Long,
    onSelectDate: (Long) -> Unit
) {
    var showDatePickerDialog by remember { mutableStateOf(false) }

    OutlinedTextField(
        modifier = modifier,
        label={ Text("Date") },
        value=convertMillisToDate(selectedUnixTime),
        placeholder={ Text("dd/MM/yyyy") },
        onValueChange = {
            val date = convertDateToMillis(it)
            onSelectDate(date)
        },
        trailingIcon = {
            IconButton(onClick = {
                showDatePickerDialog = true
            }) {
                Icon(
                    imageVector = Icons.Outlined.EditCalendar,
                    contentDescription = "Edit the date"
                )
            }
        }
    )

    if (showDatePickerDialog) {
        CustomDatePicker(
            selectedUnixTime = selectedUnixTime,
            onDateSelected = {
                onSelectDate(it)
            },
            onDismiss = {
                showDatePickerDialog = false
            }
        )
    }
}