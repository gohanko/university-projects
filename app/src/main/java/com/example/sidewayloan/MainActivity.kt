package com.example.sidewayloan

import android.content.Context
import com.example.sidewayloan.navigation.RootGraph
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.sidewayloan.data.database.loan.LoanRoomDatabase
import com.example.sidewayloan.data.datastore.user_settings.UserSettings
import com.example.sidewayloan.data.datastore.user_settings.UserSettingsSerializer
import com.example.sidewayloan.theme.SidewayLoanTheme
import com.example.sidewayloan.ui.screens.history_screen.HistoryViewModel

val Context.userSettingsDataStore: DataStore<UserSettings> by dataStore(
    "user-settings.json",
    UserSettingsSerializer
)

class MainActivity : ComponentActivity() {
    private val db by lazy {
        Room.databaseBuilder(
            context = applicationContext,
            klass = LoanRoomDatabase::class.java,
            name = "loans.db"
        ).build()
    }

    private val viewModel by viewModels<HistoryViewModel>(
        factoryProducer = {
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return HistoryViewModel(db.dao) as T
                }
            }
        }
    )

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
                        userSettingsDataStore = userSettingsDataStore,
                        historyViewModel = viewModel
                    )
                }
            }
        }
    }
}
