package com.example.loginpagetest.model.userfavourites

data class GetUserFavoriteOrganizationsResponseItem(
    val orgId: String,
    val name: String,
    val adminName: String,
    val rfc: String,
    val description: String,
    val phoneNumber: String,
    val state: String,
    val city: String,
    val email: String,
    val webpage: String,
    val category: String,
    val avg: Float
)
