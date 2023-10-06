package com.example.loginpagetest.viewmodel

import android.app.Application
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.loginpagetest.dataStore.deleteValue
import com.example.loginpagetest.dataStore.getValueFromDataStore
import com.example.loginpagetest.dataStore.hasKeyWithValue
import com.example.loginpagetest.dataStore.storeValue
import com.example.loginpagetest.util.constants.Constants
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AppViewModel(private val appContext: Application) : AndroidViewModel(appContext) {


    private val token = mutableStateOf("")
    private val isLoggedIn = mutableStateOf(false)
    private val isAdmin = mutableStateOf(false)
    private val signedPrivacy = mutableStateOf(false)
    private val _isInitialized = MutableStateFlow(false)

    // Con esta variable podemos saber si el proceso de init ya concluyó
    val isInitialized: StateFlow<Boolean>
        get() = _isInitialized


    init {
        viewModelScope.launch {
            val hasTokenStored = appContext.hasKeyWithValue(Constants.TOKEN)
            val token = appContext.getValueFromDataStore(Constants.TOKEN, "")
            val isAdmin = appContext.getValueFromDataStore(Constants.ISADMIN, false)
            val signed = appContext.getValueFromDataStore(Constants.SIGNED_PRIVACY,false)

            if (hasTokenStored) {
                setLoggedIn(true)
                setToken(token)
                setIsAdmin(isAdmin)
            }
            if (signed){
                setSignedPrivacy()
            }
            _isInitialized.value = true
            Log.d("POSTVALUE","posting value *** ${_isInitialized.value}")

        }
    }

    fun <T> storeValueInDataStore(value: T, key: Preferences.Key<T>) {
        viewModelScope.launch {
            appContext.storeValue(value, key)
        }
    }


    fun deleteToken(){
        viewModelScope.launch {
            appContext.deleteValue(Constants.TOKEN)
            token.value =""
            setLoggedOut()
        }
    }

    fun getToken(): String {
        return token.value
    }

    fun setToken(mytoken: String) {
        token.value = mytoken
    }


    fun setLoggedIn(value: Boolean) {
        isLoggedIn.value = value
    }

    fun setLoggedOut() {
        isLoggedIn.value = false
    }

    fun isUserLoggedIn(): Boolean {
        return isLoggedIn.value
    }

    fun isAdmin(): Boolean {
        return isAdmin.value
    }

    fun setIsAdmin(value: Boolean){
        isAdmin.value = value
    }

    fun setSignedPrivacy(){
        signedPrivacy.value = true
    }

    fun isPrivacySigned(): Boolean{
        return signedPrivacy.value
    }
}