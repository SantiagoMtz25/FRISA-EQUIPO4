package com.example.loginpagetest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.loginpagetest.navigation.PageNavigation
import com.example.loginpagetest.ui.theme.LoginPageTestTheme
import com.example.loginpagetest.viewmodel.AppViewModel
import kotlinx.coroutines.delay

@Composable
fun RotatingLoadingView(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val rotatingView = remember {
        RotatingCircleLoadingView(context).apply {
            // Start the animation
            startAnimation(coroutineScope)
        }
    }

    DisposableEffect(rotatingView) {
        onDispose {
            rotatingView.stopAnimation()
        }
    }
    AndroidView({ rotatingView }, modifier = modifier)
}

class MainActivity : ComponentActivity() {

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
                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.fillMaxSize()
                        ) {
                            Text("Iniciando")
                            Spacer(modifier = Modifier.height(16.dp))
                            RotatingLoadingView(modifier = Modifier.size(75.dp))
                        }
                    } else {
                        PageNavigation(appViewModel)
                    }
                }
            }
        }
    }
}