package com.example.weatherapp.ui.navigation



import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import com.example.weatherapp.R
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.weatherapp.ui.add.AddingCity
import com.example.weatherapp.ui.home.FinalHome
import com.example.weatherapp.ui.home.viewmodel.HomeViewModel
import com.example.weatherapp.ui.search.LetsSearch


//@RequiresApi(Build.VERSION_CODES.TIRAMISU)
//@Composable
//fun SetupNavGraph(navController: NavHostController){
//
//    val searchViewModel = hiltViewModel<HomeViewModel>()
//
//    val cardList = searchViewModel.customX
////    val lastSearch = cardList[(cardList.size)-1]
//
//    NavHost(
//        navController =navController ,
//        startDestination = Screen.Search.route
//    ){
//        composable(
//            route = Screen.Search.route
//        ){
//            LetsSearch(navController = navController, searchViewModel = searchViewModel)
//        }
//
//        composable(
//            route = Screen.Home.route
//        ){
//            FinalHome(searchViewModel = searchViewModel, lastSearch = searchViewModel.selectedX.value, navController = navController)
//        }
//
//        composable(
//                route = Screen.Add.route
//                ){
//            AddingCity(searchViewModel = searchViewModel, navController = navController)
//        }
//    }
//}

@Composable
fun AppBottomNavigation(navController: NavController, bottomNavigationScreen: List<BottomNavigationScreen>){
    BottomNavigation (
        backgroundColor = colorResource(id = R.color.baby_blue)
            ) {

        //To make color on selected icon :
        val backStackEntry = navController.currentBackStackEntryAsState()

        bottomNavigationScreen.forEach { screen ->
            BottomNavigationItem(
                icon = {
                    Icon( imageVector = screen.icon, contentDescription = null,
                        tint =if (backStackEntry.value?.destination?.route==screen.route) Color.Blue else Color.White)
                },
                label = {
                    Text(text = screen.route)
                },
                selected = false, alwaysShowLabel = false, onClick = {
                    when(screen.route){
                        "search_screen" -> navController.navigate(BottomNavigationScreen.Search.route){
                            popUpTo(navController.graph.findStartDestination().id){
                                saveState = true
                            }
                            launchSingleTop = true
//                            restoreState = true
                        }
                        "home_screen" -> navController.navigate(BottomNavigationScreen.Home.route){
                            popUpTo(navController.graph.findStartDestination().id){
                                saveState = true
                            }
                            launchSingleTop = true
//                            restoreState = true
                        }
                        "add_screen" -> navController.navigate(BottomNavigationScreen.Add.route){
                            popUpTo(navController.graph.findStartDestination().id){
                                saveState = true
                            }
                            launchSingleTop = true
//                            restoreState = true
                        }
                    }

                }

            )
        }

    }
}

@Composable
fun SetupNavGraphFinal(navController: NavHostController){

    val searchViewModel = hiltViewModel<HomeViewModel>()


    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = { AppBottomNavigation(navController = navController, bottomNavigationScreen = bottomNavigationItems ) }
    ){

        NavHost(navController = navController, startDestination = BottomNavigationScreen.Search.route, modifier = Modifier.padding(it)){

            composable(route = BottomNavigationScreen.Search.route){
                LetsSearch(searchViewModel = searchViewModel, navController = navController)
            }

            composable(route = BottomNavigationScreen.Home.route){

                val context = LocalContext.current
                searchViewModel.readData(context)
                val selectedCityWeather = searchViewModel.selectedCustomDataBase.collectAsState().value

                FinalHome(lastSearch = selectedCityWeather, navController = navController)

            }

            composable(route = BottomNavigationScreen.Add.route){
                AddingCity(searchViewModel = searchViewModel, navController = navController)
            }


        }
    }


}