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
import retrofit2.http.POST

interface OrgService {

    companion object {

        val instance: OrgService = Retrofit.Builder().baseUrl("https://api-test-frisa-rmex-dev.fl0.io/osc/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(OrgService::class.java)

    }

    @POST("register")
    suspend fun addOrg(@Header("Authorization") token: String, @Body osc: OrgRegister) : OrgRegisterResponse

    @POST("loginOrg")
    suspend fun loginOrg(@Body org: OrgLogin) : OrgLoginResponse

    @POST("addgrade")
    suspend fun addGrade(@Body grade: OrgGrade) : OrgGradeResponse

    @POST("orgUpdateAccount")
    suspend fun updateAccount(@Body orgUpdate: OrgUpdateAccount) : OrgUpdateAccountResponse

    @GET("oscAverage")
    suspend fun getAverage(@Body average: String?) : OrgAverageResponse

    @GET("getAll")
    suspend fun getAllOsc(@Body osc: OrgRegister) // Yet to implement
}