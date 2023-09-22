package com.example.loginpagetest.screens.favourites

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.DrawerValue
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.rememberDrawerState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.loginpagetest.R
import com.example.loginpagetest.screens.homepage.Chip
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun myFavourites(content: NavHostController) {
    val scrollState = rememberScrollState()
    val custombeige = colorResource(id = R.color.lightgray_beige)
    var searchQuery by remember { mutableStateOf("") }
    var drawerState by remember { mutableStateOf(DrawerValue.Closed) }
    val coroutineScope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState(rememberDrawerState(drawerState))
    val myColor = colorResource(id = R.color.logoRed)
    val organizationsMap = mapOf(
        "Salud" to listOf("Org salud 1", "Org salud 2", "Org salud 3"),
        "Educación" to listOf("Org educación 1", "Org educación 2", "Org educación 3"),
        "Medio Ambiente" to listOf("Org medio ambiente 1", "Org medio ambiente 2", "Org medio ambiente 3"),
        "Derechos humanos" to listOf("Org derechos humanos 1", "Org derechos humanos 2", "Org derechos humanos 3")
    )

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = { Text("Your favourites", color = Color.White) },
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
        Column(
            modifier = Modifier
                .padding(16.dp)
                .background(MaterialTheme.colorScheme.background)
        ) {
            LazyRow(
                modifier = Modifier.padding(bottom = 16.dp) // Added bottom padding here
            ) {
                items(listOf("Salud", "Educación", "Medio Ambiente", "Derechos humanos")) { tag ->
                    Chip(tag = tag, onClick = {
                        searchQuery = tag
                    }, modifier = Modifier.padding(end = 8.dp))
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentSize(Alignment.Center)
            ) {
                val cardHeight = 300.dp
                val cardWidth = 360.dp
                Card(
                    modifier = Modifier
                        .height(cardHeight)
                        .width(cardWidth)
                        .background(MaterialTheme.colorScheme.surface)
                ) {
                    LazyColumn {
                        items(3) {
                            Card(
                                modifier = Modifier
                                    .padding(8.dp)
                                    .height(cardHeight - 16.dp)
                                    .width(cardWidth - 16.dp)
                                    .background(MaterialTheme.colorScheme.surface),
                                backgroundColor = custombeige
                            ) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(16.dp) // Adjust the padding as needed
                                ) {
                                    Image(
                                        painter = painterResource(id = android.R.drawable.ic_menu_gallery),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .size(40.dp) // Adjust the size as needed
                                            .clip(CircleShape)
                                            .background(myColor)
                                    )
                                    Text(
                                        "OSC Number #",
                                        style = TextStyle(
                                            fontSize = 20.sp,
                                            fontWeight = FontWeight.Bold,
                                            textAlign = TextAlign.Center
                                        ),
                                        modifier = Modifier.padding(top = 8.dp) // Adjust the padding as needed
                                    )

                                    Text(
                                        "Welcome to OSC Number #\n" +
                                                "We would like to share that we have reached our most important goal of reaching to ...\n" +
                                                "For the past year and a half, we have been working on a project called ... which sought to ...\n\n"
                                                    .trimIndent(),
                                        style = TextStyle(fontSize = 16.sp, textAlign = TextAlign.Justify),
                                        modifier = Modifier.padding(top = 8.dp, bottom = 8.dp) // Adjust the padding as needed
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
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