package com.example.sidewayqr.viewmodel

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

data class Event(
    val id: Int,
    var name: String,
    var date: String,
    var location: String,
    var qrCodeData: String? = null
)

class EventViewModel : ViewModel() {
    var events: MutableState<List<Event>> = mutableStateOf(listOf())
        private set

    fun addEvent(event: Event) {
        events.value = events.value + event
    }

    fun editEvent(event: Event) {
        events.value = events.value.map { if (it.id == event.id) event else it }
    }

    fun deleteEvent(eventId: Int) {
        events.value = events.value.filter { it.id != eventId }
    }
}

@Composable
fun EventScreen(viewModel: EventViewModel = viewModel()) {
    val events by viewModel.events

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(text = "Events", style = MaterialTheme.typography.headlineMedium)

        Button(onClick = { /* Navigate to CreateEventScreen */ }) {
            Text("Create Event")
        }

        LazyColumn {
            items(events) { event ->
                EventItem(
                    event = event,
                    onEdit = { /* Navigate to EditEventScreen with event */ },
                    onDelete = { viewModel.deleteEvent(event.id) }
                )
            }
        }
    }
}

@Composable
fun EventItem(event: Event, onEdit: () -> Unit, onDelete: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(text = event.name, style = MaterialTheme.typography.bodyLarge)
            Text(text = event.date)
            Text(text = event.location)
        }
        Row {
            IconButton(onClick = onEdit) {
                Icon(Icons.Default.Edit, contentDescription = "Edit Event")
            }
            IconButton(onClick = onDelete) {
                Icon(Icons.Default.Delete, contentDescription = "Delete Event")
            }
        }
    }
}