package com.example.sidewayloan.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.datastore.core.DataStore
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.sidewayloan.data.user_settings.UserSettings
import com.example.sidewayloan.ui.screens.CalculatorScreen
import com.example.sidewayloan.ui.screens.MainScreen
import com.example.sidewayloan.ui.screens.UserSettingsScreen

@Composable
fun RootGraph(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    userSettingsDataStore: DataStore<UserSettings>
) {
    NavHost(
        modifier = modifier,
        navController = navHostController,
        startDestination = MainRoute
    ) {
        composable<UserSettingsRoute> {
            UserSettingsScreen(navHostController, userSettingsDataStore)
        }

        composable<MainRoute> {
            MainScreen(
                userSettingsDataStore = userSettingsDataStore,
                openCalculator = { navHostController.navigate(CalculatorRoute) },
                openUserSettings = { navHostController.navigate(UserSettingsRoute)}
            )
        }

        composable<CalculatorRoute> {
            CalculatorScreen(navHostController)
        }
    }
}