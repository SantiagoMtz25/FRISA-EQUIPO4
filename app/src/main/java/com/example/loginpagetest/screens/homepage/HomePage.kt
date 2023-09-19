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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.loginpagetest.navigation.CustomTopBar

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun OrganizationsCatalogue(content: NavHostController) {
    var searchQuery by rememberSaveable { mutableStateOf("") }
    var selectedCategory by rememberSaveable { mutableStateOf<String?>(null) }
    val keyboardController = LocalSoftwareKeyboardController.current

    // Sample organization data
    val organizationsMap = mapOf(
        "Salud" to listOf("Org salud 1", "Org salud 2", "Org salud 3"),
        "Educación" to listOf("Org educación 1", "Org educación 2", "Org educación 3"),
        "Medio Ambiente" to listOf("Org medio ambiente 1", "Org medio ambiente 2", "Org medio ambiente 3"),
        "Derechos humanos" to listOf("Org derechos humanos 1", "Org derechos humanos 2", "Org derechos humanos 3")
    )

    // Filter and sort categories based on the search query
    val filteredAndSortedCategories = organizationsMap.keys.filter {
        it.contains(searchQuery, ignoreCase = true)
    }.sortedBy { it != searchQuery }

    Column {
        CustomTopBar(title = "Welcome (person name here)", navController = content, screen = "login")

        // Search bar
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            label = { Text("Search") },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    // Hide the keyboard when search button is clicked
                    keyboardController?.hide()
                }
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )

        // Categories
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
                        .clickable {
                            selectedCategory = category
                        }
                        .background(Color.Red)
                ) {
                    Text(
                        text = category,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
        // Display list of organizations in the selected category
        selectedCategory?.let { selected ->
            organizationsMap[selected]?.let { organizations ->
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(organizations) { organization ->
                        Text(
                            organization,
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                }
            }
        }
    }
}
