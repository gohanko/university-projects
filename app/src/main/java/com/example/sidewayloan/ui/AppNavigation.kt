package com.example.sidewayloan.ui

enum class Screen {
    HISTORY,
    SETTINGS,
    CALCULATOR,
}

sealed class NavigationItem(val route: String) {
    object History : NavigationItem(Screen.HISTORY.name)
    object Settings: NavigationItem(Screen.SETTINGS.name)
    object Calculator: NavigationItem(Screen.CALCULATOR.name)
}