package com.example.loginpagetest.screens.test

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.loginpagetest.R
import com.example.loginpagetest.screens.homepage.OrganizationsCatalogue
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(content: NavHostController) {
    var drawerState by remember { mutableStateOf(DrawerValue.Closed) }
    val coroutineScope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState(rememberDrawerState(drawerState))
    val myColor = colorResource(id = R.color.logoRed)

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = { Text("Welcome (insert name)", color = Color.White)},
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
            DrawerContent(content)
        }
    ) {
        // Text("Main Screen Content", modifier = Modifier.fillMaxSize(), style = TextStyle(textAlign = TextAlign.Center))
        OrganizationsCatalogue(content)
    }
}

@Composable
fun DrawerContent(content: NavHostController) {
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
            )
            // Profile Name
            Text(
                text = "Your Name",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 4.dp)
            )
            // Profile Email
            Text(
                text = "youremail@example.com",
                fontSize = 14.sp,
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
            )
            // Divider
            Divider(modifier = Modifier.padding(horizontal = 16.dp))

            // Menu Items
            Text(
                text = "Home",
                fontSize = 18.sp,
                modifier = Modifier
                    .padding(16.dp)
                    .clickable {
                        content.navigate("testScreen")
                    }
            )
            Text(
                text = "My Favorites",
                fontSize = 18.sp,
                modifier = Modifier
                    .padding(16.dp)
                    .clickable {
                        content.navigate("myfavourites")
                    }
            )
            // Divider
            Divider(modifier = Modifier.padding(horizontal = 16.dp))
            // Future adds can go here
            Text(
                text = "About App",
                fontSize = 18.sp,
                modifier = Modifier
                    .padding(16.dp)
                    .clickable {
                        content.navigate("aboutapp")
                    }
            )
        }
    }
}