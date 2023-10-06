package com.example.loginpagetest.model

data class UserFavourites(
    // according to me to add it to the respective user in the db
    val token: String? = "",
    val name: String,
    val category: String
)
