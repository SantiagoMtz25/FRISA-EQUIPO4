package com.example.loginpagetest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.loginpagetest.navigation.PageNagivation
import com.example.loginpagetest.screens.loginpage.mainLoginPage
import com.example.loginpagetest.screens.loginpage.myLoginApp
import com.example.loginpagetest.ui.theme.LoginPageTestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoginPageTestTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PageNagivation()
                }
            }
        }
    }
}