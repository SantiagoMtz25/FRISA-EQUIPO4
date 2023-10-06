package com.example.loginpagetest.model

data class OrgUpdateAccount(
    val token: String? = "",
    val state: String,
    val city: String,
    val phoneNumber: String,
    val description: String,
    val rfc: String,
    val webpage: String,
    val category: String
)
