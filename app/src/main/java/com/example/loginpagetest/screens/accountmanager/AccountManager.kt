package com.example.loginpagetest.screens.accountmanager

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.DrawerValue
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.rememberDrawerState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Snackbar
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.loginpagetest.R
import com.example.loginpagetest.screens.test.DrawerContent
import kotlinx.coroutines.launch
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.loginpagetest.screens.createaccount.CreateAccountTextField
import com.example.loginpagetest.service.OrgService
import com.example.loginpagetest.service.UserService
import com.example.loginpagetest.viewmodel.AppViewModel
import com.example.loginpagetest.viewmodel.OrgViewModel
import com.example.loginpagetest.viewmodel.UserViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun accountManager(navController: NavHostController, appViewModel: AppViewModel) {
    var drawerState by remember { mutableStateOf(DrawerValue.Closed) }
    val coroutineScope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState(rememberDrawerState(drawerState))
    val customRed = colorResource(id = R.color.logoRed)

    var areFieldsVisible by rememberSaveable { mutableStateOf(false) }

    val userViewModel =  UserViewModel(UserService.instance)
    val orgViewModel = OrgViewModel(OrgService.instance)

    LaunchedEffect(key1 = userViewModel) {
        userViewModel.updateAccountResult.collect { result ->
            if (result != null) {
                // maybe display message of updated
                Log.d("UPDATE", "${result.message}")
            }
        }
    }
    LaunchedEffect(key1 = orgViewModel) {
        orgViewModel.updateAccountResult.collect { result ->
            if (result != null) {
                // maybe display message of updated
                Log.d("UPDATE", "${result.message}")
            }
        }
    }

    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var selectedState by remember { mutableStateOf("") }
    var selectedCity by remember { mutableStateOf("") }

    var rfc by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var webpage by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("") }

    var wrongPassword by remember { mutableStateOf(false) }
    val scrollState = rememberScrollState()
    LaunchedEffect(wrongPassword) {
        if (wrongPassword) {
            scrollState.animateScrollTo(scrollState.maxValue)
            wrongPassword = false
        }
    }
    if (wrongPassword) {
        Snackbar(
            modifier = Modifier
                .padding(16.dp)
                .background(Color.Green),
            action = {
                TextButton(onClick = { wrongPassword = false }) {
                    Text(
                        "Quitar",
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        ) {
            Text("Las contraseñas no coinciden.", color = Color.White)
        }
    }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = { Text("Mi Cuenta", color = Color.White) },
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
                backgroundColor = customRed
            )
        },
        drawerContent = {
            DrawerContent(navController, appViewModel.isAdmin(), appViewModel)
        },
        content = {
            if (appViewModel.isAdmin()) {
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
                            Text("OSC Nombre: ${appViewModel.getName()} ${appViewModel.getLastName()}")
                            Text("Admin Nombre: ${appViewModel.getAdminName()}")
                        }
                    }

                    Button(
                        onClick = { areFieldsVisible = !areFieldsVisible },
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(bottom = 16.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = customRed,
                            contentColor = Color.White
                        )
                    ) {
                        Text("Actualizar Información")
                    }

                    if (areFieldsVisible) {
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            Column (
                                modifier = Modifier
                                    .verticalScroll(rememberScrollState())
                                    .padding(16.dp)
                            ) {
                                CreateAccountTextField(value = selectedState,
                                    onValueChange = { selectedState = it },
                                    label = "Cambiar Estado",
                                    icon = {
                                        Icon(
                                            imageVector = Icons.Default.LocationOn,
                                            contentDescription = "State Icon",
                                            tint = customRed
                                        )
                                    }
                                )
                                CreateAccountTextField(value = selectedCity,
                                    onValueChange = { selectedCity = it },
                                    label = "Cambiar Ciudad",
                                    icon = {
                                        Icon(
                                            imageVector = Icons.Default.Home,
                                            contentDescription = "City Icon",
                                            tint = customRed
                                        )
                                    }
                                )
                                CreateAccountTextField(value = phoneNumber,
                                    onValueChange = { phoneNumber = it },
                                    label = "Cambiar Teléfono",
                                    icon = {
                                        Icon(
                                            imageVector = Icons.Default.Phone,
                                            contentDescription = "Phone Icon",
                                            tint = customRed
                                        )
                                    }
                                )
                                CreateAccountTextField(value = description,
                                    onValueChange = { description = it },
                                    label = "Cambiar Descripción",
                                    icon = {
                                        Icon(
                                            imageVector = Icons.Default.Menu,
                                            contentDescription = "Lock Icon",
                                            tint = customRed
                                        )
                                    }
                                )
                                CreateAccountTextField(value = rfc,
                                    onValueChange = { rfc = it },
                                    label = "Cambiar RFC",
                                    icon = {
                                        Icon(
                                            imageVector = Icons.Default.AccountBox,
                                            contentDescription = "Lock Icon",
                                            tint = customRed
                                        )
                                    }
                                )
                                CreateAccountTextField(value = webpage,
                                    onValueChange = { webpage = it },
                                    label = "Cambiar Link Página Web",
                                    icon = {
                                        Icon(
                                            imageVector = Icons.Default.Send,
                                            contentDescription = "Send Icon",
                                            tint = customRed
                                        )
                                    }
                                )
                                CreateAccountTextField(value = category,
                                    onValueChange = { category = it },
                                    label = "Cambiar Categoría",
                                    icon = {
                                        Icon(
                                            imageVector = Icons.Default.Send,
                                            contentDescription = "Send Icon",
                                            tint = customRed
                                        )
                                    }
                                )
                                Column(
                                    modifier = Modifier.fillMaxSize(),
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Button(
                                        onClick = {
                                            orgViewModel.orgUpdateAccount(
                                                appViewModel.getToken(),
                                                selectedState,
                                                selectedCity,
                                                phoneNumber,
                                                description,
                                                rfc,
                                                webpage,
                                                category)
                                        },
                                        colors = ButtonDefaults.buttonColors(
                                            backgroundColor = customRed,
                                            contentColor = Color.White
                                        )
                                    ) {
                                        Text("Guardar Cambios")
                                    }
                                }
                            }
                        }
                    }
                }
            } else {
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
                            Text("Nombre: ${appViewModel.getName()}")
                            Text("Correo: ${appViewModel.getEmail()}")
                        }
                    }

                    Button(
                        onClick = { areFieldsVisible = !areFieldsVisible },
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(bottom = 16.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = customRed,
                            contentColor = Color.White
                        )
                    ) {
                        Text("Actualizar Información")
                    }

                    if (areFieldsVisible) {
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            Column (
                                modifier = Modifier
                                    .verticalScroll(rememberScrollState())
                                    .padding(16.dp)
                            ) {
                                CreateAccountTextField(value = selectedState,
                                    onValueChange = { selectedState = it },
                                    label = "Cambiar Estado",
                                    icon = {
                                        Icon(
                                            imageVector = Icons.Default.LocationOn,
                                            contentDescription = "State Icon",
                                            tint = customRed
                                        )
                                    }
                                )
                                CreateAccountTextField(value = selectedCity,
                                    onValueChange = { selectedCity = it },
                                    label = "Cambiar Ciudad",
                                    icon = {
                                        Icon(
                                            imageVector = Icons.Default.Home,
                                            contentDescription = "City Icon",
                                            tint = customRed
                                        )
                                    }
                                )
                                CreateAccountTextField(value = phoneNumber,
                                    onValueChange = { phoneNumber = it },
                                    label = "Cambiar Teléfono",
                                    icon = {
                                        Icon(
                                            imageVector = Icons.Default.Phone,
                                            contentDescription = "Phone Icon",
                                            tint = customRed
                                        )
                                    }
                                )
                                CreateAccountTextField(value = password,
                                    onValueChange = { password = it },
                                    label = "Cambiar Contraseña",
                                    icon = {
                                        Icon(
                                            imageVector = Icons.Default.Lock,
                                            contentDescription = "Lock Icon",
                                            tint = customRed
                                        )
                                    }
                                )
                                CreateAccountTextField(value = confirmPassword,
                                    onValueChange = { confirmPassword = it },
                                    label = "Confirmar Contraseña",
                                    icon = {
                                        Icon(
                                            imageVector = Icons.Default.Lock,
                                            contentDescription = "Lock Icon",
                                            tint = customRed
                                        )
                                    }
                                )
                                Column(
                                    modifier = Modifier.fillMaxSize(),
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Button(
                                        onClick = {
                                            if (password == confirmPassword) {
                                                userViewModel.userUpdateAccount(
                                                    appViewModel.getToken(),
                                                    selectedState,
                                                    selectedCity,
                                                    phoneNumber,
                                                    password
                                                )
                                            } else {
                                                wrongPassword = true
                                            }
                                        },
                                        colors = ButtonDefaults.buttonColors(
                                            backgroundColor = customRed,
                                            contentColor = Color.White
                                        )
                                    ) {
                                        Text("Guardar Cambios")
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    )
}