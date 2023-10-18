package com.example.loginpagetest.screens.myosc

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.loginpagetest.R
import com.example.loginpagetest.screens.test.DrawerContent
import com.example.loginpagetest.service.OrgService
import com.example.loginpagetest.viewmodel.AppViewModel
import com.example.loginpagetest.viewmodel.OrgViewModel
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun myOSC (navController: NavHostController, appViewModel: AppViewModel) {
    /*val isAdmin: Boolean = navController.currentBackStackEntry
        ?.arguments?.getBoolean("isAdmin") ?: false*/

    var drawerState by remember { mutableStateOf(DrawerValue.Closed) }
    val coroutineScope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState(rememberDrawerState(drawerState))
    val myColor = colorResource(id = R.color.logoRed)
    var isProgressBarVisible by remember { mutableStateOf(false) }

    val orgViewModel = OrgViewModel(OrgService.instance)
    var average by remember { mutableFloatStateOf(0f) }

    LaunchedEffect(key1 = orgViewModel) {
        orgViewModel.getAverageResult.collect { result ->
            if (result != null) {
                average = result.average
            }
        }
    }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = { Text("Panel de Control", color = Color.White) },
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.frisa),
                        contentDescription = "Profile Picture",
                        modifier = Modifier.size(100.dp)
                    )
                    Text("Nombre OSC: OSC John Doe")
                    Text("Nombre Admin: Admin John Doe")
                }
            }
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .align(Alignment.CenterHorizontally)
                ) {
                    Text(
                        text = "Promedio:",
                        fontSize = 20.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.CenterHorizontally),
                        fontWeight = FontWeight.Bold
                    )
                    Button(
                        onClick = {
                            // Launch the coroutine to execute getAverage
                            coroutineScope.launch {
                                orgViewModel.getAverage(appViewModel.getToken())
                            }
                            // Make ProgressBar visible
                            isProgressBarVisible = true
                        },
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    ) {
                        Text("Ver Mi Promedio")
                    }

                    // Conditionally display the ProgressBar based on isProgressBarVisible
                    if (isProgressBarVisible) {
                        ProgressBar(average = average)
                    }
                }
            }
        }
    }
}

@Composable
fun ProgressBar(average: Float) {
    val totalBarWidth = 200.dp
    val filledWidth = (totalBarWidth.value * average / 5).dp

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(top = 6.dp)
    ) {
        Box(
            modifier = Modifier
                .width(totalBarWidth)
                .height(20.dp)
                .background(Color.LightGray)
        ) {
            Box(
                modifier = Modifier
                    .width(filledWidth)
                    .fillMaxHeight()
                    .background(Color.Blue)
            )
        }
        Row(
            modifier = Modifier.width(totalBarWidth),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            for (i in 1..5) {
                Text(text = i.toString())
            }
        }
    }
}