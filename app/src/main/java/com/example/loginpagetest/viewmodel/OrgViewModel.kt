package com.example.loginpagetest.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loginpagetest.model.OrgRegister
import com.example.loginpagetest.model.OrgRegisterResponse
import com.example.loginpagetest.model.UserLogin
import com.example.loginpagetest.model.UserLoginResponse
import com.example.loginpagetest.model.UserProtectedResponse
import com.example.loginpagetest.model.UserRegister
import com.example.loginpagetest.model.UserRegistrationResponse
import com.example.loginpagetest.service.OrgService
import com.example.loginpagetest.service.UserService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException

class OrgViewModel(private val orgService: OrgService) : ViewModel() {

    private val _orgRegisterResult = MutableStateFlow<OrgRegisterResponse?>(null)
    val orgRegisterResult: StateFlow<OrgRegisterResponse?>
        get() = _orgRegisterResult

    fun addOrganization(token: String, org: OrgRegister) {

        viewModelScope.launch {

            var response: OrgRegisterResponse? = null

            try {

                response = orgService.addOrg(token, org)
                _orgRegisterResult.value = response
                response.message?.let { Log.d("RESPONSE", it) }

            } catch (e: Exception) {
                Log.d("RESPONSE", e.localizedMessage)
            }
        }
    }
}
