package com.example.sidewayqr.ui.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.sidewayqr.data.model.Event

@Composable
fun ScanHistoryList(
    modifier: Modifier,
    eventsList: List<Event>
) {
    LazyColumn(
        modifier = modifier
            .padding(10.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        items(eventsList) {
            ScanHistoryListItem(
                name = it.name,
                startTime = it.startDate.time.toString(),
                endTime = it.endDate.time.toString()
            )
        }
    }
}