package com.example.sidewayqr.data.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CookieRepository(private val dataStore: DataStore<Preferences>) {
    private companion object {
        val COOKIE = stringPreferencesKey("cookie")
    }

    val cookie: Flow<String> =
        dataStore.data.map { preferences ->
            preferences[COOKIE] ?: ""
        }

    suspend fun saveCookie(cookie: String) {
        dataStore.edit { preferences ->
            preferences[COOKIE] = cookie
        }
    }
}