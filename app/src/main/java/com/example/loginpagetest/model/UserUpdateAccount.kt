package com.example.loginpagetest.model

data class UserUpdateAccount(
    val token: String? = "",
    val state: String,
    val city: String,
    val phoneNumber: String,
    val password: String,
    val confirmPassword: String
)
