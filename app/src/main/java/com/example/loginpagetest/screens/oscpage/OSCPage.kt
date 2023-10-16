package com.example.loginpagetest.screens.oscpage

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.loginpagetest.R
import com.example.loginpagetest.navigation.CustomTopBar2
import com.example.loginpagetest.viewmodel.AppViewModel

private const val PREFS_NAME = "StarRankingPrefs"
private const val LAST_RANKING_KEY = "LastRankingTime"

@Composable
fun OSCPage(content: NavHostController, appViewModel: AppViewModel) {
    val customRed = colorResource(id = R.color.logoRed)
    val customGray = colorResource(id = R.color.logoGray)
    var starFilter by remember { mutableIntStateOf(0) }
    val inviteUser: Boolean = content.currentBackStackEntry?.arguments?.getBoolean("inviteUser") ?: false
    val isAdmin: Boolean = content.currentBackStackEntry
        ?.arguments?.getBoolean("isAdmin") ?: false
    val organization: String = content.currentBackStackEntry
        ?.arguments?.getString("organization") ?: ""


    /*val osc = OrgViewModel(OrgService.instance)

    LaunchedEffect(key1 = osc.orgAddGradeResult) {
        osc.orgAddGradeResult.collect { result ->
            if (result != null) {
                // maybe output grade has been sent, idk
            }
        }
    }*/

    val context = LocalContext.current
    val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    val lastRankingTime = prefs.getLong(LAST_RANKING_KEY, 0L)
    val currentTime = System.currentTimeMillis()
    val isAllowedToRank = (currentTime - lastRankingTime) > 24 * 60 * 60 * 1000

    var selectedStar by rememberSaveable { mutableIntStateOf(0) }

    /*LaunchedEffect(selectedStar) {
        //osc.addGrade(organization, selectedStar.toFloat())
        val editor = prefs.edit()
        editor.putLong(LAST_RANKING_KEY, System.currentTimeMillis())
        editor.apply()
    }*/

    var savedStarRank by rememberSaveable { mutableIntStateOf(0) }

    Column {
        CustomTopBar2(title = "Página OSC", navController = content)

        Card(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                // Top section with photo and texts
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Replace with your actual logo and photo
                    Image(
                        painter = painterResource(id = R.drawable.ic_launcher_foreground),
                        contentDescription = "Logo",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(50.dp)
                            .clip(CircleShape)
                    )
                    Spacer(modifier = Modifier.width(8.dp))

                    Column {
                        Text(text = "Nombre", fontWeight = FontWeight.Bold)
                        Text(text = "Ubicación")
                        Text(text = "Categoría")
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))
                Divider()
                Spacer(modifier = Modifier.height(10.dp))
                // Description
                Text(
                    text = "This is a description.This is a description.This is a description." +
                            "This is a description.This is a description.This is a description." +
                            "This is a description.This is a description.This is a description.",
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))
                // Section to add two images
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    // Replace these with your actual images
                    Image(
                        painter = painterResource(id = R.drawable.ic_launcher_foreground),
                        contentDescription = "Image 1",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(100.dp)
                    )
                    Image(
                        painter = painterResource(id = R.drawable.ic_launcher_foreground),
                        contentDescription = "Image 2",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(100.dp)
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                Divider()
                Spacer(modifier = Modifier.height(10.dp))
                val context = LocalContext.current
                Column(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    val phoneNumber = "123456789"
                    val dialNumber by rememberUpdatedState(phoneNumber)
                    val facebookUrl = "https://www.facebook.com/facebook"
                    val openFacebook by rememberUpdatedState(facebookUrl)
                    val instagramUrl = "https://www.instagram.com/instagram"
                    val openInstagram by rememberUpdatedState(instagramUrl)
                    val email = "email@example.com"
                    val openEmail by rememberUpdatedState(email)
                    val twitterUrl = "https://twitter.com/twitter"
                    val openTwitter by rememberUpdatedState(twitterUrl)
                    var openFacebookTrigger by remember { mutableStateOf(false) }
                    var openInstagramTrigger by remember { mutableStateOf(false) }
                    var openEmailTrigger by remember { mutableStateOf(false) }
                    var openTwitterTrigger by remember { mutableStateOf(false) }
                    var dialNumberTrigger by remember { mutableStateOf(false) }

                    Text(
                        text = "Teléfono: $phoneNumber",
                        modifier = Modifier.clickable {
                            dialNumber // This will trigger the LaunchedEffect below
                        }
                    )
                    Text(
                        text = "Facebook: @facebook",
                        modifier = Modifier.clickable {
                            openFacebookTrigger = true
                        }
                    )
                    Text(
                        text = "Instagram: @instagram",
                        modifier = Modifier.clickable {
                            openInstagramTrigger = true
                        }
                    )
                    Text(
                        text = "Correo: $email",
                        modifier = Modifier.clickable {
                            openEmailTrigger = true
                        }
                    )
                    Text(
                        text = "Twitter: @twitter",
                        modifier = Modifier.clickable {
                            openTwitterTrigger = true
                        }
                    )
                    LaunchedEffect(dialNumberTrigger) {
                        if (dialNumberTrigger) {
                            val dialIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$dialNumber"))
                            launchIntent(context, dialIntent)
                            dialNumberTrigger = false
                        }
                    }
                    LaunchedEffect(openFacebookTrigger) {
                        if (openFacebookTrigger) {
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(openFacebook))
                            launchIntent(context, intent)
                            openFacebookTrigger = false
                        }
                    }
                    LaunchedEffect(openInstagramTrigger) {
                        if (openInstagramTrigger) {
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(openInstagram))
                            launchIntent(context, intent)
                            openInstagramTrigger = false
                        }
                    }
                    LaunchedEffect(openEmailTrigger) {
                        if (openEmailTrigger) {
                            val intent = Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:$openEmail"))
                            launchIntent(context, intent)
                            openEmailTrigger = false
                        }
                    }
                    LaunchedEffect(openTwitterTrigger) {
                        if (openTwitterTrigger) {
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(openTwitter))
                            launchIntent(context, intent)
                            openTwitterTrigger = false
                        }
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
                /*MaterialTheme(
                    colorScheme = MaterialTheme.colorScheme.copy(primary = customRed, onPrimary = Color.White)
                ) {
                    Button(
                        onClick = {
                            val shareContent = """
                                Name: Name
                                Location: Location
                                Category: Category
                                Description: This is a description...
                            """.trimIndent()

                            val sendIntent: Intent = Intent().apply {
                                action = Intent.ACTION_SEND
                                putExtra(Intent.EXTRA_TEXT, shareContent)
                                type = "text/plain"
                            }

                            val shareIntent = Intent.createChooser(sendIntent, null)
                            startActivityLauncher.launch(shareIntent)
                        },
                        modifier = Modifier.width(100.dp).align(Alignment.CenterHorizontally)
                    ) {
                        Text("Share")
                    }
                }*/
                Divider()
                Spacer(modifier = Modifier.height(10.dp))
                if (appViewModel.isAdmin()) {
                    Text("Califica la OSC:")
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        (1..5).forEach { index ->
                            Icon(
                                imageVector = Icons.Default.Star,
                                contentDescription = "Star $index",
                                tint = if (savedStarRank >= index) customRed else customGray,
                                modifier = Modifier
                                    .clickable {
                                        if (isAllowedToRank) {
                                            savedStarRank = index
                                            val editor = prefs.edit()
                                            editor.putLong(LAST_RANKING_KEY, System.currentTimeMillis())
                                            editor.apply()
                                        } else {
                                            Toast.makeText(context, "Debe esperar 24 horas para volver a calificar.", Toast.LENGTH_SHORT).show()
                                        }
                                    }
                                    .padding(7.dp)
                                    .size(40.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

fun launchIntent(context: Context, intent: Intent) {
    try {
        context.startActivity(intent)
    } catch (e: ActivityNotFoundException) {
        Toast.makeText(context, "App not available", Toast.LENGTH_SHORT).show()
    }
}