package com.example.sidewayqr.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.sidewayqr.network.SidewayQRAPIService
import com.example.sidewayqr.ui.screens.settings.AboutPage
import com.example.sidewayqr.ui.screens.settings.AccountSettingsScreen
import com.example.sidewayqr.ui.screens.settings.DataUsageScreen
import com.example.sidewayqr.ui.screens.settings.GeneralSettingsScreen
import com.example.sidewayqr.ui.screens.settings.LanguageSelectionScreen
import com.example.sidewayqr.ui.screens.settings.NotificationSettingsScreen
import com.example.sidewayqr.ui.screens.ScanHistoryScreen
import com.example.sidewayqr.ui.screens.settings.SettingsScreen
import com.example.sidewayqr.ui.screens.settings.logoutHandler
import com.example.sidewayqr.viewmodel.EventOperationViewModel

@Composable
fun NavigationGraph(
    navHostController: NavHostController,
    sidewayQRAPIService: SidewayQRAPIService,
    eventOperationViewModel: EventOperationViewModel
) {
    NavHost(
        navHostController,
        startDestination = "scan_history_screen"
    ) {
        composable("scan_history_screen") {
            ScanHistoryScreen(
                navHostController,
                sidewayQRAPIService,
                eventOperationViewModel
            )
        }

        composable("account_settings") {
            AccountSettingsScreen(navHostController) {
                logoutHandler(navHostController.context)
            }
        }

        composable("settings_screen") {
            SettingsScreen(navHostController)
        }

        composable("about_page") {
            AboutPage(navHostController)
        }

        composable("notification_settings") {
            NotificationSettingsScreen(navHostController)
        }

        composable("general_settings") {
            GeneralSettingsScreen(navHostController)
        }

        composable("language_selection") {
            LanguageSelectionScreen(navHostController)
        }

        composable("data_usage") {
            DataUsageScreen(navHostController)
        }
    }
}