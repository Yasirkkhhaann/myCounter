package com.example.composecourse

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.*
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.*
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

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
import androidx.compose.foundation.layout.add
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DhikrListScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Dhikr List", color = Color.Black) },
                navigationIcon = {
                    IconButton(onClick = { /* Handle back navigation */ }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.Black
                        )
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = Color(0xFFEFEFEF)
                )
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
                    .background(Brush.linearGradient(listOf(Color.Cyan, Color.Blue)))
            ) {

                Spacer(modifier = Modifier.height(16.dp))

                // Zikr Items
                LazyColumn(
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(zikrList) { zikr ->
                        ZikrCard(
                            count = zikr.count,
                            title = zikr.title,
                            onDelete = { /* Handle delete */ },
                            onContinue = { /* Handle continue */ },
                            onEdit = { /* Handle edit */ }
                        )
                    }
                }
            }
        }, floatingActionButton = {
            FloatingActionButton(onClick = { }) {
                Icon(Icons.Filled.Add, contentDescription = "Add")
            }
        }
    )
}

@Composable
fun ZikrCard(
    count: Int,
    title: String,
    onDelete: () -> Unit,
    onContinue: () -> Unit,
    onEdit: () -> Unit
) {

    Card(
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(0.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
        modifier = Modifier
            .fillMaxWidth()
            .background(Brush.linearGradient(listOf(Color(0xFF66C3F4), Color(0xFFF8B48F))))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Box(

                    modifier = Modifier
                        .size(50.dp)
                        .background(Brush.linearGradient(listOf(Color.Red, Color.Blue)), shape = CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = count.toString(),
                        color = Color.White,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }

                Spacer(modifier = Modifier.width(10.dp))

                // Zikr title
                Text(
                    text = "Name: ($title)",
                    color = Color.Black,
                    style = MaterialTheme.typography.titleMedium,
                    fontSize = 18.sp
                    // modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(10.dp))

                IconButton(onClick = onEdit) {
                    Icon(
                        Icons.Default.Edit, // Replace with edit icon resource
                        contentDescription = "Edit",
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))


            // Action buttons
            Column(horizontalAlignment = Alignment.Start) {

                Row() {
                    Button(
                        modifier = Modifier.weight(1f),
                        onClick = onContinue,
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF720DFF))
                    ) {
                        Text(text = "Continue", color = Color.White)
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    OutlinedButton(
                        onClick = onDelete,
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(text = "Delete")
                    }
                }


                Spacer(modifier = Modifier.width(8.dp))


            }
        }
    }
}


// Sample data model for zikr
data class Zikr(val count: Int, val title: String)

// Sample list of zikr
val zikrList = listOf(
    Zikr(15, "Lillah"),
    Zikr(60, "1234"),
    Zikr(19, "554"),
    Zikr(60, "1234"),
    Zikr(19, "554"),
    Zikr(60, "1234"),
    Zikr(19, "554"),
    Zikr(60, "1234"),
    Zikr(19, "554"),
    Zikr(60, "1234"),
    Zikr(19, "554"),
    Zikr(60, "1234"),
    Zikr(19, "554"),
    Zikr(60, "1234"),
    Zikr(19, "554")
)
