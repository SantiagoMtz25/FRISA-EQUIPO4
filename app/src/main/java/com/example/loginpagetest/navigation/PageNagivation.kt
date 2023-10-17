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
import com.example.loginpagetest.screens.myosc.myOSC
import com.example.loginpagetest.screens.oscpage.OSCPage
import com.example.loginpagetest.screens.test.MainScreen
import com.example.loginpagetest.viewmodel.AppViewModel

@Composable
fun PageNavigation (appViewModel: AppViewModel) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "login"
    ) {
        composable("login") {
            myLoginApp(navController, appViewModel)
        }
        /*composable("home") {
            OrganizationsCatalogue(navController)
        }*/
        composable("create_account") {
            CreateAccount(navController, appViewModel)
        }

        composable("OSCpage") {
            OSCPage(navController, appViewModel)
        }

        // Main screen will the receive the parameter type in order to load different view components
        composable("testScreen") {
            MainScreen(navController, appViewModel)
        }
        composable("myfavourites") {
            myFavourites(navController, appViewModel)
        }
        composable("aboutapp") {
            aboutApp(navController, appViewModel)
        }

        composable("inviteUser/{inviteUser}",
            arguments = listOf(navArgument("inviteUser") { type = NavType.BoolType })
        ) {
            inviteUser(navController, appViewModel) // I do not think I should send it here
        }

        composable("accountManager") {
            accountManager(navController, appViewModel)
        }

        composable("myOSC") {
            myOSC(navController, appViewModel)
        }
    }
}
