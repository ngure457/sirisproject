package com.example.sirisproject.ui.theme.screens.others

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.NightsStay
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.sirisproject.navigation.ROUTE_HOME

data class PrayerType(
    val id: Int,
    val title: String,
    val description: String,
    val icon: androidx.compose.ui.graphics.vector.ImageVector,
    val backgroundColor: Color,
    val iconColor: Color
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrayerScreen(navController: NavHostController) {
    var selectedType by remember { mutableStateOf<Int?>(null) }

    val prayerTypes = listOf(
        PrayerType(
            id = 1,
            title = "Personal Prayer",
            description = "Intimate communication with God about personal matters, concerns, and thanksgiving.",
            icon = Icons.Default.Favorite,
            backgroundColor = Color(0xFFFCE7F3), // Light pink
            iconColor = Color(0xFFDB2777)         // Darker pink
        ),
        PrayerType(
            id = 2,
            title = "The Rosary",
            description = "Praying on behalf of YOU & others, lifting up their needs and concerns to God.",
            icon = Icons.Default.People,
            backgroundColor = Color(0xFFDCFCE7), // Light blue
            iconColor = Color(0xFF2563EB)        // Darker blue
        ),
        PrayerType(
            id = 3,
            title = "Meditation Prayer",
            description = "Quiet reflection and listening for divine guidance and wisdom.",
            icon = Icons.Default.Star,
            backgroundColor = Color(0xFFF3E8FF), // Light purple
            iconColor = Color(0xFF9333EA)        // Darker purple
        ),
        PrayerType(
            id = 4,
            title = "Scripture Prayer",
            description = "Praying the words of sacred texts and applying them to your life.",
            icon = Icons.Default.Book,
            backgroundColor = Color(0xFFD1FAE5), // Light green
            iconColor = Color(0xFF059669)        // Darker green
        ),
        PrayerType(
            id = 5,
            title = "Morning Prayer",
            description = "Beginning the day with gratitude and seeking guidance for the hours ahead.",
            icon = Icons.Default.WbSunny,
            backgroundColor = Color(0xFFFEF3C7), // Light amber
            iconColor = Color(0xFFD97706)        // Darker amber
        ),
        PrayerType(
            id = 6,
            title = "Evening Prayer",
            description = "Reflecting on the day's events and releasing concerns before rest.",
            icon = Icons.Default.NightsStay,
            backgroundColor = Color(0xFFE0E7FF), // Light indigo
            iconColor = Color(0xFF4F46E5)        // Darker indigo
        ),
        PrayerType(
            id = 7,
            title = "Contemplative Prayer",
            description = "Deep focus on divine presence through silence and stillness.",
            icon = Icons.Default.Timer,
            backgroundColor = Color(0xFFCCFBF1), // Light teal
            iconColor = Color(0xFF0D9488)        // Darker teal
        )
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Pray Today") },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            // Navigate to home screen directly, clearing the back stack
                            navController.navigate(ROUTE_HOME) {
                                // Pop up to the start destination of the graph to
                                // avoid building up a large stack of destinations
                                popUpTo(ROUTE_HOME) {
                                    inclusive = false
                                }
                                // Avoid multiple copies of the same destination when
                                // reselecting the same item
                                launchSingleTop = true
                            }

                            // Alternatively, you can use the onBackPressed callback if preferred
                            // onBackPressed()
                        }
                    ) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back to Home")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF9FAFB))
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            // Header
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Types of Prayer",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1F2937)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Explore different ways to connect spiritually",
                    fontSize = 16.sp,
                    color = Color(0xFF4B5563)
                )
            }

            // Grid of prayer types
            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 160.dp),
                contentPadding = PaddingValues(8.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(prayerTypes) { prayerType ->
                    PrayerTypeCard(
                        prayerType = prayerType,
                        isSelected = selectedType == prayerType.id,
                        onClick = {
                            selectedType = if (selectedType == prayerType.id) null else prayerType.id
                        }
                    )
                }
            }

            // Footer
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Click on a prayer type to learn more about it",
                    fontSize = 14.sp,
                    color = Color(0xFF6B7280)
                )
            }
        }
    }
}

@Composable
fun PrayerTypeCard(
    prayerType: PrayerType,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = if (isSelected) 8.dp else 2.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            // Icon container
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(prayerType.backgroundColor),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = prayerType.icon,
                    contentDescription = null,
                    tint = prayerType.iconColor,
                    modifier = Modifier.size(24.dp)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Title
            Text(
                text = prayerType.title,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF1F2937)
            )

            // Description (only shown when selected)
            if (isSelected) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = prayerType.description,
                    fontSize = 14.sp,
                    color = Color(0xFF4B5563)
                )
            }
        }
    }
}