package com.example.sirisproject.ui.theme.screens.dashboard

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.compose.material.icons.filled.*
import com.example.sirisproject.data.composable
import com.example.sirisproject.ui.theme.screens.others.EventsScreen
import com.example.sirisproject.ui.theme.screens.others.ProfileScreen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    val navController = rememberNavController()
    var selectedTab by remember { mutableStateOf(BottomNavItem.Home) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Grace Community Church") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        },
        bottomBar = {
            NavigationBar {
                BottomNavItems.forEach { item ->
                    NavigationBarItem(
                        icon = { Icon(item.icon, contentDescription = item.title) },
                        label = { Text(item.title) },
                        selected = selectedTab == item,
                        onClick = {
                            selectedTab = item
                            navController.navigate(item.route) {
                                popUpTo(navController.graph.startDestinationId)
                                launchSingleTop = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = BottomNavItem.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("home") {
                homescreen()
            }
            composable(BottomNavItem.Events.route) {
                EventsScreen()
            }
            composable(BottomNavItem.Bible.route) {
                BibleScreen()
            }
            composable(BottomNavItem.Prayer.route) {
                PrayerScreen()
            }
            composable(BottomNavItem.Profile.route) {
                ProfileScreen()
            }
            composable(BottomNavItem.More.route) {
                MoreScreen()
            }
        }
    }