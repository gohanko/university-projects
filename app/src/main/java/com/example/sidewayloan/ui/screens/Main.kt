package com.example.sidewayloan.ui.screens

import com.example.sidewayloan.navigation.MainGraph
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.sidewayloan.ui.composables.BottomNavigationBar

@Composable
fun MainScreen(openCalculator: () -> Unit) {
    val navController = rememberNavController()

    Scaffold(bottomBar = {
        BottomNavigationBar(navController)
    }) { padding ->
        MainGraph(
            modifier = Modifier.padding(padding),
            navController,
            openCalculator
        )
    }
}