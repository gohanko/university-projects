package com.example.sidewayqr.ui.screens

import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.QrCodeScanner
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.sidewayqr.data.api.GenericAPIResponse
import com.example.sidewayqr.ui.composables.PullToRefreshLazyColumn
import com.example.sidewayqr.ui.composables.scan_history.ScanHistoryListItem
import com.example.sidewayqr.ui.composables.scan_history.ScanHistoryTopAppBar
import com.example.sidewayqr.viewmodel.EventOperationViewModel
import io.github.g00fy2.quickie.QRResult
import io.github.g00fy2.quickie.ScanQRCode
import retrofit2.Call
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScanHistoryScreen(
    navHostController: NavHostController,
    eventOperationViewModel: EventOperationViewModel,
) {
    val context = LocalContext.current

    val isLoading by eventOperationViewModel.isLoading.collectAsState()
    val eventsList = eventOperationViewModel.eventsList

    fun onRefresh() {
        eventOperationViewModel.setIsLoading(true)
        eventOperationViewModel.getEvents()
        eventOperationViewModel.setIsLoading(false)
    }

    LaunchedEffect(true) {
        onRefresh()
    }

    fun handleAttendEventResponse(call: Call<GenericAPIResponse>, response: Response<GenericAPIResponse>) {
        if (response.code() == 201) {
            // refresh the page
            eventOperationViewModel.getEvents()
            Toast.makeText(context, "Successfully attended class!", Toast.LENGTH_LONG).show()
        }
    }

    fun handleQRCode(result: QRResult) {
        val text = when (result) {
            is QRResult.QRSuccess -> result.content.rawValue
            QRResult.QRUserCanceled -> "User canceled"
            QRResult.QRMissingPermission -> "Missing Permission"
            is QRResult.QRError -> "${result.exception.javaClass.simpleName}: ${result.exception.localizedMessage}"
        }

        if (result is QRResult.QRSuccess) {
            val eventId = text?.split(':')?.get(0)
            val code = text?.split(":")?.get(1)

            if (eventId != null && code != null) {
                eventOperationViewModel.attendEvent(
                    eventId = eventId.toInt(),
                    eventCode = code,
                    handleResponse = ::handleAttendEventResponse,
                )
            }
        }
    }

    val scanQRCodeLauncher = rememberLauncherForActivityResult(ScanQRCode(), ::handleQRCode)

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .imePadding(),
        topBar = {
            ScanHistoryTopAppBar(
                navController = navHostController,
                refresh = ::onRefresh
            )
        },
        floatingActionButton = {
            ExpandableFloatingActionButton(
                onScanClick = { scanQRCodeLauncher.launch(null) },
                onCreateClick = { navHostController.navigate("event_creation") },
                onEditClick = { eventId, eventName, eventDescription, eventStartDate, eventEndDate ->
                    navHostController.navigate(
                        "event_edit/$eventId/$eventName/$eventDescription/$eventStartDate/$eventEndDate"
                    )
                },
            )
        }
    ) { innerPadding ->
        PullToRefreshLazyColumn(
            modifier = Modifier.padding(innerPadding),
            listItems = eventsList,
            content = {
                ScanHistoryListItem(
                    event = it,
                    onDeleteClick = { eventId ->
                        eventOperationViewModel.deleteEvent(
                            eventId = eventId,
                            handleResponse = { call, response ->
                                Log.d("API Response", "Delete Event Response: ${response.code()} - ${response.message()}")
                                if (response.isSuccessful) {
                                    Toast.makeText(context, "Event Deleted", Toast.LENGTH_SHORT).show()
                                    onRefresh()
                                } else {
                                    val errorBody = response.errorBody()?.string()
                                    Log.e("API Error", "Failed to Delete Event: $errorBody")
                                    Toast.makeText(context, "Failed to Delete Event: $errorBody", Toast.LENGTH_SHORT).show()
                                }
                            }
                        )
                    }
                )
            },
            isRefreshing = isLoading,
            onRefresh = ::onRefresh
        )
    }
}

@Composable
fun ExpandableFloatingActionButton(
    onScanClick: () -> Unit,
    onCreateClick: () -> Unit,
    onEditClick: (Int, String, String, String, String) -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .wrapContentSize()
            .animateContentSize()
    ) {
        if (expanded) {
            Spacer(modifier = Modifier.height(8.dp))
            FloatingActionButton(onClick = { onEditClick(1, "Event Name", "Event Description", "Start Date", "End Date") }) {
                Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit Event")
            }
            Spacer(modifier = Modifier.height(8.dp))
            FloatingActionButton(onClick = onCreateClick) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Create Event")
            }
            Spacer(modifier = Modifier.height(8.dp))
            FloatingActionButton(onClick = onScanClick) {
                Icon(imageVector = Icons.Default.QrCodeScanner, contentDescription = "Scan QR Code")
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
        FloatingActionButton(
            onClick = { expanded = !expanded },
            elevation = FloatingActionButtonDefaults.elevation(0.dp)
        ) {
            Icon(imageVector = if (expanded) Icons.Default.Close else Icons.Default.MoreVert, contentDescription = "Expand")
        }
    }
}