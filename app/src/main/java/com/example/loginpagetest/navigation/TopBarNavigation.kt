package com.example.loginpagetest.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun CustomTopBar(title: String, navController: NavHostController, screen: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)  // Fixed height
            .background(Color.Red),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = "Back Arrow",
            tint = Color.White,
            modifier = Modifier
                .clickable {
                    if (screen.isNotEmpty()) {
                        navController.navigate(screen)
                    } else {
                        navController.navigateUp()
                    }
                }
                .padding(start = 16.dp, end = 16.dp)
        )
        Text(
            text = title,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
    }
}
