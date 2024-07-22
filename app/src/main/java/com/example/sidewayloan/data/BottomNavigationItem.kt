package com.example.sidewayloan.data

import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavigationItem(
    val route: String,
    val text: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
)