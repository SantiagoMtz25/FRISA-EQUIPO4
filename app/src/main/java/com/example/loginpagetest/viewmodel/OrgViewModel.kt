package com.example.loginpagetest.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loginpagetest.model.OrgGrade
import com.example.loginpagetest.model.OrgGradeResponse
import com.example.loginpagetest.model.OrgRegister
import com.example.loginpagetest.model.OrgRegisterResponse
import com.example.loginpagetest.model.OrgUpdateAccount
import com.example.loginpagetest.model.OrgUpdateAccountResponse
import com.example.loginpagetest.model.getoscaverage.OrgAverageResponse
import com.example.loginpagetest.service.OrgService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class OrgViewModel(private val orgService: OrgService) : ViewModel() {

    private val _orgRegisterResult = MutableStateFlow<OrgRegisterResponse?>(null)
    val orgRegisterResult: StateFlow<OrgRegisterResponse?> = _orgRegisterResult

    private val _orgAddGradeResult = MutableStateFlow<OrgGradeResponse?>(null)
    val orgAddGradeResult: StateFlow<OrgGradeResponse?> = _orgAddGradeResult

    private val _updateAccountResult = MutableStateFlow<OrgUpdateAccountResponse?>(null)
    val updateAccountResult: StateFlow<OrgUpdateAccountResponse?> = _updateAccountResult

    private val _getAverageResult = MutableStateFlow<OrgAverageResponse?>(null)
    val getAverageResult: StateFlow<OrgAverageResponse?> = _getAverageResult

    fun addGrade (
        name: String,
        average: Float
    ) {
        val oscgrade = OrgGrade(name, average)

        viewModelScope.launch {
            var response: OrgGradeResponse
            try {
                response = orgService.addGrade(oscgrade)
                _orgAddGradeResult.value = response
            } catch (e: Exception) {

                var errorResponse = OrgGradeResponse("")
                errorResponse.message = e.localizedMessage
                _orgAddGradeResult.value = errorResponse
            }
        }
    }

    fun addOrganization(
        token: String,
        org: OrgRegister
    ) {

        viewModelScope.launch {
            //var response: OrgRegisterResponse? = null
            /*try {
                response = orgService.addOrg(name, adminName, rfc, description, phoneNumber, state, city, email, webpage)
                _orgRegisterResult.value = response
                response.message?.let { Log.d("RESPONSE", it) }

            } catch (e: Exception) {
                Log.d("RESPONSE", e.localizedMessage)
            }*/
            viewModelScope.launch {
                var response: OrgRegisterResponse
                try {
                    response = orgService.addOrg(token, org)
                    _orgRegisterResult.value = response
                } catch (e: Exception) {

                    var errorResponse = OrgRegisterResponse("")
                    errorResponse.message = e.localizedMessage
                    _orgRegisterResult.value = errorResponse
                }
            }
        }
    }

    fun orgUpdateAccount (
        token: String?,
        state: String,
        city: String,
        phoneNumber: String,
        description: String,
        rfc: String,
        webpage: String,
        category: String
    ) {
        val orgUpdate = OrgUpdateAccount(token, state, city, phoneNumber, description, rfc, webpage, category)

        viewModelScope.launch {
            var response: OrgUpdateAccountResponse

            try {
                response = orgService.updateAccount(orgUpdate)
                _updateAccountResult.value = response
            } catch (e: Exception) {
                var errorResponse = OrgUpdateAccountResponse("")
                errorResponse.message = e.localizedMessage
                _updateAccountResult.value = errorResponse
            }
        }
    }

    fun getAverage (token: String?) {
        viewModelScope.launch {
            val response: OrgAverageResponse
            try {
                response = orgService.getAverage(token)
                _getAverageResult.value = response
            } catch (e: Exception) {

                val errorResponse = e.localizedMessage
                Log.d("ERROR-API", errorResponse)
                //_getUserFavoriteOrgsResult.value = errorResponse
            }
        }
    }
}
