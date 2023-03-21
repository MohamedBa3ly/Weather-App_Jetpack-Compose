package com.example.weatherapp.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

//sealed class Screen(val route: String){
//    object Search: Screen(route = "search_screen")
//    object Home: Screen(route = "home_screen")
//    object Add: Screen(route = "add_screen")
//}

sealed class BottomNavigationScreen(val route: String,val title: String, val icon: ImageVector){
    object Search: BottomNavigationScreen(route = "search_screen", title = "Search", icon = Icons.Filled.Search)
    object Home: BottomNavigationScreen(route = "home_screen", title = "Home", icon = Icons.Filled.Home)
    object Add: BottomNavigationScreen(route = "add_screen", title = "Add", icon = Icons.Filled.Add)
}

val bottomNavigationItems = listOf(
    BottomNavigationScreen.Search,
    BottomNavigationScreen.Home,
    BottomNavigationScreen.Add
)