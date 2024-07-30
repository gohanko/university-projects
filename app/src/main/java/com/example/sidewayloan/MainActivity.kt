package com.example.sidewayloan

import android.content.Context
import com.example.sidewayloan.navigation.RootGraph
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.navigation.compose.rememberNavController
import com.example.sidewayloan.data.user_settings.UserSettings
import com.example.sidewayloan.data.user_settings.UserSettingsSerializer
import com.example.sidewayloan.theme.SidewayLoanTheme

val Context.userSettingsDataStore: DataStore<UserSettings> by dataStore(
    "user-settings.json",
    UserSettingsSerializer
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SidewayLoanTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    RootGraph(
                        modifier = Modifier,
                        navController,
                        userSettingsDataStore = userSettingsDataStore
                    )
                }
            }
        }
    }
}
