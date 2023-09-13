package com.example.loginpagetest.screens.homepage

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.loginpagetest.navigation.CustomTopBar

@Composable
fun OrganizationsCatalogue(content: NavHostController) {
    Column {
        CustomTopBar(title = "Welocome (persone name here)", navController = content, screen = "login")
        val query = remember { mutableStateOf(TextFieldValue()) }
        BasicTextField(
            value = query.value,
            onValueChange = { query.value = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )

        LazyColumn {
            items(DataProvider.categories) { category ->
                CategoryItem(category = category)
            }
        }
    }
}

@Composable
fun CategoryItem(category: Category) {
    var expanded by rememberSaveable { mutableStateOf(false) }

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { expanded = !expanded }
            .padding(8.dp),
        elevation = 4.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .background(Color.White)
        ) {
            Text(text = category.name)
            if (expanded) {
                LazyColumn {
                    items(category.organizations) { organization ->
                        OrganizationItem(organization)
                    }
                }
            }
        }
    }
}

@Composable
fun OrganizationItem(organization: Organization) {
    Text(
        text = "  - ${organization.name}",
        modifier = Modifier.padding(8.dp)
    )
}