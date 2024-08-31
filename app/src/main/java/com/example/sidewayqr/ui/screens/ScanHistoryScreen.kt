package com.example.sidewayqr.ui.screens

import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.QrCodeScanner
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.sidewayqr.data.api.GenericAPIResponse
import com.example.sidewayqr.network.SidewayQRAPIService
import com.example.sidewayqr.ui.composables.FullPageLoadingIndicator
import com.example.sidewayqr.ui.composables.PullToRefreshLazyColumn
import com.example.sidewayqr.ui.composables.ScanHistoryListItem
import com.example.sidewayqr.ui.composables.ScanHistoryTopAppBar
import com.example.sidewayqr.ui.composables.status.NotFound
import com.example.sidewayqr.viewmodel.EventOperationViewModel
import com.example.sidewayqr.viewmodel.SearchEventViewModel
import io.github.g00fy2.quickie.QRResult
import io.github.g00fy2.quickie.ScanQRCode
import retrofit2.Call
import retrofit2.Response

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScanHistoryScreen(
    navController: NavController,
    sidewayQRAPIService: SidewayQRAPIService,
    eventOperationViewModel: EventOperationViewModel
) {
    val context = LocalContext.current

    val searchEventViewModel = SearchEventViewModel()
    val searchText by searchEventViewModel.searchText.collectAsState()
    val isSearching by searchEventViewModel.isSearching.collectAsState()

    val isLoading by eventOperationViewModel.isLoading.collectAsState()
    val eventsList = eventOperationViewModel.eventsList

    fun handleOnResponse(call: Call<GenericAPIResponse>, response: Response<GenericAPIResponse>) {
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
                    handleResponse = ::handleOnResponse
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
            ScanHistoryTopAppBar(navController = navController)
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                scanQRCodeLauncher.launch(null)
            }) {
                Icon(imageVector = Icons.Default.QrCodeScanner, contentDescription = "Scan QR Icon")
            }
        }
    ) { innerPadding ->
        if (eventsList.isEmpty() && !isLoading) {
            NotFound()
            return@Scaffold
        }
        
        PullToRefreshLazyColumn(
            modifier = Modifier.padding(innerPadding),
            listItems = eventsList,
            content = {
                ScanHistoryListItem(
                    event = it
                )
            },
            isRefreshing = isLoading,
            onRefresh = {
                eventOperationViewModel.setIsLoading(true)
                eventOperationViewModel.getEvents()
                eventOperationViewModel.setIsLoading(false)
            }
        )
    }
}