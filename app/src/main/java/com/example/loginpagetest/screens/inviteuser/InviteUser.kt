package com.example.loginpagetest.screens.inviteuser

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.loginpagetest.navigation.CustomTopBar
import com.example.loginpagetest.screens.homepage.OrganizationsCatalogue
import com.example.loginpagetest.viewmodel.AppViewModel

@Composable
fun inviteUser (navController: NavHostController, appViewModel: AppViewModel, inviteUser: Boolean) {
    Column {
        CustomTopBar(title = "Bienvenid@", navController = navController, screen = "login")
        OrganizationsCatalogue(appViewModel, navController = navController, inviteUser = inviteUser)
    }
}