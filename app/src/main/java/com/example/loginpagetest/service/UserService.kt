package com.example.loginpagetest.service

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

/* This code defines a UserService interface,
   which is used to create a Retrofit service for handling
   HTTP requests related to user operations.
 */

interface UserService {

    companion object {
        // place our link here from FL0 server once api is uploaded
        val instance: UserService = Retrofit.Builder().baseUrl("https://api-test-frisa-n7cx-dev.fl0.io/users/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UserService::class.java)
    }

    @POST("register")
    suspend fun insertUser(@Body user: UserRegister): UserRegistrationResponse

    @POST("login")
    suspend fun loginUser(@Body user: UserLogin): UserLoginResponse

    @GET("protected")
    //@Headers("Authorization: {token}")
    suspend fun protectedRoute(@Header("Authorization") token: String) : UserProtectedResponse

}