package com.dundxn.dishdazzle.data.pref

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Application.datastore: DataStore<Preferences> by preferencesDataStore(name = "session")

class UserPreference(private val dataStore: DataStore<Preferences>) {
    suspend fun saveSession(email: String, name: String, isLogin: Boolean) {
        dataStore.edit { preferences ->
            preferences[EMAIL] = email
            preferences[NAME] = name
            preferences[LOGIN_KEY] = isLogin
        }
    }

    suspend fun clearSession() {
        dataStore.edit { preferences ->
            preferences[EMAIL] = ""
            preferences[NAME] = ""
            preferences[LOGIN_KEY] = false
        }
    }

    fun isLogin(): Flow<Boolean> {
        return dataStore.data.map { preferences ->
            preferences[LOGIN_KEY] ?: false
        }
    }

    fun getName(): Flow<String> {
        return dataStore.data.map { preferences ->
            preferences[NAME] ?: ""
        }
    }

    fun getEmail(): Flow<String> {
        return dataStore.data.map { preferences ->
            preferences[EMAIL] ?: ""
        }
    }

    companion object {
        private var INSTANCE: UserPreference? = null

        private val EMAIL = stringPreferencesKey("email")
        private val LOGIN_KEY = booleanPreferencesKey("isLogin")
        private val NAME = stringPreferencesKey("name")

        fun getInstance(dataStore: DataStore<Preferences>): UserPreference {
            return INSTANCE ?: synchronized(this) {
                val instance = UserPreference(dataStore)
                INSTANCE = instance
                instance
            }
        }
    }
}