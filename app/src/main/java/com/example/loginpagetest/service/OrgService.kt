package com.example.loginpagetest.service

import com.example.loginpagetest.model.OrgRegister
import com.example.loginpagetest.model.OrgRegisterResponse
import com.example.loginpagetest.model.UserLogin
import com.example.loginpagetest.model.UserLoginResponse
import com.example.loginpagetest.model.UserProtectedResponse
import com.example.loginpagetest.model.UserRegister
import com.example.loginpagetest.model.UserRegistrationResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface OrgService {

    companion object {

        val instance: OrgService = Retrofit.Builder().baseUrl("https://api-android2023-klhg-dev.fl0.io/orgs/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(OrgService::class.java)
    }

    @POST("add")
    suspend fun addOrg(@Header("Authorization") token: String, @Body org: OrgRegister) : OrgRegisterResponse

}