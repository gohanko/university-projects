package com.example.sidewayqr.ui.screens

import android.util.Log
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
import androidx.compose.ui.unit.dp
import com.example.sidewayqr.data.datastore.CookieRepository
import com.example.sidewayqr.network.SidewayQRAPIService
import com.example.sidewayqr.ui.composables.ScanHistoryList
import com.example.sidewayqr.ui.composables.status.NotFound
import com.example.sidewayqr.viewmodel.SidewayQRViewModel
import io.github.g00fy2.quickie.ScanQRCode

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScanHistoryScreen(
    sidewayQRAPIService: SidewayQRAPIService,
    sidewayQRViewModel: SidewayQRViewModel,
    cookieRepository: CookieRepository
) {
    val searchText by sidewayQRViewModel.searchText.collectAsState()
    val isSearching by sidewayQRViewModel.isSearching.collectAsState()
    val errorMessage by sidewayQRViewModel.errorMessage.collectAsState()
    val eventsList = sidewayQRViewModel.eventsList

    val pullRefreshState = rememberPullToRefreshState()

    val scanQRCodeLauncher = rememberLauncherForActivityResult(ScanQRCode()) { result ->

    }

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
            ) {

            }
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
        } else if (eventsList.isEmpty()) {
            NotFound()
        } else {
            ScanHistoryList(
                modifier = Modifier.padding(innerPadding),
                eventsList = eventsList
            )
        }
    }
}