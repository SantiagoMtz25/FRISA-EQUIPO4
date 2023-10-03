package com.example.loginpagetest.screens.loginpage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.loginpagetest.R
import com.example.loginpagetest.model.UserLoginResponse
import com.example.loginpagetest.ui.theme.LoginPageTestTheme

@Composable
fun myLoginApp(navController: NavHostController) {
    LoginPageTestTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            mainLoginPage(navController)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun mainLoginPage(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Color definitions
        val customRed = colorResource(id = R.color.logoRed)
        val customLighterRed = colorResource(id = R.color.almostlogored)
        val customGray = colorResource(id = R.color.logoGray)
        val customPink = colorResource(id = R.color.lightred_pink)

        val scrollState = rememberScrollState()

        // Variables which will save user entered values
        var email by rememberSaveable { mutableStateOf("") }
        var password by rememberSaveable { mutableStateOf("") }
        var passwordVisibility by rememberSaveable { mutableStateOf(true) }

        // To get the result from login and redirect or not to screen
        var loginResult by remember {
            mutableStateOf(UserLoginResponse())
        }
        var successfulLogin by rememberSaveable { mutableStateOf(true) }

        var inviteUser by rememberSaveable { mutableStateOf(false) }

        // FRISA Logo
        val image: Painter = painterResource(id = R.drawable.frisa)
        Image(
            painter = image,
            contentDescription = null,
            modifier = Modifier
                .size(200.dp, 200.dp)
                .align(Alignment.CenterHorizontally)
        )
        // Welcome text
        Text(
            text = "Bienvenid@",
            style = TextStyle(
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        )
        Spacer(modifier = Modifier.height(28.dp))
        // Email input
        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email),
            colors = TextFieldDefaults.textFieldColors(
                cursorColor = customRed,
                focusedIndicatorColor = customPink,
                unfocusedIndicatorColor = customGray,
                focusedLabelColor = customLighterRed
            ),
            leadingIcon = {
                Icon(
                    Icons.Default.Email,
                    contentDescription = "Email Icon",
                    tint = customLighterRed
                )
            },
            // modifier = Modifier.background(customGray) // Change this to your desired background color
        )
        Spacer(modifier = Modifier.height(16.dp))
        // Password input
        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            colors = TextFieldDefaults.textFieldColors(
                cursorColor = customRed,
                focusedIndicatorColor = customPink,
                unfocusedIndicatorColor = customGray,
                focusedLabelColor = customLighterRed
            ),
            leadingIcon = {
                Icon(
                    Icons.Default.Lock,
                    contentDescription = "Lock Icon",
                    tint = customLighterRed
                )
            }
            // modifier = Modifier.background(customGray) // Change this to your desired background color
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            MaterialTheme(
                colorScheme = MaterialTheme.colorScheme.copy(primary = customRed, onPrimary = Color.White)
            ) {
                Button(
                    onClick = {
                        // while api is not yet used, for testing
                        successfulLogin = true
                        loginResult.isAdmin = true
                        navController.navigate("testScreen/${loginResult.isAdmin}")
                        /*if (!loginResult.token.isNullOrEmpty() && loginResult.isAdmin) {
                            // Admin user login, navigating to admin screen
                            successfulLogin = true
                            navController.navigate("testScreen/${loginResult.isAdmin}")

                        } else if (!loginResult.token.isNullOrEmpty()) {
                            // Regular user login, navigating to regular user screen
                            successfulLogin = true
                            navController.navigate("testScreen/${loginResult.isAdmin}")

                        } else {
                            // Login failed, showing a snack bar
                            successfulLogin = false
                        }*/
                    },
                    modifier = Modifier.width(100.dp)
                ) {
                    Text("Login")
                }
            }
            Spacer(modifier = Modifier.width(14.dp))
            MaterialTheme(
                colorScheme = MaterialTheme.colorScheme.copy(primary = customRed, onPrimary = Color.White)
            ) {
                Button(
                    onClick = {
                        navController.navigate("create_account")
                    },
                    modifier = Modifier.width(165.dp)
                ) {
                    Text("Create Account")
                }
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        MaterialTheme(
            colorScheme = MaterialTheme.colorScheme.copy(primary = customRed, onPrimary = Color.White)
        ) {
            Button(
                onClick = {
                    inviteUser = true
                    navController.navigate("inviteUser/${inviteUser}")
                },
                modifier = Modifier.width(145.dp)
            ) {
                Text("Invite User")
            }
        }

        // Wrong login snack-bar message for user
        LaunchedEffect(!successfulLogin) {
            if (!successfulLogin) {
                scrollState.animateScrollTo(scrollState.maxValue)
            }
        }
        if (!successfulLogin) {
            Snackbar(
                modifier = Modifier
                    .padding(16.dp)
                    .background(Color.Green),
                action = {
                    TextButton(onClick = { successfulLogin = true }) {
                        androidx.compose.material.Text(
                            "Dismiss",
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            ) {
                androidx.compose.material.Text("Email or Password are incorrect", color = Color.White)
            }
        }
    }
}