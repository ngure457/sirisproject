package com.example.sirisproject.ui.theme.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@Composable

fun SplashScreen(modifier: Modifier = Modifier,navController:androidx.navigation.NavController ) {
    val backgroundColor = Color(0x9FCAE3E1)
    LaunchedEffect(key1 = true) {
        delay(2000)
        navController.navigate("auth"){
            popUpTo("splash"){
                inclusive = true
            }
        }

    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            Icon(
                imageVector = Icons.Default.Favorite,
                contentDescription = "Prayer App Icon",
                modifier = Modifier.size(100.dp),
                tint = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Prayer Connect",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}