package com.example.sidewayloan.ui.composables

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.sidewayloan.ui.NavigationItem

@Composable
fun CalculatoFAB (navController: NavController) {
    FloatingActionButton(onClick = { navController.navigate(NavigationItem.Calculator.route) }) {
        Icon(Icons.Filled.Add, "Calculate Loans")
    }
}
