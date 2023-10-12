package com.example.loginpagetest

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.loginpagetest.navigation.PageNavigation
import com.example.loginpagetest.ui.theme.LoginPageTestTheme
import com.example.loginpagetest.viewmodel.AppViewModel
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {

    // Add the token checks to avoid login screen
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val appViewModel: AppViewModel = viewModel()

            val configLoaded =  remember {
                mutableStateOf(false)
            }

            LaunchedEffect(appViewModel.isUserLoggedIn()) {
                delay(2000)
                appViewModel.isInitialized.collect { result ->
                    if (result != null) {
                        configLoaded.value = result
                    }
                }
            }

            LoginPageTestTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // verified screens load correctly, THEY DO
                    // configLoaded.value = true
                    if (!configLoaded.value) {
                        Text("loading")

                    } else {
                        PageNavigation(appViewModel)
                    }
                }
            }
        }
    }
}