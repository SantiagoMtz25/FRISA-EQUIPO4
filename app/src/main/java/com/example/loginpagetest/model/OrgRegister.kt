package com.example.loginpagetest.model

import com.example.loginpagetest.screens.homepage.Category

data class OrgRegister(
    val name: String,
    val adminName: String,
    val rfc: String,
    val description: String,
    val phoneNumber: String,
    val state: String,
    val city: String,
    val email: String,
    val webpage: String,
    val category: String
)
