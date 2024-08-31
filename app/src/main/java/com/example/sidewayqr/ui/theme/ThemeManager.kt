package com.example.sidewayqr.ui.theme

import android.content.Context
import androidx.appcompat.app.AppCompatDelegate


object ThemeManager {
    const val THEME_PREFERENCE = "theme_preference"
    const val THEME_KEY = "theme_key"

    const val LIGHT_MODE = "Light"
    const val DARK_MODE = "Dark"
    const val SYSTEM_DEFAULT = "System Default"

    fun applyTheme(theme: String) {
        when (theme) {
            LIGHT_MODE -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            DARK_MODE -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            SYSTEM_DEFAULT -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
        }
    }

    fun saveTheme(context: Context, theme: String) {
        val preferences = context.getSharedPreferences(THEME_PREFERENCE, Context.MODE_PRIVATE)
        preferences.edit().putString(THEME_KEY, theme).apply()
        applyTheme(theme)
    }

    fun getSavedTheme(context: Context): String {
        val preferences = context.getSharedPreferences(THEME_PREFERENCE, Context.MODE_PRIVATE)
        return preferences.getString(THEME_KEY, SYSTEM_DEFAULT) ?: SYSTEM_DEFAULT
    }
}
