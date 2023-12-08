package com.bcare.bcareapp.data.local.preference

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class UserPreference private constructor(private val dataStore: DataStore<Preferences>){

    fun getToken(): Flow<String?> {
        return dataStore.data.map { preferences ->
            preferences[TOKEN]
        }
    }

    suspend fun saveToken(token: String) {
        dataStore.edit {preferences ->
            preferences[TOKEN] = token
        }
    }

    suspend fun  logout() {
        dataStore.edit {
            it.clear()
        }
    }

    companion object{
        private val TOKEN = stringPreferencesKey("token")

        @Volatile
        private var INSTANCE: UserPreference? = null

        fun getInstance(dataStore: DataStore<Preferences>): UserPreference {
            return INSTANCE ?: synchronized(this) {
                val instance = UserPreference(dataStore)
                INSTANCE = instance
                instance
            }
        }
    }
}