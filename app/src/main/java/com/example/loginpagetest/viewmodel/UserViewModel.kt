package com.example.loginpagetest.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loginpagetest.model.OrgGrade
import com.example.loginpagetest.model.OrgGradeResponse
import com.example.loginpagetest.model.UserFavToDelete
import com.example.loginpagetest.model.UserFavToDeleteResponse
import com.example.loginpagetest.model.UserFavouritesResponse
import com.example.loginpagetest.model.UserLogin
import com.example.loginpagetest.model.UserLoginResponse
import com.example.loginpagetest.model.UserRegister
import com.example.loginpagetest.model.UserRegistrationResponse
import com.example.loginpagetest.model.UserUpdateAccount
import com.example.loginpagetest.model.UserUpdateAccountResponse
import com.example.loginpagetest.model.getall.GetAllOrganizationsResponse
import com.example.loginpagetest.model.userfavourites.GetUserFavoriteOrganizationsResponse
import com.example.loginpagetest.service.UserService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException

class UserViewModel(private val userService: UserService) : ViewModel() {

    // Normal user registration result
    private val _registrationResult = MutableStateFlow<UserRegistrationResponse?>(null)
    val registrationResult: StateFlow<UserRegistrationResponse?> = _registrationResult

    // Login result
    private val _loginResult = MutableStateFlow<UserLoginResponse?>(null)
    val loginResult: StateFlow<UserLoginResponse?> = _loginResult

    // Add a favourite, called when the OSC in the catalogue star is clicked
    private val _addFavouriteResult = MutableStateFlow<UserFavouritesResponse?>(null)
    val addFavouriteResult: StateFlow<UserFavouritesResponse?> = _addFavouriteResult

    private val _removeFavouriteResult = MutableStateFlow<UserFavToDeleteResponse?>(null)
    val removeFavouriteResult: StateFlow<UserFavToDeleteResponse?> = _removeFavouriteResult

    private val _updateAccountResult = MutableStateFlow<UserUpdateAccountResponse?>(null)
    val updateAccountResult: StateFlow<UserUpdateAccountResponse?> = _updateAccountResult

    private val _getUserFavoriteOrgsResult = MutableStateFlow<GetUserFavoriteOrganizationsResponse?>(null)
    val getUserFavoriteOrgsResult: StateFlow<GetUserFavoriteOrganizationsResponse?> = _getUserFavoriteOrgsResult

    private val _orgAddGradeResult = MutableStateFlow<OrgGradeResponse?>(null)
    val orgAddGradeResult: StateFlow<OrgGradeResponse?> = _orgAddGradeResult

    private val _getAllOrganizationsResult = MutableStateFlow<GetAllOrganizationsResponse?>(null)
    val getAllOrganizationsResult: StateFlow<GetAllOrganizationsResponse?> =
        _getAllOrganizationsResult

    // is this like creating account?
    fun addUser(
        name: String,
        lastname: String,
        email: String,
        password: String,
        phoneNumber: String,
        state: String,
        city: String
    ) {
        val user = UserRegister(name, lastname, email, password, phoneNumber, state, city)

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
            } catch (e: Exception) {
                Log.d("RESPONSE", e.localizedMessage)
                val errorMessage = e.localizedMessage
                val errorResponse = UserLoginResponse(null, errorMessage)
                _loginResult.value = errorResponse
            }
        }
    }

    fun addFavourite(
        token: String,
        oscId: String
    ) {
        viewModelScope.launch {
            var response: UserFavouritesResponse

            try {
                response = userService.addFavourite(token, oscId)
                _addFavouriteResult.value = response
            } catch (e: Exception) {

                var errorResponse = UserFavouritesResponse("")
                errorResponse.message = e.localizedMessage
                _addFavouriteResult.value = errorResponse
            }
        }
    }

    fun removeFavourite(
        token: String,
        oscId: String
    ) {
        viewModelScope.launch {
            var response: UserFavToDeleteResponse

            try {
                response = userService.removeFavourite(token, oscId)
                _removeFavouriteResult.value = response
            } catch (e: Exception) {

                var errorResponse = UserFavToDeleteResponse("")
                errorResponse.message = e.localizedMessage
                _removeFavouriteResult.value = errorResponse
            }
        }
    }

    fun userUpdateAccount(
        token: String,
        state: String,
        city: String,
        phoneNumber: String,
        password: String
    ) {
        val userUpdate = UserUpdateAccount(state, city, phoneNumber, password)

        viewModelScope.launch {
            var response: UserUpdateAccountResponse

            try {
                response = userService.updateAccount(token, userUpdate)
                _updateAccountResult.value = response
            } catch (e: Exception) {
                var errorResponse = UserUpdateAccountResponse("")
                errorResponse.message = e.localizedMessage
                _updateAccountResult.value = errorResponse
            }
        }
    }

    fun getUserFavoriteOrganization(token: String) {
        viewModelScope.launch {
            val response: GetUserFavoriteOrganizationsResponse
            try {
                response = userService.getUserFavoriteOrganization(token)
                _getUserFavoriteOrgsResult.value = response
            } catch (e: Exception) {

                val errorResponse = e.localizedMessage
                Log.d("ERROR-API", errorResponse)
                //_getUserFavoriteOrgsResult.value = errorResponse
            }
        }
    }

    fun addGrade(
        token: String,
        orgId: String,
        grade: Int
    ) {
        val oscgrade = OrgGrade(grade)

        viewModelScope.launch {
            var response: OrgGradeResponse
            try {
                response = userService.addGrade(token, orgId, oscgrade)
                _orgAddGradeResult.value = response
            } catch (e: Exception) {

                var errorResponse = OrgGradeResponse("")
                errorResponse.message = e.localizedMessage
                _orgAddGradeResult.value = errorResponse
            }
        }
    }

    fun getAllOsc (
        token: String
    ) {
        viewModelScope.launch {
            var response: GetAllOrganizationsResponse
            try {
                response = userService.getAllOsc(token)
                _getAllOrganizationsResult.value = response
            } catch (e: Exception) {
                var errorResponse = GetAllOrganizationsResponse()
                // errorResponse.message = e.localizedMessage
                _getAllOrganizationsResult.value = errorResponse
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