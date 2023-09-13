package com.example.loginpagetest.screens.createaccount

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.loginpagetest.R
import com.example.loginpagetest.navigation.CustomTopBar

@Composable
fun CreateAccount(navController: NavHostController) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        val scrollState = rememberScrollState()
        Column {
            CustomTopBar(title = "Organizations", navController = navController, screen = "login")
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .verticalScroll(scrollState),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                var name by rememberSaveable { mutableStateOf("") }
                var lastName by rememberSaveable { mutableStateOf("") }
                var email by rememberSaveable { mutableStateOf("") }
                var password by rememberSaveable { mutableStateOf("") }
                var confirmPassword by rememberSaveable { mutableStateOf("") }
                var phoneNumber by rememberSaveable { mutableStateOf("") }
                var state by rememberSaveable { mutableStateOf("") }
                var city by rememberSaveable { mutableStateOf("") }
                val image: Painter = painterResource(id = R.drawable.frisa)
                Image(
                    painter = image,
                    contentDescription = null,
                    modifier = Modifier
                        .size(100.dp, 100.dp)
                        .align(Alignment.CenterHorizontally)
                )

                CreateAccountTextField(value = name, onValueChange = { name = it }, label = "Name")
                CreateAccountTextField(value = lastName, onValueChange = { lastName = it }, label = "Last Name")
                CreateAccountTextField(value = email, onValueChange = { email = it }, label = "Email", keyboardType = KeyboardType.Email)
                CreateAccountTextField(value = password, onValueChange = { password = it }, label = "Password", keyboardType = KeyboardType.Password)
                CreateAccountTextField(value = confirmPassword, onValueChange = { confirmPassword = it }, label = "Confirm Password", keyboardType = KeyboardType.Password)
                CreateAccountTextField(value = phoneNumber, onValueChange = { phoneNumber = it }, label = "Phone Number", keyboardType = KeyboardType.Phone)
                CreateAccountTextField(value = state, onValueChange = { state = it }, label = "State")
                CreateAccountTextField(value = city, onValueChange = { city = it }, label = "City/Municipality")
                Button(onClick = {
                    // println("Create Account button clicked")
                    navController.navigate("login")
                }) {
                    Text("Create Account")
                }
            }
        }
    }
}

@Composable
fun CreateAccountTextField(value: String, onValueChange: (String) -> Unit, label: String, keyboardType: KeyboardType = KeyboardType.Text) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = ImeAction.Done
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp, bottom = 8.dp)
    )
}
