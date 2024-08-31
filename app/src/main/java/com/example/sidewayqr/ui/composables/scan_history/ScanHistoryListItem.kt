package com.example.sidewayqr.ui.composables.scan_history

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Login
import androidx.compose.material.icons.automirrored.outlined.Logout
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.sidewayqr.data.model.Event
import com.example.sidewayqr.utility.convertDateTimeToReadable

@Composable
fun ScanHistoryListItem(
    event: Event,
) {
    Card (
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer,
        ),
    ) {
        Row {
            val color = if (event.isAttended) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error
            Column(
                modifier = Modifier
                    .background(color = color)
                    .fillMaxHeight()
                    .width(10.dp)
            ) {}

            Column(
                modifier = Modifier
                    .padding(
                        top = 15.dp,
                        end=15.dp,
                        bottom = 15.dp,
                        start = 15.dp
                    ),
                verticalArrangement = Arrangement
                    .spacedBy(10.dp)
            ) {
                Row {
                    Text(
                        text = event.name,
                        fontWeight = FontWeight.ExtraBold,
                    )
                }

                Row {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement
                            .spacedBy(5.dp)
                    ) {
                        Icon(
                            modifier = Modifier
                                .clip(shape = RoundedCornerShape(5.dp))
                                .padding(5.dp)
                                .size(15.dp),
                            imageVector = Icons.AutoMirrored.Outlined.Login,
                            contentDescription = "Icon for event's starting time."
                        )

                        Text(text = convertDateTimeToReadable(event.startDate))
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(5.dp)
                    ) {
                        Icon(
                            modifier = Modifier
                                .clip(shape = RoundedCornerShape(5.dp))
                                .padding(5.dp)
                                .size(15.dp),
                            imageVector = Icons.AutoMirrored.Outlined.Logout,
                            contentDescription = "Icon for event's ending time."
                        )

                        Text(text = convertDateTimeToReadable(event.endDate))
                    }
                }
            }
        }
    }
}