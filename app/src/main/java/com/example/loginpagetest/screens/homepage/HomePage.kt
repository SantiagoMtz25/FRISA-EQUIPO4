package com.example.loginpagetest.screens.homepage

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue

@Composable
fun OrganizationsCatalogue() {
    val query = remember { mutableStateOf(TextFieldValue()) }

    // Search Bar
    BasicTextField(
        value = query.value,
        onValueChange = { query.value = it },
        modifier = Modifier.fillMaxSize()
    )

    // Catalogue List
    LazyColumn {
        items(DataProvider.categories) { category ->
            if (category.name.contains(query.value.text, true) ||
                category.organizations.any { it.name.contains(query.value.text, true) }) {
                CategoryItem(category = category)
            }
        }
    }
}

@Composable
fun CategoryItem(category: Category) {
    Column {
        Text(text = category.name)
        for (org in category.organizations) {
            Text(text = " - ${org.name}")
        }
    }
}
