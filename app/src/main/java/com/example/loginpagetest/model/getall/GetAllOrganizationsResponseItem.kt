package com.example.loginpagetest.model.getall

data class GetAllOrganizationsResponseItem (
    val orgId: String,
    val name: String,
    val description: String,
    val phoneNumber: String,
    val email: String,
    val webpage: String,
    val category: String
)