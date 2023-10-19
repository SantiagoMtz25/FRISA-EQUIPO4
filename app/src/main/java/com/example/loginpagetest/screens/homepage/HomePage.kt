package com.example.loginpagetest.screens.homepage

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.navigation.NavHostController
import com.example.loginpagetest.R
import androidx.compose.material3.Surface
import androidx.compose.ui.draw.shadow
import com.example.loginpagetest.model.UserFavToDeleteResponse
import com.example.loginpagetest.model.UserFavouritesResponse
import com.example.loginpagetest.model.getall.GetAllOrganizationsResponse
import com.example.loginpagetest.service.UserService
import com.example.loginpagetest.viewmodel.AppViewModel
import com.example.loginpagetest.viewmodel.UserViewModel

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun OrganizationsCatalogue(appViewModel: AppViewModel, navController: NavHostController, inviteUser: Boolean) {
    var isPopupVisible by remember { mutableStateOf(false) }
    val customRed = colorResource(id = R.color.logoRed)
    val customGray = colorResource(id = R.color.logoGray)
    val customPink = colorResource(id = R.color.lightred_pink)
    var searchQuery by remember { mutableStateOf("") }

    fun getCategoryOfOrganization(organization: String, organizationsMap: Map<String, List<String>>): String? {
        for ((category, organizations) in organizationsMap) {
            if (organization in organizations) {
                return category
            }
        }
        return null
    }
    var selectedCategory by remember { mutableStateOf<String?>(null) }
    val keyboardController = LocalSoftwareKeyboardController.current
    val organizationsMap = mapOf(
        "Salud" to listOf("Org salud 1", "Org salud 2", "Org salud 3"),
        "Educación" to listOf("Org educación 1", "Org educación 2", "Org educación 3"),
        "Medio Ambiente" to listOf("Org medio ambiente 1", "Org medio ambiente 2", "Org medio ambiente 3"),
        "Derechos Humanos" to listOf("Org derechos humanos 1", "Org derechos humanos 2", "Org derechos humanos 3"),
        "Asociaciones Religiosas" to listOf("Org religiosa 1", "Org religiosa 2", "Org religiosa 3"),
        "Transporte Público" to listOf("Org de transporte 1", "Org de transporte 2", "Org de transporte 3"),
        "Cultura" to listOf("Org cultural 1", "Org cultural 2", "Org cultural 3"),
        "Servicios Asistenciales" to listOf("Org de servicios 1", "Org de servicios 2", "Org de servicios 3")
    )
    val filteredAndSortedCategories: Map<String, List<String>> = organizationsMap
        .mapValues { entry ->
            entry.value.filter {
                it.contains(searchQuery, ignoreCase = true) || entry.key.contains(searchQuery, ignoreCase = true)
            }
        }
        .filter { it.value.isNotEmpty() }
    var starFilter by remember { mutableIntStateOf(0) }

    val userViewModel = UserViewModel(UserService.instance)

    var getAllOrganizationsResult = remember { mutableStateOf(GetAllOrganizationsResponse()) }

    userViewModel.getAllOsc(appViewModel.getToken())

    LaunchedEffect(key1 = userViewModel.getAllOrganizationsResult) {
        userViewModel.getAllOrganizationsResult.collect { result ->
            if (result != null) {
                getAllOrganizationsResult.value = result

                Log.d("CATALOGO", "${getAllOrganizationsResult.value}")
            }
        }
    }

    val addFavouriteResult = remember { mutableStateOf(UserFavouritesResponse()) }

    LaunchedEffect(key1 = userViewModel.addFavouriteResult) {
        userViewModel.addFavouriteResult.collect { result ->
            if (result != null) {
                addFavouriteResult.value = result
                // maybe print something to let the user know the action was executed
                Log.d("FAVOURITE", "val: ${addFavouriteResult.value}")
            }
        }
    }

    val removeFavouriteResult = remember { mutableStateOf(UserFavToDeleteResponse()) }

    LaunchedEffect(key1 = userViewModel.removeFavouriteResult) {
        userViewModel.removeFavouriteResult.collect { result ->
            if (result != null) {
                removeFavouriteResult.value = result
                // maybe print a snack-bar or some shit
                Log.d("FAVOURITE", "val: ${removeFavouriteResult.value}")
            }
        }
    }

    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { newValue ->
                    searchQuery = newValue
                    selectedCategory = getCategoryOfOrganization(searchQuery, organizationsMap)
                },
                singleLine = true,
                label = { Text("Buscar OSC") },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Search
                ),
                keyboardActions = KeyboardActions(
                    onSearch = {
                        keyboardController?.hide()
                    }
                ),
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp),
                colors = TextFieldDefaults.textFieldColors(
                    cursorColor = customRed,
                    focusedIndicatorColor = customPink,
                    unfocusedIndicatorColor = customGray,
                    focusedLabelColor = Color.Black
                ),
            )
            // Filter IconButton
            IconButton(onClick = {
                isPopupVisible = true
            }) {
                Icon(
                    imageVector = Icons.Default.List,
                    contentDescription = "Filter",
                    tint = Color.Black,
                    modifier = Modifier.size(48.dp)
                )
            }
        }
        // Add icon functionality here
        if(isPopupVisible) {
            Popup(onDismissRequest = { isPopupVisible = false }) {
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .shadow(5.dp)
                        .padding(16.dp)
                        .background(Color.White)
                ) {
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                            .background(color = Color.White, shape = RoundedCornerShape(8.dp))
                    ) {
                        Text(text = "Filtros", style = MaterialTheme.typography.bodyMedium)
                        Divider()
                        // Name Filter
                        /*Text("Nombre:")
                        OutlinedTextField(
                            value = "",
                            onValueChange = { /*Handle Name Filter Change*/ },
                            label = { Text("Nombre") },
                            singleLine = true,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(4.dp),
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedBorderColor = Color.Gray,
                                unfocusedBorderColor = Color.LightGray
                            ),
                            keyboardOptions = KeyboardOptions.Default.copy(
                                imeAction = ImeAction.Done
                            ),
                            keyboardActions = KeyboardActions(
                                onDone = {
                                    // Handle on Done Clicked
                                }
                            )
                        )
                        // Location Filter
                        Text("Ubicación:")
                        OutlinedTextField(
                            value = "",
                            onValueChange = { /*Handle Name Filter Change*/ },
                            label = { Text("Ubicación") },
                            singleLine = true,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(4.dp),
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedBorderColor = Color.Gray,
                                unfocusedBorderColor = Color.LightGray
                            ),
                            keyboardOptions = KeyboardOptions.Default.copy(
                                imeAction = ImeAction.Done
                            ),
                            keyboardActions = KeyboardActions(
                                onDone = {
                                    // Handle on Done Clicked
                                }
                            )
                        )*/
                        // Category Filter
                        Text("Categoría:")
                        LazyColumn {
                            items(organizationsMap.keys.toList()) { category ->
                                val isSelected = category == selectedCategory
                                Text(
                                    text = category,
                                    color = if (isSelected) Color.White else Color.Black,
                                    modifier = Modifier
                                        .clickable { selectedCategory = category }
                                        .background(if (isSelected) customPink else Color.Transparent)
                                        .padding(2.dp)
                                        .fillMaxWidth()
                                        .border(
                                            width = 1.dp,
                                            color = Color.Gray,
                                            shape = RoundedCornerShape(4.dp)
                                        )
                                )
                            }
                        }
                        Divider()
                        // Star Filter
                        Text("Estrellas:")
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            (1..5).forEach { index ->
                                Icon(
                                    imageVector = Icons.Default.Star,
                                    contentDescription = "Star $index",
                                    tint = if (starFilter >= index) customRed else customGray,
                                    modifier = Modifier.clickable { starFilter = index }
                                )
                            }
                        }
                        // Apply button to apply filters
                        MaterialTheme (
                            colorScheme = MaterialTheme.colorScheme.copy(primary = customRed, onPrimary = Color.White)
                        ) {
                            Button(onClick = { isPopupVisible = false }) {
                                Text("Buscar")
                            }
                        }
                    }
                }
            }
        }

        LazyRow(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, top = 8.dp)
        ) {
            items(listOf("Salud", "Educación", "Medio Ambiente", "Derechos humanos",
                "Asociaciones Religiosas", "Transporte Público", "Cultura", "Servicios Asistenciales"
            )) { tag ->
                Chip(tag = tag, onClick = {
                    searchQuery = if (searchQuery == tag) "" else tag
                }, modifier = Modifier.padding(end = 8.dp)) // Add padding to each tag
            }
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            items(filteredAndSortedCategories.keys.toList()) { category ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .background(Color.Transparent)
                        .clickable {
                            if (selectedCategory == category) {
                                selectedCategory = null
                            } else {
                                selectedCategory = category
                            }
                        }
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = category)
                        Spacer(Modifier.weight(1f))
                        Icon(
                            imageVector = if (selectedCategory == category) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                            contentDescription = "Keyboard Arrow Icon",
                            tint = customGray
                        )
                    }
                }
                if (appViewModel.isAdmin() || inviteUser) {
                    if (selectedCategory == category) {
                        filteredAndSortedCategories[category]?.forEach { organization ->
                        Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 20.dp, end = 10.dp, top = 8.dp, bottom = 8.dp)
                                    .background(Color.Transparent)
                                    .clickable {
                                        navController.navigate("OSCpage/${inviteUser}")
                                    }
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(text = organization)
                                }
                            }
                        }
                    }
                } else {
                    if (selectedCategory == category) {
                        organizationsMap[category]?.forEach { organization ->
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 20.dp, end = 10.dp, top = 8.dp, bottom = 8.dp)
                                    .background(Color.Transparent)
                                    .clickable {
                                        navController.navigate("OSCpage/${inviteUser}")
                                    }
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(text = organization)
                                    var isClicked by remember { mutableStateOf(false) }
                                    Icon(
                                        tint = if (isClicked) Color.Yellow else Color.Gray,
                                        imageVector = Icons.Default.Star,
                                        contentDescription = null,
                                        modifier = Modifier.clickable {
                                            isClicked = !isClicked
                                            Log.d("CLICKED", "$isClicked")
                                            // according to logic of start being clicked or unclicked
                                            if (isClicked) {
                                                userViewModel.addFavourite(appViewModel.getToken(), "652f6928a67d0110c1b8516e")
                                            } else {
                                                userViewModel.removeFavourite(appViewModel.getToken(), "652f6928a67d0110c1b8516e")
                                            }
                                        }
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
fun Chip(tag: String, onClick: () -> Unit, modifier: Modifier = Modifier) {
    val customPink = colorResource(id = R.color.lightred_pink)
    Box(
        modifier = modifier
            .background(customPink, shape = RoundedCornerShape(16.dp))
            .clickable(onClick = onClick)
            .padding(horizontal = 12.dp, vertical = 6.dp)
    ) {
        Text(text = tag)
    }
}

