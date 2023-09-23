package com.example.loginpagetest.screens.inviteuser

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.loginpagetest.screens.homepage.OrganizationsCatalogue

@Composable
fun inviteUser (navController: NavHostController) {
    OrganizationsCatalogue(content = navController)    
}