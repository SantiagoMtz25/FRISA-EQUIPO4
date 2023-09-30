package com.example.loginpagetest.screens.createaccount

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.loginpagetest.R

@Composable
fun buttonSlider(onSwitchToggled: (Boolean) -> Unit) {
    val customLighterRed = colorResource(id = R.color.almostlogored)
    val customGray = colorResource(id = R.color.logoGray)
    Column(
        modifier = Modifier
            .padding(14.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        var isSwitchChecked by rememberSaveable { mutableStateOf(false) }

        Box(
            modifier = Modifier
                .height(48.dp) // Set height as you like
                .fillMaxWidth()
                .clickable {
                    isSwitchChecked = !isSwitchChecked
                    onSwitchToggled(isSwitchChecked)
                }
                .background(customLighterRed),
            contentAlignment = Alignment.Center
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "User Account",
                    color = if (!isSwitchChecked) Color.White else Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 16.dp)
                )

                Text(
                    text = "OSC Account",
                    color = if (isSwitchChecked) Color.White else Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 16.dp)
                )
            }

            // Slider Indicator
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(0.5f) // Occupy half space
                    .align(if (isSwitchChecked) Alignment.CenterEnd else Alignment.CenterStart)
                    .background(customGray.copy(alpha = 0.25f)) // Added transparency
                    .clip(RoundedCornerShape(10.dp))
            )
        }
    }
}
