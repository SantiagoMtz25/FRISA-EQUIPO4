package com.example.loginpagetest.model

data class UserRegister(
    val name: String,
    val lastname: String,
    val email: String,
    val password: String,
    val confirmPassword: String,
    val phoneNumber: String,
    val state: String,
    val city: String
)
