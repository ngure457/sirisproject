package com.example.sirisproject.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sirisproject.ui.theme.screens.SplashScreen
import com.example.sirisproject.ui.theme.screens.dashboard.HomeScreen
import com.example.sirisproject.ui.theme.screens.others.PrayerScreen

@Composable
fun AppNavHost(
    navController: NavHostController = rememberNavController(),
    startDestination: String = ROUTE_SPLASH
) {
    NavHost(navController = navController, startDestination = startDestination) {
        composable(ROUTE_SPLASH) {
            SplashScreen {
                val ROUTE_HOME = "HomeScreen"
                navController.navigate(ROUTE_HOME) {
                    popUpTo(ROUTE_SPLASH) { inclusive = true }
                }
            }
        }

        val ROUTE_HOME = "HomeScreen"
        composable(ROUTE_HOME) {
            HomeScreen(navController)
        }

        val ROUTE_PRAYER = "PrayerScreen"
        composable(ROUTE_PRAYER) {
            PrayerScreen(navController)
        }


        }
    }



