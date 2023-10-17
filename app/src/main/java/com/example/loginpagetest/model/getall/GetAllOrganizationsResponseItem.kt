package com.example.loginpagetest.model.getall

data class GetAllOrganizationsResponseItem (
    val orgId: String,
    val name: String,
    val description: String,
    val category: String,
    val webpage: String,
    val phoneNumber: String
)