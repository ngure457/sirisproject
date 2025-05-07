package com.example.sirisproject.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sirisproject.models.BibleReading
import com.example.sirisproject.ui.theme.screens.SplashScreen
import com.example.sirisproject.ui.theme.screens.dashboard.HomeScreen
import com.example.sirisproject.ui.theme.screens.logo.LoginScreen
import com.example.sirisproject.ui.theme.screens.others.DailyReadingsScreen
import com.example.sirisproject.ui.theme.screens.others.PrayerScreen
import com.example.sirisproject.ui.theme.screens.register.RegisterScreen

@Composable
fun AppNavHost(navController:NavHostController = rememberNavController(),
               startDestination:String = ROUTE_HOME){
    NavHost(navController = navController,startDestination=startDestination){
        composable(ROUTE_SPLASH){ SplashScreen {
            navController.navigate(ROUTE_SPLASH){
                popUpTo(ROUTE_HOME){inclusive=true}  } } }
        composable(ROUTE_REGISTER) { RegisterScreen(navController) }
        composable(ROUTE_LOGIN) { LoginScreen(navController) }
        composable(ROUTE_HOME) { HomeScreen(navController)  }
        composable(ROUTE_BIBLE) { DailyReadingsScreen(navController)  }
        composable(ROUTE_ADD_PRODUCT) { AddproductScreen(navController)  }
        composable("$ROUTE_UPDATE_PRODUCT/{productId}") {

                passedData -> UpdateproductScreen(
            navController, passedData.arguments?.getString("productId")!!
        )
        }

    }



