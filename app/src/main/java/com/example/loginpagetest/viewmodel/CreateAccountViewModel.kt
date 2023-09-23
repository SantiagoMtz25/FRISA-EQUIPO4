package com.example.loginpagetest.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

class CreateAccountViewModel(private val stateHandle: SavedStateHandle) : ViewModel() {
    var name by mutableStateOf("")
    var lastName by mutableStateOf("")
    var email by mutableStateOf("")
    var password by mutableStateOf("")
    var confirmPassword by mutableStateOf("")
    var phoneNumber by mutableStateOf("")
    var selectedState by mutableStateOf("")
    var selectedCity by mutableStateOf("")
    var isStateDropdownExpanded by mutableStateOf(false)
    var isCityDropdownExpanded by mutableStateOf(false)
    var showSnackbar by mutableStateOf(false)
    var showSuccessSnackbar by mutableStateOf(false)
}



