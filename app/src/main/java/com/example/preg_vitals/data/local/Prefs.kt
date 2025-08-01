package com.example.preg_vitals.data.local

import android.content.Context
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.datastore by preferencesDataStore("settings")

object PrefsKeys {
    val HOUR  = intPreferencesKey("reminder_hour")
    val MIN   = intPreferencesKey("reminder_minute")
    val DARK_MODE_ENABLED = booleanPreferencesKey("dark_mode_enabled") // Added
}

class Prefs(private val ctx: Context) {
    val reminderTime: Flow<Pair<Int,Int>> = ctx.datastore.data.map { preferences ->
        val hour = preferences[PrefsKeys.HOUR] ?: 9 // Default hour if not set
        val minute = preferences[PrefsKeys.MIN] ?: 0 // Default minute if not set
        hour to minute
    }

    suspend fun saveTime(hour: Int, min: Int) =
        ctx.datastore.edit { settings ->
            settings[PrefsKeys.HOUR] = hour
            settings[PrefsKeys.MIN] = min
        }

    // Added for dark mode
    val darkFlow: Flow<Boolean> = ctx.datastore.data.map { preferences ->
        preferences[PrefsKeys.DARK_MODE_ENABLED] ?: false // Default to false (light mode)
    }

    // Added for dark mode
    suspend fun setDark(isDark: Boolean) =
        ctx.datastore.edit { settings ->
            settings[PrefsKeys.DARK_MODE_ENABLED] = isDark
        }
}
