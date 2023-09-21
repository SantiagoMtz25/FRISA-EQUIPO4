package com.example.loginpagetest.screens.homepage

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.loginpagetest.navigation.CustomTopBar
import com.example.loginpagetest.navigation.CustomTopBar2

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun OrganizationsCatalogue(content: NavHostController) {
    var searchQuery by remember { mutableStateOf("") }
    val selectedCategories = remember { mutableStateListOf<String>() }
    val keyboardController = LocalSoftwareKeyboardController.current
    val organizationsMap = mapOf(
        "Salud" to listOf("Org salud 1", "Org salud 2", "Org salud 3"),
        "Educación" to listOf("Org educación 1", "Org educación 2", "Org educación 3"),
        "Medio Ambiente" to listOf("Org medio ambiente 1", "Org medio ambiente 2", "Org medio ambiente 3"),
        "Derechos humanos" to listOf("Org derechos humanos 1", "Org derechos humanos 2", "Org derechos humanos 3")
    )
    val filteredAndSortedCategories = organizationsMap.keys.filter {
        it.contains(searchQuery, ignoreCase = true)
    }.sortedBy { it != searchQuery }

    Column {
        CustomTopBar(title = "Welcome (person name here)", navController = content, screen = "login")

        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            label = { Text("Search") },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    keyboardController?.hide()
                }
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            items(filteredAndSortedCategories) { category ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .background(Color.LightGray)
                        .clickable {
                            if (selectedCategories.contains(category)) {
                                selectedCategories.remove(category)
                            } else {
                                selectedCategories.add(category)
                            }
                        }
                ) {
                    Text(
                        text = category,
                        modifier = Modifier.padding(16.dp)
                    )
                }

                // Conditionally display the list of organizations
                if (selectedCategories.contains(category)) {
                    organizationsMap[category]?.forEach { organization ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 32.dp, end = 16.dp, top = 8.dp, bottom = 8.dp)
                                .background(Color.LightGray)
                                .clickable {
                                    content.navigate("OSCpage")
                                }
                        ) {
                            Text(
                                text = organization,
                                modifier = Modifier.padding(16.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}