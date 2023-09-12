package com.example.loginpagetest.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.loginpagetest.screens.homepage.HomePage
import com.example.loginpagetest.screens.loginpage.myLoginApp

@Composable
fun PageNagivation () {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            myLoginApp(navController)
        }
        composable("home") {
            HomePage(navController)
        }
    }
}
