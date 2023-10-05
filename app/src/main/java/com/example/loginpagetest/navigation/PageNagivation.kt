package com.example.loginpagetest.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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

@Composable
fun PageNavigation (tokenResult: Boolean) {
    val navController = rememberNavController()

    var startScreen by remember { mutableStateOf("") }

    if (tokenResult) {
        startScreen = "testScreen/{isAdmin}"
    } else {
        startScreen = "login"
    }

    NavHost(navController = navController, startDestination = startScreen) {
        composable("login") {
            myLoginApp(navController)
        }
        /*composable("home") {
            OrganizationsCatalogue(navController)
        }*/
        composable("create_account") {
            CreateAccount(navController)
        }

        composable("OSCpage/{inviteUser}/{isAdmin}/{organization}",
            arguments = listOf(
                navArgument("inviteUser") { type = NavType.BoolType },
                navArgument("isAdmin") { type = NavType.BoolType },
                navArgument("organization") { type = NavType.StringType }
            )
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

        composable("myOSC/{isAdmin}",
            arguments = listOf(navArgument("isAdmin") { type = NavType.BoolType })
        ) {
            myOSC(navController)
        }
    }
}
