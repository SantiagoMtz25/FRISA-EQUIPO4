package com.example.loginpagetest.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loginpagetest.model.OrgGrade
import com.example.loginpagetest.model.OrgGradeResponse
import com.example.loginpagetest.model.OrgRegister
import com.example.loginpagetest.model.OrgRegisterResponse
import com.example.loginpagetest.service.OrgService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class OrgViewModel(private val orgService: OrgService) : ViewModel() {

    private val _orgRegisterResult = MutableStateFlow<OrgRegisterResponse?>(null)
    val orgRegisterResult: StateFlow<OrgRegisterResponse?> = _orgRegisterResult

    private val _orgAddGradeResult = MutableStateFlow<OrgGradeResponse?>(null)
    val orgAddGradeResult: StateFlow<OrgGradeResponse?> = _orgAddGradeResult

    fun addGrade (
        average: Float
    ) {
        val oscgrade = OrgGrade(average)

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
        name: String,
        adminName: String,
        rfc: String,
        description: String,
        phoneNumber: String,
        state: String,
        city: String,
        email: String,
        webpage: String,
        category: String
    ) {

        viewModelScope.launch {

            val osc = OrgRegister(name, adminName, rfc, description, phoneNumber, state, city, email, webpage, category)

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
                    response = orgService.addOrg(osc)
                    _orgRegisterResult.value = response
                } catch (e: Exception) {

                    var errorResponse = OrgRegisterResponse("")
                    errorResponse.message = e.localizedMessage
                    _orgRegisterResult.value = errorResponse
                }
            }
        }
    }
}
