package com.example.loginpagetest.model

data class UserLoginResponse(
    val token: String? = "",
    var message: String? = null,
    var isAdmin: Boolean = false
)