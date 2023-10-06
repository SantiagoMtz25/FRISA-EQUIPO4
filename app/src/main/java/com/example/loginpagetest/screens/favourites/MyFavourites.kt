package com.example.loginpagetest.screens.favourites

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.loginpagetest.R
import com.example.loginpagetest.model.userfavourites.GetUserFavoriteOrganizationsResponse
import com.example.loginpagetest.screens.homepage.Chip
import com.example.loginpagetest.screens.myosc.Event
import com.example.loginpagetest.screens.test.DrawerContent
import com.example.loginpagetest.service.UserService
import com.example.loginpagetest.viewmodel.UserViewModel
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "UnrememberedMutableState")
@Composable
fun myFavourites(content: NavHostController) {
    val scrollState = rememberScrollState()
    var searchQuery by remember { mutableStateOf("") }
    var drawerState by remember { mutableStateOf(DrawerValue.Closed) }
    val coroutineScope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState(rememberDrawerState(drawerState))
    val myColor = colorResource(id = R.color.logoRed)

    val userViewModel = UserViewModel(UserService.instance)

    var eventsList = listOf(
        Event("Event 1", "2023-10-01", "Salud","This is a short description of Event 1."),
        Event("Event 2", "2023-11-01", "Educación","This is a short description of Event 2."),
        Event("Event 3", "2023-12-01", "Derechos humanos","This is a short description of Event 3."),
        Event("Event 4", "2023-12-01", "Transporte Público","This is a short description of Event 4."),
        Event("Event 5", "2023-12-01", "Medio Ambiente","This is a short description of Event 5.")
    )
    val filteredEvents by derivedStateOf {
        if (searchQuery.isEmpty()) eventsList else eventsList.filter { it.category == searchQuery }
    }
    val Organizations = remember {
        mutableStateOf(GetUserFavoriteOrganizationsResponse())
    }

    LaunchedEffect(key1 = userViewModel.getUserFavoriteOrgsResult) {
        userViewModel.getUserFavoriteOrgsResult.collect { result ->
            if (result != null) {
                // collect the list to a variable to then use it
                val organizationsResponse = GetUserFavoriteOrganizationsResponse()
                organizationsResponse.addAll(result)
                Organizations.value = organizationsResponse
            }
        }
    }

    val isAdmin: Boolean = content.currentBackStackEntry
        ?.arguments?.getBoolean("isAdmin") ?: false

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
            DrawerContent(content, isAdmin)
        }
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .background(color = Color.Transparent)
        ) {
            LazyRow(
                modifier = Modifier.padding(bottom = 16.dp) // Added bottom padding here
            ) {
                items(listOf("Salud", "Educación", "Medio Ambiente", "Derechos humanos",
                    "Asociaciones Religiosas", "Transporte Público", "Cultura", "Servicios Asistenciales"
                )) { tag ->
                    Chip(tag = tag, onClick = {
                        searchQuery = if (searchQuery == tag) "" else tag
                    }, modifier = Modifier.padding(end = 8.dp))
                }
            }

            Text(
                text = "Recent News", fontSize = 20.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally),
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(14.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentSize(Alignment.Center)
            ) {
                val cardHeight = 280.dp
                val cardWidth = 360.dp
                val customGray = colorResource(id = R.color.lightgray_beige)
                val itemsCount = 3
                val lazyColumnState = rememberLazyListState()

                val currentItemIndex by derivedStateOf {
                    lazyColumnState.firstVisibleItemIndex + 1
                }

                Card(
                    modifier = Modifier
                        .height(cardHeight)
                        .width(cardWidth)
                        .background(Color.White)
                ) {
                    Box (
                        modifier = Modifier.background(Color.White)
                    ) {
                        LazyColumn(state = lazyColumnState) {
                            items(itemsCount) {
                                Card(
                                    modifier = Modifier
                                        .padding(8.dp)
                                        .height(cardHeight - 16.dp)
                                        .width(cardWidth - 16.dp)
                                        .background(color = Color.LightGray),
                                    backgroundColor = Color.LightGray
                                ) {
                                    Column(
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .padding(16.dp)
                                    ) {
                                        Image(
                                            painter = painterResource(id = android.R.drawable.ic_menu_gallery),
                                            contentDescription = null,
                                            modifier = Modifier
                                                .size(40.dp)
                                                .clip(CircleShape)
                                                .background(myColor)
                                        )
                                        Text(
                                            "OSC Number #$currentItemIndex",
                                            style = TextStyle(
                                                fontSize = 20.sp,
                                                fontWeight = FontWeight.Bold,
                                                textAlign = TextAlign.Center
                                            ),
                                            modifier = Modifier.padding(top = 8.dp)
                                        )

                                        Text(
                                            "Welcome to OSC Number #$currentItemIndex\n" +
                                                    "We would like to share that we have reached our most important goal of reaching to ...\n" +
                                                    "For the past year and a half, we have been working on a project called ... which sought to ...\n\n"
                                                        .trimIndent(),
                                            style = TextStyle(fontSize = 16.sp, textAlign = TextAlign.Justify),
                                            modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
                                        )
                                    }
                                }
                            }
                        }
                        // Scroll Indicator
                        Text(
                            text = "$currentItemIndex / $itemsCount",
                            modifier = Modifier
                                .align(Alignment.BottomStart)
                                .padding(8.dp),
                            color = Color.Black, // Adjust color as needed
                            style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold)
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(14.dp))
            Text(
                text = "Upcoming Events", fontSize = 20.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally),
                fontWeight = FontWeight.Bold
            )

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            ) {
                LazyColumn {
                    items(filteredEvents) { event ->
                        EventCard(event = event)
                    }
                }
            }
        }
    }
}

@Composable
fun EventCard(event: Event) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .height(80.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
        ) {
            Text(text = event.title, fontWeight = FontWeight.Bold)
            Row {
                Text(text = event.date)
                Spacer(modifier = Modifier.width(20.dp))
                Text(text = event.category)
            }
            Text(text = event.description)
        }
    }
}