package com.example.sirisproject.ui.theme.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.sirisproject.R
import com.example.sirisproject.navigation.ROUTE_REGISTER
import com.example.sirisproject.navigation.ROUTE_SPLASH
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(modifier: Modifier = Modifier, navController: NavController) {
    val backgroundColor = Color(0x9FCAE3E1) // Light cyan background
    LaunchedEffect(Unit) {
        delay(3000L) // Wait for 3 seconds
        navController.navigate(ROUTE_REGISTER) {
            popUpTo(ROUTE_SPLASH) { inclusive = true } // Remove splash from back stack
        }
    }
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(backgroundColor)
            .onGloballyPositioned { Log.d("SplashScreen", "Box rendered") }, // Debug log
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(R.drawable.logo8),
                contentDescription = "App Logo",
                modifier = Modifier
                    .size(200.dp)
                    .border(2.dp, Color.Red), // Red border to confirm visibility
                contentScale = ContentScale.Fit // Ensure image scales properly
            )
        }
    }
}