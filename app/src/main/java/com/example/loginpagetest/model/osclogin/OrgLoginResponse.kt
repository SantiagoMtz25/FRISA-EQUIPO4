package com.example.loginpagetest.model.osclogin

data class OrgLoginResponse(
    val token: String? = "",
    var message: String? = null,
    var isAdmin: Boolean = false
)
