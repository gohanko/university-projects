package com.example.sidewayqr.ui.screens.scan_history

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sidewayqr.data.model.Event
import com.example.sidewayqr.network.SidewayQRAPIService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ScanHistoryViewModel(
    private val sidewayQRAPIService: SidewayQRAPIService
): ViewModel() {
    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _eventsList = mutableStateListOf<Event>()
    val eventsList: List<Event>
        get() = _eventsList

    var errorMessage: String by mutableStateOf("")

    fun onSearchTextChange(text: String) {
        _searchText.value = text
    }

    fun onToggleSearch() {
        _isSearching.value = !_isSearching.value
        if (!_isSearching.value) {
            onSearchTextChange("")
        }
    }

    fun loadEvents() {
        viewModelScope.launch {
            try {
                _eventsList.clear()
                _eventsList.addAll(sidewayQRAPIService.getEvents())
            } catch (e: Exception) {
                errorMessage = e.message.toString()
            }
        }
    }
}