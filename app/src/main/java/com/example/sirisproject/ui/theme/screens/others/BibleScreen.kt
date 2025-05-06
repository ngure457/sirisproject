package com.example.sirisproject.ui.theme.screens.others

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.SimpleDateFormat
import java.util.*

class DailyReadingsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                DailyReadingsScreen(
                    onBackPressed = { finish() },
                    onOriginalArticlePressed = {
                        // URL to the original article
                        val url = "https://www.usccb.org/bible/readings"
                        val intent = Intent(Intent.ACTION_VIEW)
                        intent.data = Uri.parse(url)
                        startActivity(intent)
                    }
                )
            }
        }
    }
}

data class DailyReading(
    val title: String,
    val reference: String,
    val content: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DailyReadingsScreen(
    onBackPressed: () -> Unit,
    onOriginalArticlePressed: () -> Unit
) {
    // Sample data - in a real app, this would come from an API or database
    val dateFormatter = SimpleDateFormat("EEEE, MMMM d, yyyy", Locale.getDefault())
    val currentDate = dateFormatter.format(Date())

    val readings = listOf(
        DailyReading(
            "First Reading",
            "Acts 1:1-11",
            "In the first book, Theophilus," +
                    " I dealt with all that Jesus did and taught until the day he was taken up, after giving instructions through the Holy Spirit to the apostles whom he had chosen. He presented himself alive to them by many proofs after he had suffered, appearing to them during forty days and speaking about the kingdom of God. While meeting with them, he enjoined them not to depart from Jerusalem," +
                    " but to wait for the promise of the Father about which you have heard me speak; for John baptized with water, but in a few days you will be baptized with the Holy Spirit.\n\n" +
                    "When they had gathered together they asked him," +
               "Lord, are you at this time going to restore the kingdom to Israel?" +
                " He answered them ,It is not for you to know the times or seasons that the Father has established by his own authority. But you will receive power when the Holy Spirit comes upon you, and you will be my witnesses in Jerusalem, throughout Judea and Samaria, and to the ends of the earth. When he had said this, as they were looking on, he was lifted up, and a cloud took him from their sight. While they were looking intently at the sky as he was going, suddenly two men dressed in white garments stood beside them." +
                    " They said, Men of Galilee, why are you standing there looking at the sky?" +
                    " This Jesus who has been taken up from you into heaven will return in the same way as you have seen him going into heaven."
    ),
    DailyReading(
        "Responsorial Psalm",
        "Psalm 47:2-3, 6-7, 8-9",
        "R. (6) God mounts his throne to shouts of joy: a blare of trumpets for the Lord.\n\n" +
                "All you peoples, clap your hands,\n" +
                "shout to God with cries of gladness,\n" +
                "For the LORD, the Most High, the awesome,\n" +
                "is the great king over all the earth.\n\n" +
                "R. God mounts his throne to shouts of joy: a blare of trumpets for the Lord.\n\n" +
                "God mounts his throne amid shouts of joy;\n" +
                "the LORD, amid trumpet blasts.\n" +
                "Sing praise to God, sing praise;\n" +
                "sing praise to our king, sing praise.\n\n" +
                "R. God mounts his throne to shouts of joy: a blare of trumpets for the Lord.\n\n" +
                "For king of all the earth is God;\n" +
                "sing hymns of praise.\n" +
                "God reigns over the nations,\n" +
                "God sits upon his holy throne.\n\n" +
                "R. God mounts his throne to shouts of joy: a blare of trumpets for the Lord."
    ),
    DailyReading(
        "Second Reading",
        "Ephesians 1:17-23",
        "Brothers and sisters: May the God of our Lord Jesus Christ, the Father of glory, give you a Spirit of wisdom and revelation resulting in knowledge of him. May the eyes of your hearts be enlightened, that you may know what is the hope that belongs to his call, what are the riches of glory in his inheritance among the holy ones, and what is the surpassing greatness of his power for us who believe, in accord with the exercise of his great might, which he worked in Christ, raising him from the dead and seating him at his right hand in the heavens, far above every principality, authority, power, and dominion, and every name that is named not only in this age but also in the one to come. And he put all things beneath his feet and gave him as head over all things to the church, which is his body, the fullness of the one who fills all things in every way."
    )
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Daily Readings") },
                navigationIcon = {
                    IconButton(onClick = onBackPressed) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Header with date and icon
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    Icons.Default.CalendarMonth,
                    contentDescription = "Calendar",
                    modifier = Modifier.size(28.dp),
                    tint = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = currentDate,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium
                )
            }

            Divider(modifier = Modifier.padding(horizontal = 16.dp))

            // Readings
            readings.forEach { reading ->
                ReadingCard(reading)
            }

            // Go to Original Article button
            Spacer(modifier = Modifier.height(24.dp))
            Button(
                onClick = onOriginalArticlePressed,
                modifier = Modifier
                    .padding(bottom = 32.dp)
                    .height(48.dp)
                    .fillMaxWidth(0.7f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            ) {
                Text(
                    "GO TO ORIGINAL ARTICLE",
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
fun ReadingCard(reading: DailyReading) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            // Reading title
            Text(
                text = reading.title,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(4.dp))

            // Reading reference
            Text(
                text = reading.reference,
                fontSize = 16.sp,
                fontStyle = FontStyle.Italic,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(12.dp))
            Divider()
            Spacer(modifier = Modifier.height(12.dp))

            // Reading content
            Text(
                text = reading.content,
                fontSize = 16.sp,
                lineHeight = 24.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DailyReadingsPreview() {
    MaterialTheme {
        DailyReadingsScreen(
            onBackPressed = {},
            onOriginalArticlePressed = {}
        )
    }
}
