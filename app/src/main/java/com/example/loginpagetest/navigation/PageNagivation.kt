package com.example.loginpagetest.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.loginpagetest.screens.aboutapp.aboutApp
import com.example.loginpagetest.screens.accountmanager.accountManager
import com.example.loginpagetest.screens.loginpage.myLoginApp
import com.example.loginpagetest.screens.createaccount.CreateAccount
import com.example.loginpagetest.screens.favourites.myFavourites
import com.example.loginpagetest.screens.inviteuser.inviteUser
import com.example.loginpagetest.screens.oscpage.OSCPage
import com.example.loginpagetest.screens.test.MainScreen

@Composable
fun PageNavigation () {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            myLoginApp(navController)
        }
        /*composable("home") {
            OrganizationsCatalogue(navController)
        }*/
        composable("create_account") {
            CreateAccount(navController)
        }

        composable("OSCpage/{inviteUser}",
            arguments = listOf(navArgument("inviteUser") { type = NavType.BoolType })
        ) {
            OSCPage(navController)
        }

        // Main screen will the receive the parameter type in order to load different view components
        composable("testScreen/{isAdmin}",
            arguments = listOf(navArgument("isAdmin") { type = NavType.BoolType })
        ) {
            MainScreen(navController)
        }
        composable("myfavourites/{isAdmin}",
            arguments = listOf(navArgument("isAdmin") { type = NavType.BoolType })
        ) {
            myFavourites(navController)
        }
        composable("aboutapp/{isAdmin}",
            arguments = listOf(navArgument("isAdmin") { type = NavType.BoolType })
        ) {
            aboutApp(navController)
        }

        composable("inviteUser/{inviteUser}",
            arguments = listOf(navArgument("inviteUser") { type = NavType.BoolType })
        ) {
            inviteUser(navController)
        }

        composable("accountManager/{isAdmin}",
            arguments = listOf(navArgument("isAdmin") { type = NavType.BoolType })
        ) {
            accountManager(navController)
        }
    }
}
