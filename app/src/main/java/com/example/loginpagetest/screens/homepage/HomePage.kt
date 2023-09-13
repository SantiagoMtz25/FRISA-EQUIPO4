package com.example.loginpagetest.screens.homepage
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.compose.material3.Text
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember


@Composable
fun OrganizationsCatalogue(content: NavHostController) {
    val query = remember { mutableStateOf(TextFieldValue()) }
    BasicTextField(
        value = query.value,
        onValueChange = { query.value = it },
        modifier = Modifier.fillMaxSize()
    )

    LazyColumn {
        items(DataProvider.categories) { category ->
            CategoryItem(category = category)
        }
    }
}

@Composable
fun CategoryItem(category: Category) {
    var expanded by remember { mutableStateOf(false) }
    Text(text = category.name, modifier = Modifier.clickable { expanded = !expanded })
    DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
        category.organizations.forEach { organization ->
            DropdownMenuItem(onClick = { expanded = false }) {
                Text(text = organization.name)
            }
        }
    }
}
