package com.example.sidewayloan.navigation

import androidx.navigation.NavHostController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.sidewayloan.ui.screens.CalculatorScreen

fun NavGraphBuilder.secondaryGraph(navHostController: NavHostController) {
    navigation<Secondary>(startDestination = Calculator) {
        composable<Calculator> {
            CalculatorScreen(navHostController)
        }
    }
}