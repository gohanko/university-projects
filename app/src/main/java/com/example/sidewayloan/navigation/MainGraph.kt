package com.example.sidewayloan.navigation

import android.util.Log
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.datastore.core.DataStore
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.sidewayloan.data.user_settings.UserSettings
import com.example.sidewayloan.ui.screens.HistoryScreen

@Composable
fun MainGraph(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    userSettingsDataStore: DataStore<UserSettings>,
    openCalculator: () -> Unit,
    openUserSettings: () -> Unit,
) {
    NavHost(
        modifier = modifier,
        navController = navHostController,
        startDestination = HistoryRoute
    ) {
        composable<HistoryRoute> {
            HistoryScreen(openCalculator)
        }

        composable<SettingsRoute> {
            Text(text="aaaaa")
        }
    }
}