package com.example.loginpagetest.screens.privacyscreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.loginpagetest.R
import com.example.loginpagetest.navigation.CustomTopBar
import com.example.loginpagetest.util.constants.Constants.avisoDePrivacidad
import com.example.loginpagetest.viewmodel.AppViewModel

@Composable
fun PrivacyMessage(navController: NavHostController, appViewModel: AppViewModel) {
    val customRed = colorResource(id = R.color.logoRed)

    // Use a Column with verticalScroll for a different way to make the content scrollable
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()), // this makes the column scrollable
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CustomTopBar(title = "Aviso de Privacidad", navController = navController, screen = "login")
        Text(text = avisoDePrivacidad, modifier = Modifier.padding(8.dp))
        MaterialTheme(
            colorScheme = MaterialTheme.colorScheme.copy(primary = customRed, onPrimary = Color.White)
        ) {
            Button(
                onClick = { navController.navigate("create_account") },
                modifier = Modifier.padding(top = 8.dp)
            ) {
                Text("Aceptar")
            }
        }
        Spacer(modifier = Modifier.height(16.dp)) // space at the bottom
    }
}