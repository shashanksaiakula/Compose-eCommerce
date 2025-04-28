package com.example.ecommersandroid.utils

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

// Extension property to create a singleton DataStore instance
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_prefs")

class DataStorePreference(private val context: Context) {

    // Get a value by key from DataStore
    suspend fun getData(key: String): String {
        val keyPreference = stringPreferencesKey(key)
        return withContext(Dispatchers.IO) {
            context.dataStore.data
                .map { preferences -> preferences[keyPreference] ?: "" }
                .first() // Use flow to get the first value
        }
    }

    // Example function to set a value in DataStore
    suspend fun setData(key: String, value: String) {
        val keyPreference = stringPreferencesKey(key)
        Log.e("check", "setData: "+value +"key is "+key)
        withContext(Dispatchers.IO) {
            context.dataStore.edit { preferences ->
                preferences[keyPreference] = value
            }
        }
    }
}