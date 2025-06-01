package com.example.sidewayloan.ui.screens

import com.example.sidewayloan.navigation.MainGraph
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.datastore.core.DataStore
import androidx.navigation.compose.rememberNavController
import com.example.sidewayloan.data.database.loan.LoanViewModel
import com.example.sidewayloan.data.datastore.user_settings.UserSettings
import com.example.sidewayloan.ui.composables.BottomNavigationBar
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

@Composable
fun MainScreen(
    userSettingsDataStore: DataStore<UserSettings>,
    loanViewModel: LoanViewModel,
    openCalculator: () -> Unit,
    openUserSettings: () -> Unit,
) {
    val userSettings = runBlocking { userSettingsDataStore.data.first() }
    if (userSettings.birthday == null) {
        openUserSettings()
    }

    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController)
        }
    ) { padding ->
        MainGraph(
            modifier = Modifier.padding(padding),
            navController,
            userSettingsDataStore,
            loanViewModel,
            openCalculator,
            openUserSettings
        )
    }
}