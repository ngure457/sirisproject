package com.example.sirisproject.ui.theme.screens.others

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

data class PrayerType(
    val id: Int,
    val title: String,
    val description: String,
    val icon: ImageVector,
    val backgroundColor: Color,
    val iconColor: Color
)

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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF9FAFB))
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

@Preview(showBackground = true)
@Composable
fun PrayerScreenPreview() {
    MaterialTheme {
        PrayerScreen(navController = NavHostController(LocalContext.current))
    }
}