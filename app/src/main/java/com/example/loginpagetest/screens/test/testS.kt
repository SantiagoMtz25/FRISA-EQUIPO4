package com.example.loginpagetest.screens.test

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.navigation.NavHostController
import com.example.loginpagetest.R
import com.example.loginpagetest.screens.homepage.OrganizationsCatalogue
import com.example.loginpagetest.viewmodel.AppViewModel
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(navController: NavHostController, appViewModel: AppViewModel) {
    var drawerState by remember { mutableStateOf(DrawerValue.Closed) }
    val coroutineScope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState(rememberDrawerState(drawerState))
    val myColor = colorResource(id = R.color.logoRed)

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = { Text("Bienvenid@ ${appViewModel.getName()}", color = Color.White)},
                navigationIcon = {
                    IconButton(onClick = {
                        coroutineScope.launch {
                            if (scaffoldState.drawerState.isOpen) {
                                scaffoldState.drawerState.close()
                            } else {
                                scaffoldState.drawerState.open()
                            }
                        }
                    }) {
                        Icon(imageVector = Icons.Default.Menu, contentDescription = "Menu", tint = Color.White)
                    }
                },
                backgroundColor = myColor
            )
        },
        drawerContent = {
            DrawerContent(navController, appViewModel.isAdmin(), appViewModel)
        }
    ) {

        OrganizationsCatalogue(appViewModel, navController, inviteUser = false)
    }
}
