package com.example.sirisproject.ui.theme.screens.dashboard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.CallEnd
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.sirisproject.navigation.ROUTE_HOME


data class Profile(
    val id: Int,
    val name: String,
    val title: String,
    val phone: String,
    val email: String,
    val avatarUrl: String,
    val department: String
)

// Sample data
val sampleProfiles = listOf(
    Profile(
        id = 1,
        name = "FR.Simon",
        title = "Archbishop of Nairobi",
        phone = "555-123-4567",
        email = "father.simon@gmail.com",
        avatarUrl = "https://via.placeholder.com/100",
        department = "Prayer"
    ),
    Profile(
        id = 2,
        name = "FR.John",
        title = "Priest",
        phone = "555-987-6543",
        email = "john.doe@gmail.com",
        avatarUrl = "https://via.placeholder.com/100",
        department = "Counseling"
    ),
    Profile(
        id = 3,
        name = "Sister Jane",
        title = "Head of Sisters in Nairobi",
        phone = "555-567-8901",
        email = "Sister.Jane@gmail.com",
        avatarUrl = "https://via.placeholder.com/100",
        department = "Guidance & Counseling"
    ),
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CallScreen(navController: NavController) {
    val profiles = remember { mutableStateOf(sampleProfiles) }
    var searchQuery by remember { mutableStateOf("") }
    var callingProfile by remember { mutableStateOf<Profile?>(null) }

    val filteredProfiles = profiles.value.filter {
        it.name.contains(searchQuery, ignoreCase = true) ||
                it.title.contains(searchQuery, ignoreCase = true)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Call Us Today") },
                navigationIcon = {
                    IconButton(onClick = {
                        // Safe navigation to home screen
                        navController.navigate(ROUTE_HOME) {
                            // Pop up to the home destination to avoid building up a large stack of destinations
                            popUpTo(ROUTE_HOME) { inclusive = true }
                        }

                    }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back to Home")
                    }
                },
                actions = {
                    IconButton(onClick = { /* Handle search action */ }) {
                        Icon(Icons.Default.Search, contentDescription = "Search")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    actionIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { paddingValues ->
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                // Search Field
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    placeholder = { Text("Search contacts...") },
                    leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = MaterialTheme.colorScheme.primary,
                        unfocusedBorderColor = MaterialTheme.colorScheme.outline
                    )
                )

                // Contact List
                if (filteredProfiles.isEmpty()) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "No contacts found",
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                } else {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .background(MaterialTheme.colorScheme.background)
                    ) {
                        items(filteredProfiles) { profile ->
                            ProfileCard(
                                profile = profile,
                                onCallClick = { callingProfile = profile }
                            )
                            Divider()
                        }
                    }
                }
            }

            // Calling Dialog
            callingProfile?.let { profile ->
                CallDialog(
                    profile = profile,
                    onDismiss = { callingProfile = null }
                )
            }
        }
    }
}

@Composable
fun ProfileCard(profile: Profile, onCallClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Avatar - Using Coil for image loading
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(profile.avatarUrl)
                .crossfade(true)
                .build(),
            contentDescription = "Avatar of ${profile.name}",
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
            //error = painterResource(id = R.drawable.ic_person_placeholder),
            //placeholder = painterResource(id = R.drawable.ic_person_placeholder)
        )

        // Profile Info
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 12.dp)
        ) {
            Text(
                text = profile.name,
                style = MaterialTheme.typography.bodyLarge,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = profile.title,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }

        // Call Button
        FloatingActionButton(
            onClick = onCallClick,
            modifier = Modifier.size(40.dp),
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.primary
        ) {
            Icon(
                Icons.Default.Call,
                contentDescription = "Call ${profile.name}",
                modifier = Modifier.size(20.dp)
            )
        }
    }
}

@Composable
fun CallDialog(profile: Profile, onDismiss: () -> Unit) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = false
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.6f)),
            contentAlignment = Alignment.Center
        ) {
            Card(
                modifier = Modifier
                    .width(280.dp)
                    .wrapContentHeight(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            ) {
                Column(
                    modifier = Modifier
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Avatar - Using Coil for image loading
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(profile.avatarUrl)
                            .crossfade(true)
                            .build(),
                        contentDescription = "Avatar of ${profile.name}",
                        modifier = Modifier
                            .size(80.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                       // error = painterResource(id = R.drawable.ic_person_placeholder),
                       // placeholder = painterResource(id = R.drawable.ic_person_placeholder)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Name and Phone
                    Text(
                        text = profile.name,
                        style = MaterialTheme.typography.titleLarge
                    )

                    Text(
                        text = profile.phone,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Calling...",
                        color = MaterialTheme.colorScheme.primary,
                        style = MaterialTheme.typography.bodyMedium
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    // End Call Button
                    FloatingActionButton(
                        onClick = onDismiss,
                        containerColor = MaterialTheme.colorScheme.error,
                        contentColor = MaterialTheme.colorScheme.onError
                    ) {
                        Icon(
                            Icons.Default.CallEnd,
                            contentDescription = "End Call"
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun AsyncImage(
    model: ImageRequest,
    contentDescription: String,
    modifier: Modifier,
    contentScale: ContentScale,
   // error: painterResource,
    //placeholder: painterResource
) {
    TODO("Not yet implemented")
}

//@Preview(showBackground = true)
//@Composable
//fun ContactDirectoryPreview() {
//    MaterialTheme {
//        ContactDirectoryApp()
//    }
//}

