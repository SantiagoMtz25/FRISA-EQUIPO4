package com.example.loginpagetest.screens.aboutapp

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.DrawerValue
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.rememberDrawerState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.loginpagetest.R
import kotlinx.coroutines.launch
import androidx.compose.foundation.text.ClickableText
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.core.content.ContextCompat.startActivity
import com.example.loginpagetest.screens.test.DrawerContent

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun aboutApp(content: NavHostController) {
    var drawerState by remember { mutableStateOf(DrawerValue.Closed) }
    val coroutineScope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState(rememberDrawerState(drawerState))
    val myColor = colorResource(id = R.color.logoRed)
    val customgray = colorResource(id = R.color.darkgray)
    val uriHandler = LocalUriHandler.current
    val context = LocalContext.current
    val isAdmin: Boolean = content.currentBackStackEntry
        ?.arguments?.getBoolean("isAdmin") ?: false

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = { Text("About App", color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = {
                        coroutineScope.launch {
                            if (scaffoldState.drawerState.isOpen) {
                                scaffoldState.drawerState.close()
                            } else {
                                scaffoldState.drawerState.open()
                            }
                        }
                    }) {
                        Icon(imageVector = Icons.Default.Menu, contentDescription = "Menu", tint = Color.White)
                    }
                },
                backgroundColor = myColor
            )
        },
        drawerContent = {
            DrawerContent(content, isAdmin)
        }
    ) {
        // write code here
        LazyColumn(
            modifier = Modifier.padding(16.dp)
        ) {
            item {
                Text("OSC Catalogue", style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center))
            }
            item {
                Text("Version 1.0.0", style = TextStyle(fontSize = 18.sp, textAlign = TextAlign.Center, color = customgray))
            }
            item {
                Text(
                "Welcome to OSC Catalogue!\n\n" +
                    "This app is designed to connect users with OSCs in México and to promote greater civic participation.\n\n" +
                    "Explore a catalogue of OSCs and find opportunities to engage in various citizenship activities through different categories.\n\n"
                .trimIndent(),
                    style = TextStyle(fontSize = 18.sp, textAlign = TextAlign.Justify),
                    modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
                )
            }
            item {
                Text(
                    text = "For further information contact us through email: ",
                    style = TextStyle(fontSize = 18.sp, textAlign = TextAlign.Justify, color = customgray)
                )
            }
            item {
                val email = "osccatalogue@frisa.com.mx"
                val annotatedString = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            color = Color.Blue, // Or any other color that you prefer
                            textDecoration = TextDecoration.Underline
                        )
                    ) {
                        append(email)
                        addStringAnnotation(tag = "email", annotation = email, start = 0, end = email.length)
                    }
                }

                ClickableText(
                    text = annotatedString,
                    style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center),
                    onClick = { offset ->
                        annotatedString.getStringAnnotations(tag = "email", start = offset, end = offset).firstOrNull()?.let { annotation ->
                            val intent = Intent(Intent.ACTION_SENDTO).apply {
                                data = Uri.parse("mailto:${annotation.item}") // Only email apps should handle this
                                putExtra(Intent.EXTRA_EMAIL, arrayOf(annotation.item))
                                setPackage("com.google.android.gm") // set Gmail app to handle intent
                            }
                            if (intent.resolveActivity(context.packageManager) != null) {
                                startActivity(context, intent, null)
                            } else {
                                // Fallback to other email apps if Gmail is not installed
                                val fallbackIntent = Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:${annotation.item}"))
                                startActivity(context, fallbackIntent, null)
                            }
                        }
                    }
                )
            }
            item {
                Text(
                    text = "\nOr visit our webpage at: ",
                    style = TextStyle(fontSize = 18.sp, textAlign = TextAlign.Center, color = customgray)
                )
            }
            item {
                val annotatedString = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            color = Color.Blue, // or any other color that you prefer
                            textDecoration = TextDecoration.Underline
                        )
                    ) {
                        append("www.osccatalogue.com.mx")
                        addStringAnnotation(tag = "URL", annotation = "http://www.osccatalogue.com.mx", start = 0, end = "www.osccatalogue.com.mx".length)
                    }
                }

                ClickableText(
                    text = annotatedString,
                    style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center),
                    onClick = { offset ->
                        annotatedString.getStringAnnotations(tag = "URL", start = offset, end = offset).firstOrNull()?.let { annotation ->
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(annotation.item)).setPackage("com.android.chrome")
                            if (intent.resolveActivity(context.packageManager) != null) {
                                startActivity(context, intent, null)
                            } else {
                                // Fallback to other browser or let user choose browser if Chrome is not installed
                                val fallbackIntent = Intent(Intent.ACTION_VIEW, Uri.parse(annotation.item))
                                startActivity(context, fallbackIntent, null)
                            }
                        }
                    }
                )
            }
        }
    }
}

