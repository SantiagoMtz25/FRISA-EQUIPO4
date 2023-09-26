package com.example.loginpagetest.dataStore

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

/* These extension functions help in performing basic CRUD operations with the Android Jetpack's
   DataStore in a more concise and manageable way. They extend the Context object,
   which makes these functions universally accessible in any part of the Android application
   where a Context is available, helping developers avoid boilerplate code related to
   DataStore operations.*/

const val DATASTORE = "my_datastore"

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = DATASTORE)

/* suspend fun storeValue(value: String, key:  Preferences.Key<String>) {
    context.dataStore.edit {
        it[key] = value
    }
    Log.d("DATASTORE", "Value saved: $value")

}*/

suspend fun <T> Context.storeValue(value: T, key: Preferences.Key<T>) {
    dataStore.edit { preferences ->
        preferences[key] = value
    }
    Log.d("DATASTORE", "Value saved: $value")
}

suspend fun Context.deleteValue(key: Preferences.Key<*>) {
    dataStore.edit { preferences ->
        preferences.remove(key)
    }
    Log.d("DATASTORE", "Key deleted: ${key.name}")
}

suspend fun Context.hasKeyWithValue(key:  Preferences.Key<String>): Boolean {

    return withContext(Dispatchers.IO) {
        dataStore.data.map { preferences ->
            preferences[key] ?: ""
        }.first().isNotEmpty()
    }
}

// val token: String = getValueFromDataStore(TOKEN, "")
// val someValue: Int = getValueFromDataStore(SOME_KEY, 0)
suspend inline fun <reified T : Any> Context.getValueFromDataStore(key: Preferences.Key<T>, defaultValue: T): T {
    return withContext(Dispatchers.IO) {
        dataStore.data.map { preferences ->
            preferences[key] ?: defaultValue
        }.first()
    }
}


