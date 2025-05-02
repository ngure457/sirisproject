package com.example.sirisproject.ui.theme.screens.dashboard

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(navController: NavController){
    val selectedItem = remember { mutableStateOf(0) }
    val authViewModel : AuthViewModel = viewModel()
    val context = LocalContext.current
   // Scaffold (
        //bottomBar = { NavigationBar(containerColor = Color.Cyan,){
          //  NavigationBarItem(
            //    selected = selectedItem.value == 0,
             //   onClick = {selectedItem.value = 0
             //       val intent= Intent(Intent.ACTION_DIAL).apply{
                 //       data= Uri.parse("tel:0789654321")
              //      }
                   // context.startActivity(intent)
              //  },
              //  icon = { Icon(imageVector = Icons.Default.Phone, contentDescription = "Phone") },
              //  label = { Text(text = "Phone") },
              //  alwaysShowLabel = true,
           // )
    {
         //   innerPadding->

        Box(){
            Image(
                painterResource(id = R.drawable.background2),
                contentDescription = "Dashboard background image",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.padding(innerPadding)
            )
        }
        Column (modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally){
            TopAppBar(
                title = { Text(text = "Dashboard") },
                navigationIcon =
                    {
                        IconButton(onClick = {})
                        { Icon(imageVector = Icons.Filled.Home, contentDescription = "Home") }
                    }
                ,
                actions = {
                    IconButton(onClick = {})
                    {
                        Icon(imageVector = Icons.Filled.Search, contentDescription = "Search")
                    }
                    IconButton(onClick = {})
                    {
                        Icon(imageVector = Icons.Filled.Person, contentDescription = "Profile")
                    }
                    IconButton(onClick = { authViewModel.logout(navController, context) })
                    {
                        Icon(imageVector = Icons.Filled.Logout, contentDescription = "Logout")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Cyan,
                    titleContentColor = Color.Black,
                    navigationIconContentColor = Color.Magenta,
                    actionIconContentColor = Color.White)
