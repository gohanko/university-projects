package com.example.sidewayqr.ui.screens.ScanHistoryScreen

import android.widget.Toast
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.sidewayqr.ui.composables.ScanHistoryList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScanHistoryScreen() {
    val viewModel = ScanHistoryViewModel()

    val searchText by viewModel.searchText.collectAsState()
    val isSearching by viewModel.isSearching.collectAsState()

    val eventsList = viewModel.eventsList
    val errorMessage = viewModel.errorMessage
    
    LaunchedEffect(Unit) {
        viewModel.loadEvents()
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
                onQueryChange = viewModel::onSearchTextChange,
                onSearch = viewModel::onSearchTextChange,
                active = false,
                onActiveChange = { viewModel.onToggleSearch() },
                placeholder = { Text(text = "Search for events")},
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                trailingIcon = { Icon(Icons.Default.MoreVert, contentDescription = null) },
            ) {}
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Default.QrCodeScanner, contentDescription = "Scan QR Icon")
            }
        }
    ) { innerPadding ->
        if (errorMessage.isNotEmpty()) {
            Text(text = errorMessage)
        }

        ScanHistoryList(
            modifier = Modifier.padding(innerPadding),
            eventsList = eventsList
        )
    }
}