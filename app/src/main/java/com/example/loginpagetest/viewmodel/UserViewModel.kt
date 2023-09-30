package com.example.loginpagetest.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.loginpagetest.model.UserLogin
import com.example.loginpagetest.model.UserLoginResponse
import com.example.loginpagetest.model.UserProtectedResponse
import com.example.loginpagetest.model.UserRegister
import com.example.loginpagetest.model.UserRegistrationResponse
import com.example.loginpagetest.service.UserService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException

class UserViewModel(private val userService: UserService) : ViewModel() {

    private val _registrationResult = MutableStateFlow<UserRegistrationResponse?>(null)
    val registrationResult: StateFlow<UserRegistrationResponse?> = _registrationResult

    private val _loginResult = MutableStateFlow<UserLoginResponse?>(null)
    val loginResult: StateFlow<UserLoginResponse?>
        get() = _loginResult

    private val _protectedResult = MutableStateFlow<UserProtectedResponse?>(null)
    val protectedResult: StateFlow<UserProtectedResponse?>
        get() = _protectedResult

    // is this like creating account?
    fun addUser(
        name: String,
        lastname: String,
        email: String,
        password: String,
        confirmPassword: String,
        phoneNumber: Int,
        state: String,
        city: String
    ) {
        val user = UserRegister(name, lastname, email, password, confirmPassword, phoneNumber, state, city)

        viewModelScope.launch {
            var response: UserRegistrationResponse
            try {
                response = userService.insertUser(user)
                _registrationResult.value = response
            } catch (e: Exception) {

                var errorResponse = UserRegistrationResponse("")
                errorResponse.message = e.localizedMessage
                _registrationResult.value = errorResponse
            }
        }
    }

    // for our app for the login functionality we'll only use email and password
    fun loginUser(email: String, password: String) {

        // Reset the login result to nul before making a new login request
        _loginResult.value = null

        val user = UserLogin(email, password)

        viewModelScope.launch {
            var response: UserLoginResponse

            try {
                response = userService.loginUser(user)
                _loginResult.value = response
                Log.d("RESPONSE", response.token.toString())
            } catch (e: HttpException) {

                when (e.code()) {

                    401 -> {
                        Log.d("RESPONSE", e.localizedMessage)
                        val errorMessage = "Credenciales incorrectas"
                        val errorResponse = UserLoginResponse(null, errorMessage)
                        _loginResult.value = errorResponse
                    }

                    else -> {
                        Log.d("RESPONSE", e.localizedMessage)
                        val errorMessage = e.localizedMessage
                        val errorResponse = UserLoginResponse(null, errorMessage)
                        _loginResult.value = errorResponse
                    }
                }
            }
            catch (e: Exception){
                Log.d("RESPONSE", e.localizedMessage)
                val errorMessage = e.localizedMessage
                val errorResponse = UserLoginResponse(null, errorMessage)
                _loginResult.value = errorResponse
            }
        }
    }

    fun testProtectedRequest(token : String) {

        viewModelScope.launch {
            var response: UserProtectedResponse? = null
            try {
                response = userService.protectedRoute(token)
                _protectedResult.value = response
                Log.d("RESPONSE", response.message)
            } catch (e: HttpException) {

                when (e.code()) {

                    401 -> {
                        Log.d("RESPONSE", e.localizedMessage)
                        val errorMessage = "Invalid credentials"
                        val errorResponse = UserProtectedResponse(errorMessage)
                        _protectedResult.value = errorResponse
                    }

                    else -> {
                        Log.d("RESPONSE", e.localizedMessage)
                        val errorMessage = e.localizedMessage
                        val errorResponse = UserProtectedResponse(errorMessage)
                        _protectedResult.value = errorResponse
                    }
                }
            }
        }
    }
}

/*
class AppViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AppViewModel::class.java)) {
            return AppViewModel(context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}*/