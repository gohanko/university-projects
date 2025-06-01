package com.example.sidewayloan.data

import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavigationBarItem(
    val route: Any,
    val text: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
)