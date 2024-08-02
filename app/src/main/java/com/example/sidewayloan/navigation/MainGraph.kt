package com.example.sidewayloan.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.datastore.core.DataStore
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.sidewayloan.data.database.loan.LoanViewModel
import com.example.sidewayloan.data.datastore.user_settings.UserSettings
import com.example.sidewayloan.ui.screens.HistoryScreen
import com.example.sidewayloan.ui.screens.SettingsScreen

@Composable
fun MainGraph(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    userSettingsDataStore: DataStore<UserSettings>,
    loanViewModel: LoanViewModel,
    openCalculator: () -> Unit,
    openUserSettings: () -> Unit,
) {
    NavHost(
        modifier = modifier,
        navController = navHostController,
        startDestination = HistoryRoute
    ) {
        composable<HistoryRoute> {
            HistoryScreen(loanViewModel, openCalculator)
        }

        composable<SettingsRoute> {
            SettingsScreen()
        }
    }
}