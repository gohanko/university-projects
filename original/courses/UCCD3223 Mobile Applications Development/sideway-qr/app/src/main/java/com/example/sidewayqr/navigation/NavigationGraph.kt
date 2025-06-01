package com.example.sidewayqr.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.sidewayqr.data.datastore.CookieRepository
import com.example.sidewayqr.network.SidewayQRAPIService
import com.example.sidewayqr.ui.screens.event.EventCreationScreen
import com.example.sidewayqr.ui.screens.Onboarding
import com.example.sidewayqr.ui.screens.ScanHistoryScreen
import com.example.sidewayqr.ui.screens.authentication.ChangePasswordScreen
import com.example.sidewayqr.ui.screens.authentication.LoginScreen
import com.example.sidewayqr.ui.screens.authentication.RegisterScreen
import com.example.sidewayqr.ui.screens.event.EventEditScreen
import com.example.sidewayqr.ui.screens.settings.AboutScreen
import com.example.sidewayqr.ui.screens.settings.AccountSettingsScreen
import com.example.sidewayqr.ui.screens.settings.DataUsageScreen
import com.example.sidewayqr.ui.screens.settings.LanguageSelectionScreen
import com.example.sidewayqr.ui.screens.settings.NotificationSettingsScreen
import com.example.sidewayqr.ui.screens.settings.SettingsScreen
import com.example.sidewayqr.viewmodel.AuthenticationViewModel
import com.example.sidewayqr.viewmodel.EventOperationViewModel

@Composable
fun NavigationGraph(
    navHostController: NavHostController,
    sidewayQRAPIService: SidewayQRAPIService,
    eventOperationViewModel: EventOperationViewModel,
    authenticationViewModel: AuthenticationViewModel,
    cookieRepository: CookieRepository
) {
    NavHost(
        navHostController,
        startDestination = "onboarding_screen"
    ) {
        composable("onboarding_screen") {
            Onboarding(
                navHostController,
                cookieRepository,
            )
        }

        composable("login_screen") {
               LoginScreen(
                   authenticationViewModel = authenticationViewModel,
                   navHostController = navHostController
               )
            }

        composable("register_screen") {
            RegisterScreen(
                authenticationViewModel = authenticationViewModel,
                navHostController = navHostController
            )
        }

        composable("change_password_screen") {
            ChangePasswordScreen(
                authenticationViewModel = authenticationViewModel,
                navHostController = navHostController,
                cookieRepository = cookieRepository
            )
        }

        composable("scan_history_screen") {
            ScanHistoryScreen(
                navHostController,
                eventOperationViewModel,
            )
        }

        composable("event_creation") {
            EventCreationScreen(
                navHostController,
                eventOperationViewModel
            )
        }

        composable(
            "event_edit/{eventId}/{eventName}/{eventDescription}/{eventStartDate}/{eventEndDate}",
            arguments = listOf(
                navArgument("eventId") { type = NavType.IntType },
                navArgument("eventName") { type = NavType.StringType },
                navArgument("eventDescription") { type = NavType.StringType },
                navArgument("eventStartDate") { type = NavType.StringType },
                navArgument("eventEndDate") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val eventId = backStackEntry.arguments?.getInt("eventId") ?: 0
            val eventName = backStackEntry.arguments?.getString("eventName") ?: ""
            val eventDescription = backStackEntry.arguments?.getString("eventDescription") ?: ""
            val eventStartDate = backStackEntry.arguments?.getString("eventStartDate") ?: ""
            val eventEndDate = backStackEntry.arguments?.getString("eventEndDate") ?: ""

            EventEditScreen(
                navHostController = navHostController,
                eventOperationViewModel = eventOperationViewModel,
                eventId = eventId,
                initialName = eventName,
                initialDescription = eventDescription,
                initialStartDate = eventStartDate,
                initialEndDate = eventEndDate
            )
        }

        composable("account_settings") {
            AccountSettingsScreen(
                navHostController,
                authenticationViewModel,
                cookieRepository
            )
        }

        composable("settings_screen") {
            SettingsScreen(
                navHostController
            )
        }

        composable("about_page") {
            AboutScreen(navHostController)
        }

        composable("notification_settings") {
            NotificationSettingsScreen(navHostController)
        }

        composable("language_selection") {
            LanguageSelectionScreen(navHostController)
        }

        composable("data_usage") {
            DataUsageScreen(navHostController)
        }
    }
}