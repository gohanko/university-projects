package com.example.sidewayloan.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.sidewayloan.ui.composables.CalculatoFAB

@Composable
fun History(navController: NavController) {
    Scaffold(
        floatingActionButton = { CalculatoFAB(navController) }
    ) { padding ->
        Column(modifier = Modifier.padding((padding))) {

        }
    }
}