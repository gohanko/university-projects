package com.example.sidewayloan.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.sidewayloan.ui.screens.HistoryScreen

@Composable
fun MainGraph(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    openCalculator: () -> Unit
) {
    NavHost(
        modifier = modifier,
        navController = navHostController,
        startDestination = History
    ) {
        composable<History> {
            HistoryScreen(openCalculator)
        }

        composable<Settings> {
            Text(text="aaaaa")
        }
    }
}