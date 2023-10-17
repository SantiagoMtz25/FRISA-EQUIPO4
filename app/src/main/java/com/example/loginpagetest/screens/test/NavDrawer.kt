package com.example.loginpagetest.screens.test

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.loginpagetest.R
import com.example.loginpagetest.viewmodel.AppViewModel

@Composable
fun DrawerContent(content: NavHostController, isAdmin: Boolean) {
    val myColor = colorResource(id = R.color.lightred_pink)

    val appViewModel: AppViewModel = viewModel()

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        item {
            // Profile Picture
            Image(
                painter = painterResource(id = android.R.drawable.ic_menu_gallery), // Replace this with your image resource
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .padding(16.dp)
                    .border(2.dp, color = myColor)
            )
            // Profile Name
            Text(
                text = "${appViewModel.getName()} ${appViewModel.getLastName()}",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 4.dp)
            )
            // Profile Email
            Text(
                text = "${appViewModel.getEmail()}",
                fontSize = 14.sp,
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
            )
            // Divider
            Divider(modifier = Modifier.padding(horizontal = 16.dp))

            // Menu Items
            Text(
                text = "Catálogo ",
                fontSize = 18.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .clickable {
                        content.navigate("testScreen")
                    }
            )
            if (isAdmin) {
                // Load something else here
                Text(
                    text = "Mi Organización",
                    fontSize = 18.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .clickable {
                            content.navigate("myOSC")
                        }
                )
            } else {
                Text(
                    text = "Mis Favoritos",
                    fontSize = 18.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .clickable {
                            content.navigate("myfavourites")
                        }
                )
            }
            //Divider(modifier = Modifier.padding(horizontal = 16.dp))
            Text(
                text = "Mi Cuenta",
                fontSize = 18.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .clickable {
                        content.navigate("accountManager")
                    }
            )
            // Divider
            Divider(modifier = Modifier.padding(horizontal = 16.dp))
            // Future adds can go here
            Text(
                text = "Acerca de la App",
                fontSize = 18.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .clickable {
                        content.navigate("aboutapp")
                    }
            )
            //Divider(modifier = Modifier.padding(horizontal = 16.dp))
            Text(
                text = "Cerrar Sesión",
                fontSize = 18.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .clickable {
                        // destroy internally saved user token

                        appViewModel.setLoggedIn(false)
                        appViewModel.deleteToken()
                        appViewModel.setLoggedOut()

                        content.navigate("login")
                    }
            )
        }
    }
}