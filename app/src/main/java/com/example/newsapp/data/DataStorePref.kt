package com.example.newsapp.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

// Definisikan DataStore di level top-level
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_settings")

// Definisikan kunci untuk status login
val IS_LOGGED_IN_KEY = booleanPreferencesKey("is_logged_in")

// Fungsi untuk mengelola status login
class DataStorePref(private val context: Context) {

    // Menyimpan status login
    suspend fun saveLoginStatus(isLoggedIn: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[IS_LOGGED_IN_KEY] = isLoggedIn
        }
    }

    // Membaca status login
    val isLoggedIn: Flow<Boolean> = context.dataStore.data
        .map { preferences ->
            preferences[IS_LOGGED_IN_KEY] ?: false
        }
}
