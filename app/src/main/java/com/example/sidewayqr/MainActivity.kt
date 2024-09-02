package com.example.sidewayqr

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.navigation.compose.rememberNavController
import com.example.sidewayqr.data.datastore.CookieRepository
import com.example.sidewayqr.navigation.NavigationGraph
import com.example.sidewayqr.network.SidewayQRAPIService
import com.example.sidewayqr.ui.screens.settings.setLanguage
import com.example.sidewayqr.ui.theme.SidewayQRTheme
import com.example.sidewayqr.ui.theme.ThemeManager
import com.example.sidewayqr.viewmodel.AuthenticationViewModel
import com.example.sidewayqr.viewmodel.EventOperationViewModel

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "cookies")

class MainActivity : ComponentActivity() {
    private lateinit var cookieRepository: CookieRepository
    private lateinit var sidewayQRAPIService: SidewayQRAPIService
    private lateinit var authenticationViewModel: AuthenticationViewModel
    private lateinit var eventOperationViewModel: EventOperationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        cookieRepository = CookieRepository(dataStore)
        sidewayQRAPIService = SidewayQRAPIService.getInstance(cookieRepository)
        authenticationViewModel = AuthenticationViewModel(sidewayQRAPIService, cookieRepository)
        eventOperationViewModel = EventOperationViewModel(sidewayQRAPIService)

        ThemeManager.applyTheme(ThemeManager.getSavedTheme(this))

        val preferences = getSharedPreferences("AppPreferences", Context.MODE_PRIVATE)
        val selectedLanguage = preferences.getString("selected_language", "English")
        selectedLanguage?.let {
            setLanguage(it, this)
        }

        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()

            SidewayQRTheme {
                NavigationGraph(
                    navController,
                    sidewayQRAPIService,
                    eventOperationViewModel,
                    authenticationViewModel,
                    cookieRepository
                )
            }
        }
    }
}
