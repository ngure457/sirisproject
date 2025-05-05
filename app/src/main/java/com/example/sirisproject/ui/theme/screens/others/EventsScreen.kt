package com.example.sirisproject.ui.theme.screens.others

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun EventsScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            "Upcoming Events",
            style = MaterialTheme.typography.headlineSmall
        )

        // Event Cards
        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    "Sunday Worship Service",
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    "May 11, 2025 • 9:00 AM & 11:00 AM",
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    "Join us for praise, worship, and a message from Pastor John on 'Living a Life of Purpose'.",
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    Button(
                        onClick = { /* TODO: View event details */ }
                    ) {
                        Text("Details")
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(
                        onClick = { /* TODO: Add to calendar */ }
                    ) {
                        Text("Add to Calendar")
                    }
                }
            }
        }

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    "Weekly Bible Study",
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    "May 13, 2025 • 7:00 PM",
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    "Join us as we dive into the Book of Romans and explore its relevance for our lives today.",
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    Button(
                        onClick = { /* TODO: View event details */ }
                    ) {
                        Text("Details")
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(
                        onClick = { /* TODO: Add to calendar */ }
                    ) {
                        Text("Add to Calendar")
                    }
                }
            }
        }
    }
}