package com.example.sidewayloan.ui.screens

import android.util.Log
import com.example.sidewayloan.navigation.MainGraph
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.datastore.core.DataStore
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import com.example.sidewayloan.data.user_settings.UserSettings
import com.example.sidewayloan.navigation.UserSettingsRoute
import com.example.sidewayloan.ui.composables.BottomNavigationBar
import com.example.sidewayloan.userSettingsDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

@Composable
fun MainScreen(
    userSettingsDataStore: DataStore<UserSettings>,
    openCalculator: () -> Unit,
    openUserSettings: () -> Unit,
) {
    val userSettings = runBlocking { userSettingsDataStore.data.first() }
    Log.d("AAAAAAAAAAAAAA", userSettings.birthday.toString())
    if (userSettings.birthday == null) {
        openUserSettings()
    }

    val navController = rememberNavController()

    Scaffold(bottomBar = {
        BottomNavigationBar(navController)
    }) { padding ->
        MainGraph(
            modifier = Modifier.padding(padding),
            navController,
            userSettingsDataStore,
            openCalculator,
            openUserSettings
        )
    }
}