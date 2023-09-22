package com.example.loginpagetest.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.loginpagetest.screens.aboutapp.aboutApp
import com.example.loginpagetest.screens.homepage.OrganizationsCatalogue
import com.example.loginpagetest.screens.loginpage.myLoginApp
import com.example.loginpagetest.screens.createaccount.CreateAccount
import com.example.loginpagetest.screens.favourites.myFavourites
import com.example.loginpagetest.screens.oscpage.OSCPage
import com.example.loginpagetest.screens.test.MainScreen

@Composable
fun PageNagivation () {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            myLoginApp(navController)
        }
        composable("home") {
            OrganizationsCatalogue(navController)
        }
        composable("create_account") {
            CreateAccount(navController)
        }
        composable("OSCpage") {
            OSCPage(navController)
        }
        composable("testScreen") {
            MainScreen(navController)
        }
        composable("myfavourites") {
            myFavourites(navController)
        }
        composable("aboutapp") {
            aboutApp(navController)
        }
    }
}
