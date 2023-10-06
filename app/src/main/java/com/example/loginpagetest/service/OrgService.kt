package com.example.loginpagetest.service

import com.example.loginpagetest.model.OrgGrade
import com.example.loginpagetest.model.OrgGradeResponse
import com.example.loginpagetest.model.OrgRegister
import com.example.loginpagetest.model.OrgRegisterResponse
import com.example.loginpagetest.model.OrgUpdateAccount
import com.example.loginpagetest.model.OrgUpdateAccountResponse
import com.example.loginpagetest.model.getoscaverage.OrgAverageResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface OrgService {

    companion object {

        val instance: OrgService = Retrofit.Builder().baseUrl("https://api-test-frisa-rmex-dev.fl0.io/orgs")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(OrgService::class.java)
    }

    @POST("add")
    suspend fun addOrg(@Body osc: OrgRegister) : OrgRegisterResponse

    @POST("addgrade")
    suspend fun addGrade(@Body grade: OrgGrade) : OrgGradeResponse

    @POST("orgUpdateAccount")
    suspend fun updateAccount(@Body orgUpdate: OrgUpdateAccount) : OrgUpdateAccountResponse

    @GET("oscAverage")
    suspend fun getAverage(@Body average: String?) : OrgAverageResponse
}