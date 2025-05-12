package com.example.sirisproject.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sirisproject.ui.theme.screens.SplashScreen
import com.example.sirisproject.ui.theme.screens.dashboard.HomeScreen
import com.example.sirisproject.ui.theme.screens.logo.LoginScreen
import com.example.sirisproject.ui.theme.screens.others.DailyReadingsScreen
import com.example.sirisproject.ui.theme.screens.others.EventsScreen
import com.example.sirisproject.ui.theme.screens.others.MoneyScreen
import com.example.sirisproject.ui.theme.screens.others.PrayerScreen
import com.example.sirisproject.ui.theme.screens.others.ProductListScreen
import com.example.sirisproject.ui.theme.screens.others.ProfileScreen
import com.example.sirisproject.ui.theme.screens.others.UpdateproductScreen
import com.example.sirisproject.ui.theme.screens.register.RegisterScreen



@Composable
fun AppNavHost(
    navController: NavHostController = rememberNavController(),
    startDestination: String = ROUTE_SPLASH
) {
    NavHost(navController = navController, startDestination = startDestination) {
        composable(ROUTE_SPLASH) {
            SplashScreen(modifier = Modifier, navController)
            navController.navigate(ROUTE_REGISTER){
                popUpTo(ROUTE_HOME){inclusive = true}
            }
        }

        composable(ROUTE_REGISTER) {
            RegisterScreen(navController)
        }

        composable(ROUTE_LOGIN) {
            LoginScreen(navController)
        }

        composable(ROUTE_HOME) {
            HomeScreen(navController)
        }

        composable(ROUTE_BIBLE) {
            DailyReadingsScreen(
                navController = navController,
                onBackPressed = {
                    // Navigate directly to home screen
                    navController.navigate(ROUTE_HOME) {
                        popUpTo(ROUTE_HOME) {
                            inclusive = false
                        }
                        launchSingleTop = true
                    }
                },
                onOriginalArticlePressed = {
                    // Add logic for opening the original article
                    // Example: open a web browser or navigate to another screen
                }
            )
        }

        composable(ROUTE_EVENTS) {
            EventsScreen(navController)
        }

        composable(ROUTE_MONEY) {
            MoneyScreen(navController)
        }

        composable(ROUTE_PROFILE) {
            ProfileScreen(navController)
        }

        composable(ROUTE_PRAYER) {
            PrayerScreen(navController)
        }

        composable(ROUTE_SHOP) {
            ProductListScreen(navController)
        }
        composable("$ROUTE_UPDATE_PRODUCT/{productId}") {

                passedData -> UpdateproductScreen(
            navController, passedData.arguments?.getString("productId")!!
        )
        }

    }
}




