package com.example.loginpagetest.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class CreateOSCViewModel () : ViewModel() {
    var name by mutableStateOf("")
    var adminName by mutableStateOf("")
    var rfc by mutableStateOf("")
    var description by mutableStateOf("")
    var phoneNumber by mutableStateOf("")
    var selectedState by mutableStateOf("")
    var selectedCity by mutableStateOf("")
    var email by mutableStateOf("")
    var webpage by mutableStateOf("")

    var category by mutableStateOf("")
    var isStateDropdownExpanded by mutableStateOf(false)
    var isCityDropdownExpanded by mutableStateOf(false)
    var showSnackbar by mutableStateOf(false)
    var showSuccessSnackbar by mutableStateOf(false)
}