package com.example.composecourse

import android.content.Context
import android.media.MediaPlayer
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mycounter.R


@Composable
fun SplashScreen() {
    // Font family
    val fontFamily = FontFamily(Font(R.font.dsdigi))

    // Remember states
    var count by rememberSaveable { mutableStateOf(0) }
    var isVibratorOn by rememberSaveable { mutableStateOf(true) }
    var isSoundOn by rememberSaveable { mutableStateOf(true) }

    // Context and vibrator setup
    val context = LocalContext.current
    val vibrator = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        context.getSystemService(VibratorManager::class.java)?.defaultVibrator
    } else {
        context.getSystemService(Vibrator::class.java)
    }

    // Main layout
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFAA00AA)), // Background color
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // Top Buttons
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(onClick = { /* Remove Ads */ }) {
                Text(text = "Remove Ads")
            }
            Button(onClick = { }) {
                Text(text = "Dhikr List")
            }
        }

        // Counter design
        Box(
            modifier = Modifier
                .size(250.dp)
                .background(Color.Black, shape = RoundedCornerShape(50))
        ) {
            Column(
                modifier = Modifier.align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CountsDisplay(count, fontFamily)

                Spacer(modifier = Modifier.height(16.dp))

                Row(horizontalArrangement = Arrangement.SpaceEvenly) {
                    Button(onClick = {
                        count++
                        if (isVibratorOn) {
                            vibrator?.vibrate(
                                VibrationEffect.createOneShot(150, VibrationEffect.DEFAULT_AMPLITUDE)
                            )
                        }
                        if (isSoundOn) {
                            playSound(context, R.raw.moderntechnology)
                        }
                    }) {
                        Text(text = "Count")
                    }
                    Button(onClick = { count = 0 }) {
                        Text(text = "Reset")
                    }
                }
            }
        }

        // Bottom Buttons
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            IconButtonBox(
                onClick = { /* Favorite action */ },
                icon = Icons.Default.Search,
                description = "Search"
            )

            IconButtonBox(
                onClick = { isVibratorOn = !isVibratorOn },
                iconResId = if (isVibratorOn) R.drawable.vibrate else R.drawable.vibrateoff,
                description = "Vibrate Toggle"
            )

            IconButtonBox(
                onClick = { isSoundOn = !isSoundOn },
                iconResId = if (isSoundOn) R.drawable.speaker else R.drawable.speakeroff,
                description = "Sound Toggle"
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountsDisplay(count: Int, fontFamily: FontFamily) {
    TextField(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(50),
        readOnly = true,
        textStyle = TextStyle(
            fontFamily = fontFamily,
            textAlign = TextAlign.Center,
            fontSize = 60.sp,
            color = Color.White
        ),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.Black,
            unfocusedContainerColor = Color.Black,
            disabledContainerColor = Color.Black,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        value = count.toString(),
        onValueChange = {}
    )
}

@Composable
fun IconButtonBox(onClick: () -> Unit, iconResId: Int? = null, icon: ImageVector? = null, description: String) {
    Box(
        modifier = Modifier
            .size(50.dp)
            .background(Color.Black, shape = RoundedCornerShape(50))
    ) {
        IconButton(onClick = onClick) {
            if (icon != null) {
                Icon(icon, contentDescription = description, tint = Color.White)
            } else if (iconResId != null) {
                Icon(
                    painter = painterResource(id = iconResId),
                    contentDescription = description,
                    tint = Color.White,
                    modifier = Modifier.size(48.dp)
                )
            }
        }
    }
}

fun playSound(context: Context, soundResId: Int) {
    val mediaPlayer = MediaPlayer.create(context, soundResId)
    mediaPlayer.setOnCompletionListener { it.release() } // Release resources after playing
    mediaPlayer.start() // Start playing sound
}
