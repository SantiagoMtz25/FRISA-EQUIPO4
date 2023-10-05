package com.example.loginpagetest.screens.accountmanager

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.loginpagetest.R
import com.example.loginpagetest.screens.test.DrawerContent
import com.example.loginpagetest.viewmodel.CreateAccountViewModel
import kotlinx.coroutines.launch
import androidx.compose.runtime.saveable.rememberSaveable
import com.example.loginpagetest.screens.createaccount.CreateAccountTextField
import com.example.loginpagetest.viewmodel.CreateOSCViewModel
import com.example.loginpagetest.viewmodel.OrgViewModel
import com.example.loginpagetest.viewmodel.UserViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun accountManager(navController: NavHostController) {
    var drawerState by remember { mutableStateOf(DrawerValue.Closed) }
    val coroutineScope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState(rememberDrawerState(drawerState))
    val customRed = colorResource(id = R.color.logoRed)

    val isAdmin: Boolean = navController.currentBackStackEntry
        ?.arguments?.getBoolean("isAdmin") ?: false

    val viewModel: CreateAccountViewModel = viewModel()
    val oscViewModel : CreateOSCViewModel = viewModel()
    var areFieldsVisible by rememberSaveable { mutableStateOf(false) }

    val userVM: UserViewModel = viewModel()
    val orgVM: OrgViewModel = viewModel()

    LaunchedEffect(key1 = userVM.updateAccountResult) {
        userVM.updateAccountResult.collect { result ->
            if (result != null) {
                // message of account updated, do not allow user to update every  single time
            }
        }
    }
    LaunchedEffect(key1 = orgVM.updateAccountResult) {
        orgVM.updateAccountResult.collect { result ->
            if (result != null) {
                // same as the previous one
            }
        }
    }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = { Text("Your Account", color = Color.White) },
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
            DrawerContent(navController, isAdmin)
        },
        content = {
            if (isAdmin) {
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
                            Text("OSC Name: OSC John Doe")
                            Text("Admin Name: John Doe")
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
                        Text("Update Information")
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
                                CreateAccountTextField(value = oscViewModel.selectedState,
                                    onValueChange = { oscViewModel.selectedState = it },
                                    label = "Change State",
                                    icon = {
                                        Icon(
                                            imageVector = Icons.Default.LocationOn,
                                            contentDescription = "State Icon",
                                            tint = customRed
                                        )
                                    }
                                )
                                CreateAccountTextField(value = oscViewModel.selectedCity,
                                    onValueChange = { oscViewModel.selectedCity = it },
                                    label = "Change City",
                                    icon = {
                                        Icon(
                                            imageVector = Icons.Default.Home,
                                            contentDescription = "City Icon",
                                            tint = customRed
                                        )
                                    }
                                )
                                CreateAccountTextField(value = oscViewModel.phoneNumber,
                                    onValueChange = { oscViewModel.phoneNumber = it },
                                    label = "Change Number",
                                    icon = {
                                        Icon(
                                            imageVector = Icons.Default.Phone,
                                            contentDescription = "Phone Icon",
                                            tint = customRed
                                        )
                                    }
                                )
                                CreateAccountTextField(value = oscViewModel.description,
                                    onValueChange = { oscViewModel.description = it },
                                    label = "Change Description",
                                    icon = {
                                        Icon(
                                            imageVector = Icons.Default.Menu,
                                            contentDescription = "Lock Icon",
                                            tint = customRed
                                        )
                                    }
                                )
                                CreateAccountTextField(value = oscViewModel.rfc,
                                    onValueChange = { oscViewModel.rfc = it },
                                    label = "RFC Change",
                                    icon = {
                                        Icon(
                                            imageVector = Icons.Default.AccountBox,
                                            contentDescription = "Lock Icon",
                                            tint = customRed
                                        )
                                    }
                                )
                                CreateAccountTextField(value = oscViewModel.webpage,
                                    onValueChange = { oscViewModel.webpage = it },
                                    label = "Webpage Change",
                                    icon = {
                                        Icon(
                                            imageVector = Icons.Default.Send,
                                            contentDescription = "Send Icon",
                                            tint = customRed
                                        )
                                    }
                                )
                                CreateAccountTextField(value = oscViewModel.category,
                                    onValueChange = { oscViewModel.category = it },
                                    label = "Category Change",
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
                                            if (viewModel.password == viewModel.confirmPassword) {

                                                orgVM.orgUpdateAccount(
                                                    "",
                                                    oscViewModel.selectedState,
                                                    oscViewModel.selectedCity,
                                                    oscViewModel.phoneNumber,
                                                    oscViewModel.description,
                                                    oscViewModel.rfc,
                                                    oscViewModel.webpage,
                                                    oscViewModel.category
                                                )

                                                // Handle save changes click event
                                            } else {
                                                // Passwords do not match, show an error message
                                            }
                                        },
                                        colors = ButtonDefaults.buttonColors(
                                            backgroundColor = customRed,
                                            contentColor = Color.White
                                        )
                                    ) {
                                        Text("Save Changes")
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
                            Text("Name: John Doe")
                            Text("Email: john.doe@example.com")
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
                        Text("Update Information")
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
                                CreateAccountTextField(value = viewModel.selectedState,
                                    onValueChange = { viewModel.selectedState = it },
                                    label = "Change State",
                                    icon = {
                                        Icon(
                                            imageVector = Icons.Default.LocationOn,
                                            contentDescription = "State Icon",
                                            tint = customRed
                                        )
                                    }
                                )
                                CreateAccountTextField(value = viewModel.selectedCity,
                                    onValueChange = { viewModel.selectedCity = it },
                                    label = "Change City",
                                    icon = {
                                        Icon(
                                            imageVector = Icons.Default.Home,
                                            contentDescription = "City Icon",
                                            tint = customRed
                                        )
                                    }
                                )
                                CreateAccountTextField(value = viewModel.phoneNumber,
                                    onValueChange = { viewModel.phoneNumber = it },
                                    label = "Change Number",
                                    icon = {
                                        Icon(
                                            imageVector = Icons.Default.Phone,
                                            contentDescription = "Phone Icon",
                                            tint = customRed
                                        )
                                    }
                                )
                                CreateAccountTextField(value = viewModel.password,
                                    onValueChange = { viewModel.password = it },
                                    label = "Change Password",
                                    icon = {
                                        Icon(
                                            imageVector = Icons.Default.Lock,
                                            contentDescription = "Lock Icon",
                                            tint = customRed
                                        )
                                    }
                                )
                                CreateAccountTextField(value = viewModel.confirmPassword,
                                    onValueChange = { viewModel.confirmPassword = it },
                                    label = "Confirm Change",
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
                                            if (viewModel.password == viewModel.confirmPassword) {
                                                // Handle save changes click event
                                                userVM.userUpdateAccount(
                                                    "",
                                                    viewModel.selectedState,
                                                    viewModel.selectedCity,
                                                    viewModel.phoneNumber,
                                                    viewModel.password,
                                                    viewModel.confirmPassword
                                                )

                                            } else {
                                                // Passwords do not match, show an error message
                                            }
                                        },
                                        colors = ButtonDefaults.buttonColors(
                                            backgroundColor = customRed,
                                            contentColor = Color.White
                                        )
                                    ) {
                                        Text("Save Changes")
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