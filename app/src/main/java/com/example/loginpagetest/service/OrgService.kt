package com.example.loginpagetest.service

import com.example.loginpagetest.model.OrgGrade
import com.example.loginpagetest.model.OrgGradeResponse
import com.example.loginpagetest.model.OrgRegister
import com.example.loginpagetest.model.OrgRegisterResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

interface OrgService {

    companion object {

        val instance: OrgService = Retrofit.Builder().baseUrl("https://api-test-frisa-n7cx-dev.fl0.io/users/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(OrgService::class.java)
    }

    @POST("add")
    suspend fun addOrg(@Body osc: OrgRegister) : OrgRegisterResponse

    @POST("addgrade")
    suspend fun addGrade(@Body grade: OrgGrade) : OrgGradeResponse

}