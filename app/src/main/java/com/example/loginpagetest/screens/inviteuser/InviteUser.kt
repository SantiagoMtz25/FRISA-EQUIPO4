package com.example.loginpagetest.screens.inviteuser

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.loginpagetest.navigation.CustomTopBar
import com.example.loginpagetest.screens.homepage.OrganizationsCatalogue

@Composable
fun inviteUser (navController: NavHostController) {
    Column {
        CustomTopBar(title = "Welcome", navController = navController, screen = "login")
        OrganizationsCatalogue(content = navController)
    }
}