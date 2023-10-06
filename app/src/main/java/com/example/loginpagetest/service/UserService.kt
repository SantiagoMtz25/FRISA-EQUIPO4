package com.example.loginpagetest.service

import com.example.loginpagetest.model.UserFavToDelete
import com.example.loginpagetest.model.UserFavToDeleteResponse
import com.example.loginpagetest.model.UserFavourites
import com.example.loginpagetest.model.UserFavouritesResponse
import com.example.loginpagetest.model.UserLogin
import com.example.loginpagetest.model.UserLoginResponse
import com.example.loginpagetest.model.UserRegister
import com.example.loginpagetest.model.UserRegistrationResponse
import com.example.loginpagetest.model.UserUpdateAccount
import com.example.loginpagetest.model.UserUpdateAccountResponse
import com.example.loginpagetest.model.userfavourites.GetUserFavoriteOrganizationsResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

/* This code defines a UserService interface,
   which is used to create a Retrofit service for handling
   HTTP requests related to user operations.
 */

interface UserService {

    companion object {
        // place our link here from FL0 server once api is uploaded
        val instance: UserService = Retrofit.Builder().baseUrl("https://api-test-frisa-rmex-dev.fl0.io/users/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UserService::class.java)
    }

    @POST("register")
    suspend fun insertUser(@Body user: UserRegister): UserRegistrationResponse

    @POST("login")
    suspend fun loginUser(@Body user: UserLogin): UserLoginResponse

    @POST("addfavourite")
    suspend fun addFavourite(@Body userFav: UserFavourites) : UserFavouritesResponse

    @POST("removeFavourite")
    suspend fun removeFavourite(@Body userFavRemove: UserFavToDelete) : UserFavToDeleteResponse

    @POST("userUpdateAccount")
    suspend fun updateAccount(@Body userUpdate: UserUpdateAccount) : UserUpdateAccountResponse

    @GET("getUserFavoriteOrganizations/")
    suspend fun getUserFavoriteOrganization(
        @Header("Authorization") token: String
    ): GetUserFavoriteOrganizationsResponse

}