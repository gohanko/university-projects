package com.example.sidewayqr.ui.screens.settings

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LanguageSelectionScreen(navController: NavController) {
    val context = LocalContext.current
    val languages = listOf("English", "Mandarin")

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Select Language") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            items(languages) { language ->
                Text(
                    text = language,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            setLanguage(language, context)
                            navController.popBackStack()
                        }
                        .padding(16.dp)
                )
            }
        }
    }
}

fun setLanguage(language: String, context: Context) {
    val locale = when (language) {
        "Mandarin" -> Locale("cn")
        else -> Locale("en")
    }

    Locale.setDefault(locale)
    val config = context.resources.configuration
    config.setLocale(locale)
    context.createConfigurationContext(config)

    // Save the language selection in SharedPreferences (or DataStore)
    val preferences = context.getSharedPreferences("AppPreferences", Context.MODE_PRIVATE)
    preferences.edit().putString("selected_language", language).apply()

    // Recreate the activity to apply the language change
    //(context as? Activity)?.recreate()
}

fun loadDataUsageSettings(context: Context): Triple<Boolean, Boolean, Int> {
    val preferences = context.getSharedPreferences("AppPreferences", Context.MODE_PRIVATE)
    val mobileDataEnabled = preferences.getBoolean("mobile_data_enabled", true)
    val dataLimitEnabled = preferences.getBoolean("data_limit_enabled", false)
    val dataLimit = preferences.getInt("data_limit", 1000)
    return Triple(mobileDataEnabled, dataLimitEnabled, dataLimit)
}