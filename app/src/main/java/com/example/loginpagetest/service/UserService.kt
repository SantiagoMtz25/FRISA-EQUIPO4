package com.example.loginpagetest.service

import com.example.loginpagetest.model.OrgGrade
import com.example.loginpagetest.model.OrgGradeResponse
import com.example.loginpagetest.model.UserFavToDeleteResponse
import com.example.loginpagetest.model.UserFavouritesResponse
import com.example.loginpagetest.model.UserLogin
import com.example.loginpagetest.model.UserLoginResponse
import com.example.loginpagetest.model.UserRegister
import com.example.loginpagetest.model.UserRegistrationResponse
import com.example.loginpagetest.model.UserUpdateAccount
import com.example.loginpagetest.model.UserUpdateAccountResponse
import com.example.loginpagetest.model.getall.GetAllOrganizationsResponse
import com.example.loginpagetest.model.userfavourites.GetUserFavoriteOrganizationsResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface UserService {

    companion object {
        // place our link here from FL0 server once api is uploaded
        val instance: UserService = Retrofit.Builder().baseUrl("https://api-test-frisa-rmex-dev.fl0.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UserService::class.java)
    }

    @POST("auth/userregister")
    suspend fun insertUser(@Body user: UserRegister): UserRegistrationResponse

    @PUT("auth/userlogin")
    suspend fun loginUser(@Body user: UserLogin): UserLoginResponse

    @PATCH("users/addFavorite/{organizationId}")
    suspend fun addFavourite(
        @Header("Authorization") token: String,
        @Path("organizationId") organizationId: String
    ): UserFavouritesResponse

    @PATCH("users/removeFavorite    /{organizationId}")
    suspend fun removeFavourite(
        @Header("Authorization") token: String,
        @Path("organizationId") organizationId: String
    ) : UserFavToDeleteResponse

    @PATCH("users/userUpdateAccount")
    suspend fun updateAccount(
        @Header("Authorization") token: String,
        @Body userUpdate: UserUpdateAccount
    ) : UserUpdateAccountResponse

    @GET("users/getUserFavoriteOrganizations")
    suspend fun getUserFavoriteOrganization(
        @Header("Authorization") token: String
    ): GetUserFavoriteOrganizationsResponse

    @PATCH("users/gradeorg/{organizationId}")
    suspend fun addGrade(
        @Header("Authorization") token: String,
        @Path("organizationId") organizationId: String,
        @Body grade: OrgGrade
    ) : OrgGradeResponse

    @GET("users/getAllOrgs")
    suspend fun getAllOsc(
        @Header("Authorization") token: String
    ) : GetAllOrganizationsResponse

}