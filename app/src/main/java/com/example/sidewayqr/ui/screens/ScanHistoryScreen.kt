package com.example.sidewayqr.ui.screens

import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.sidewayqr.data.api.GenericAPIResponse
import com.example.sidewayqr.data.datastore.CookieRepository
import com.example.sidewayqr.network.SidewayQRAPIService
import com.example.sidewayqr.ui.composables.ScanHistoryList
import com.example.sidewayqr.ui.composables.status.NotFound
import com.example.sidewayqr.viewmodel.SidewayQRViewModel
import io.github.g00fy2.quickie.QRResult
import io.github.g00fy2.quickie.ScanQRCode
import retrofit2.Call
import retrofit2.Response

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScanHistoryScreen(
    sidewayQRViewModel: SidewayQRViewModel,
) {
    val context = LocalContext.current

    val searchText by sidewayQRViewModel.searchText.collectAsState()
    val isSearching by sidewayQRViewModel.isSearching.collectAsState()
    val errorMessage by sidewayQRViewModel.errorMessage.collectAsState()
    val eventsList = sidewayQRViewModel.eventsList

    fun handleOnResponse(call: Call<GenericAPIResponse>, response: Response<GenericAPIResponse>) {
        if (response.code() == 201) {
            // refresh the page
            sidewayQRViewModel.getEvents()
            Toast.makeText(context, "Successfully attended class!", Toast.LENGTH_LONG).show()
        } else {

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
                sidewayQRViewModel.attendEvent(
                    eventId = eventId.toInt(),
                    eventCode = code,
                    onResponse = ::handleOnResponse
                )
            }
        }
    }

    val scanQRCodeLauncher = rememberLauncherForActivityResult(ScanQRCode(), ::handleQRCode)

    LaunchedEffect(Unit) {
        sidewayQRViewModel.getEvents()
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .imePadding(),
        topBar = {
            SearchBar(
                modifier = Modifier
                    .padding(horizontal = 10.dp, vertical = 5.dp)
                    .fillMaxWidth(),
                query = searchText ,
                onQueryChange = sidewayQRViewModel::onSearchTextChange,
                onSearch = sidewayQRViewModel::onSearchTextChange,
                active = isSearching,
                onActiveChange = { sidewayQRViewModel.onToggleSearch() },
                placeholder = { Text(text = "Search for events")},
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                trailingIcon = { Icon(Icons.Default.MoreVert, contentDescription = null) },
            ) {}
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                scanQRCodeLauncher.launch(null)
            }) {
                Icon(imageVector = Icons.Default.QrCodeScanner, contentDescription = "Scan QR Icon")
            }
        }
    ) { innerPadding ->
        if (errorMessage.isNotEmpty()) {
            Text(text = errorMessage, modifier = Modifier.padding(innerPadding))
            return@Scaffold
        }

        if (eventsList.isEmpty()) {
            NotFound()
            return@Scaffold
        }

        ScanHistoryList(
            modifier = Modifier.padding(innerPadding),
            eventsList = eventsList
        )
    }
}