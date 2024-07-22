package com.example.sidewayloan.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.sidewayloan.ui.screens.MainScreen

@Composable
fun RootGraph(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
) {
    NavHost(
        modifier = modifier,
        navController = navHostController,
        startDestination = Main
    ) {
        secondaryGraph(navHostController)

        composable<Main> {
            MainScreen { navHostController.navigate(Calculator) }
        }
    }
}