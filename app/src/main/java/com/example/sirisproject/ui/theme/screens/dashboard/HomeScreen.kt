package com.example.sirisproject.ui.theme.screens.dashboard

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.sirisproject.R
import com.example.sirisproject.navigation.ROUTE_BIBLE
import com.example.sirisproject.navigation.ROUTE_CALL
import com.example.sirisproject.navigation.ROUTE_EVENTS
import com.example.sirisproject.navigation.ROUTE_MONEY
import com.example.sirisproject.navigation.ROUTE_PRAYER
import com.example.sirisproject.navigation.ROUTE_PROFILE
import com.example.sirisproject.navigation.ROUTE_SHOP



data class FeatureCard(
    val id: Int,
    val title: String,
    val imageResId: Int, // Resource ID for the image
    val destinationActivity: Class<*>? = null // Activity to launch when card is clicked
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    val context = LocalContext.current

    // Sample feature cards - in a real app, you might get these from a repository
    val features = listOf(
        FeatureCard(
            id = 1,
            title = "Daily Readings",
            imageResId = R.drawable.daily_readings,
            destinationActivity = ROUTE_BIBLE::class.java
        ),
        FeatureCard(
            id = 2,
            title = "Offertory",
            imageResId = R.drawable.daily_reading3,
            destinationActivity = ROUTE_MONEY::class.java
        ),
        FeatureCard(
            id = 3,
            title = "Upcoming Events",
            imageResId = R.drawable.daily_readings2,
            destinationActivity = ROUTE_EVENTS::class.java
        ),
        FeatureCard(
            id = 4,
            title = "Pray Today",
            imageResId = R.drawable.daily_readings5,
            destinationActivity = ROUTE_PRAYER::class.java
        ),
        FeatureCard(
            id = 5,
            title = "What's in the Shop?",
            imageResId = R.drawable.shopping,
            destinationActivity = ROUTE_SHOP::class.java
        ),
        FeatureCard(
            id = 6,
            title = "Call US",
            imageResId = R.drawable.calling,
            destinationActivity = ROUTE_CALL::class.java
        )
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("The Congregation") },
                navigationIcon = {
                    IconButton(onClick = { ROUTE_PROFILE }) {
                        Icon(Icons.Default.Person, contentDescription = "person icon")
                    }
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(vertical = 16.dp)
        ) {
            items(features) { feature ->
                FeatureCardItem(
                    feature = feature,
                    onClick = {
                        // Launch the associated activity when card is clicked
                        feature.destinationActivity?.let { activityClass ->
                            val intent = Intent(context, activityClass)
                            context.startActivity(intent)
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun FeatureCardItem(feature: FeatureCard, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(220.dp) // Medium height for card
            .clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Card image
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp) // Allocate most space to the image
            ) {
                // In a real app, you might want to use a library like Coil or Glide
                // This is a placeholder using a local resource
                Image(
                    painter = painterResource(id = feature.imageResId),
                    contentDescription = feature.title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }

            // Card title
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp) // Fixed height for the title section
                    .padding(8.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = feature.title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

// For preview purposes
@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    MaterialTheme {
        HomeScreen(navController = NavController(LocalContext.current))
    }
}
