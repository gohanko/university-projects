package com.example.sidewayqr.ui.screens.event

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.sidewayqr.viewmodel.EventOperationViewModel
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventEditScreen(
    navHostController: NavHostController,
    eventOperationViewModel: EventOperationViewModel,
    eventId: Int,
    initialName: String,
    initialDescription: String,
    initialStartDate: String,
    initialEndDate: String
) {
    val context = LocalContext.current

    val eventName = remember { mutableStateOf(initialName) }
    val eventDescription = remember { mutableStateOf(initialDescription) }
    val eventStartDate = remember { mutableStateOf(initialStartDate) }
    val eventEndDate = remember { mutableStateOf(initialEndDate) }

    val calendar = Calendar.getInstance()
    val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX", Locale.getDefault())

    val startDatePickerDialog = DatePickerDialog(
        context,
        { _, year, month, dayOfMonth ->
            val timePickerDialog = TimePickerDialog(
                context,
                { _, hourOfDay, minute ->
                    calendar.set(year, month, dayOfMonth, hourOfDay, minute)
                    eventStartDate.value = dateFormat.format(calendar.time)
                },
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                true
            )
            timePickerDialog.show()
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    )

    val endDatePickerDialog = DatePickerDialog(
        context,
        { _, year, month, dayOfMonth ->
            val timePickerDialog = TimePickerDialog(
                context,
                { _, hourOfDay, minute ->
                    calendar.set(year, month, dayOfMonth, hourOfDay, minute)
                    eventEndDate.value = dateFormat.format(calendar.time)
                },
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                true
            )
            timePickerDialog.show()
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    )

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text("Edit Event") },
                navigationIcon = {
                    IconButton(onClick = { navHostController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(16.dp)
            ) {
                OutlinedTextField(
                    value = eventName.value,
                    onValueChange = { eventName.value = it },
                    label = { Text("Event Name") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = eventDescription.value,
                    onValueChange = { eventDescription.value = it },
                    label = { Text("Event Description") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    onClick = { startDatePickerDialog.show() },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = if (eventStartDate.value.isEmpty()) "Select Start Date" else eventStartDate.value)
                }
                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    onClick = { endDatePickerDialog.show() },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = if (eventEndDate.value.isEmpty()) "Select End Date" else eventEndDate.value)
                }
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = {
                        eventOperationViewModel.updateEvent(
                            eventId = eventId,
                            name = eventName.value,
                            description = eventDescription.value,
                            startDate = eventStartDate.value,
                            endDate = eventEndDate.value,
                            handleResponse = { call, response ->
                                if (response.isSuccessful) {
                                    Toast.makeText(context, "Event Updated", Toast.LENGTH_SHORT).show()
                                    navHostController.popBackStack()
                                } else {
                                    val errorBody = response.errorBody()?.string()
                                    Toast.makeText(context, "Failed to Update Event: $errorBody", Toast.LENGTH_SHORT).show()
                                }
                            }
                        )
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Update Event")
                }
            }
        }
    )
}