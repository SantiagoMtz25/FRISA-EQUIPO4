package com.example.loginpagetest.screens.oscpage

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.loginpagetest.R
import com.example.loginpagetest.navigation.CustomTopBar

@Composable
fun OSCPage(content: NavHostController) {
    Column {
        CustomTopBar(title = "OSC Page", navController = content, screen = "home")

        Card(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                // Top section with photo and texts
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Replace with your actual logo and photo
                    Image(
                        painter = painterResource(id = R.drawable.ic_launcher_foreground),
                        contentDescription = "Logo",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(50.dp)
                            .clip(CircleShape)
                    )
                    Spacer(modifier = Modifier.width(8.dp))

                    Column {
                        Text(text = "Name", fontWeight = FontWeight.Bold)
                        Text(text = "Location")
                        Text(text = "Category")
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
                // Description
                Text(
                    text = "This is a description.This is a description.This is a description." +
                            "This is a description.This is a description.This is a description." +
                            "This is a description.This is a description.This is a description." +
                            "This is a description.This is a description.This is a description." +
                            "This is a description.This is a description.This is a description." +
                            "This is a description.This is a description.This is a description.",
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))
                // Section to add two images
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    // Replace these with your actual images
                    Image(
                        painter = painterResource(id = R.drawable.ic_launcher_foreground),
                        contentDescription = "Image 1",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(100.dp)
                    )
                    Image(
                        painter = painterResource(id = R.drawable.ic_launcher_foreground),
                        contentDescription = "Image 2",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(100.dp)
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                // Social Media Information
                Column(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Text(text = "Phone: 123456789")
                    Text(text = "Facebook: @facebook")
                    Text(text = "Instagram: @instagram")
                    Text(text = "Email: email@example.com")
                    Text(text = "Twitter: @twitter")
                }
            }
        }
    }
}
