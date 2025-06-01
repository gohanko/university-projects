package com.example.sidewayloan.ui.composables.date_picker_field

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
import com.example.sidewayloan.utils.dateFormat

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerField(
    modifier: Modifier = Modifier,
    label: String,
    selectedDate: String,
    onSelectDate: (String) -> Unit
) {
    var showDatePickerDialog by remember { mutableStateOf(false) }

    OutlinedTextField(
        modifier = modifier,
        label={ Text(label) },
        value=selectedDate,
        placeholder={ Text(dateFormat) },
        onValueChange = {
            onSelectDate(it)
        },
        trailingIcon = {
            IconButton(
                onClick = {
                    showDatePickerDialog = true
                }
            ) {
                Icon(
                    imageVector = Icons.Outlined.EditCalendar,
                    contentDescription = "Edit the date"
                )
            }
        }
    )

    if (showDatePickerDialog) {
        DatePickerPopup(
            selectedDate = selectedDate,
            onDateSelected = {
                onSelectDate(it)
            },
            onDismiss = {
                showDatePickerDialog = false
            }
        )
    }
}