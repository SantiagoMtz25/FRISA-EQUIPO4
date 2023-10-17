package com.example.loginpagetest.model.userfavourites

data class GetUserFavoriteOrganizationsResponseItem(
    val orgId: String,
    val name: String,
    val description: String,
    val category: String,
    val webpage: String,
    val phoneNumber: String
)
