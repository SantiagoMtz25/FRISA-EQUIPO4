package com.example.loginpagetest.service

import com.example.loginpagetest.model.OrgGrade
import com.example.loginpagetest.model.OrgGradeResponse
import com.example.loginpagetest.model.OrgRegister
import com.example.loginpagetest.model.OrgRegisterResponse
import com.example.loginpagetest.model.OrgUpdateAccount
import com.example.loginpagetest.model.OrgUpdateAccountResponse
import com.example.loginpagetest.model.getoscaverage.OrgAverageResponse
import com.example.loginpagetest.model.osclogin.OrgLogin
import com.example.loginpagetest.model.osclogin.OrgLoginResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT

interface OrgService {

    companion object {

        val instance: OrgService = Retrofit.Builder().baseUrl("https://api-test-frisa-rmex-dev.fl0.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(OrgService::class.java)

    }

    @POST("auth/oscregister")
    suspend fun addOrg(@Header("Authorization") token: String, @Body osc: OrgRegister) : OrgRegisterResponse

    @PUT("auth/osclogin")
    suspend fun loginOrg(@Body org: OrgLogin) : OrgLoginResponse

    @PATCH("osc/orgUpdateAccount")
    suspend fun updateAccount(
        @Header("Authorization") token: String,
        @Body orgUpdate: OrgUpdateAccount
    ) : OrgUpdateAccountResponse

    @GET("osc/getgrade")
    suspend fun getAverage(@Header("Authorization") token: String) : OrgAverageResponse

}