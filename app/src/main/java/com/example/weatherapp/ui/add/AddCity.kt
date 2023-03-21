package com.example.weatherapp.ui.add


import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import com.example.weatherapp.R
import com.example.weatherapp.model.CardWeatherFinal
import com.example.weatherapp.ui.home.viewmodel.HomeViewModel
import com.example.weatherapp.ui.navigation.BottomNavigationScreen
import me.saket.swipe.SwipeAction
import me.saket.swipe.SwipeableActionsBox


@Composable
fun AddingCity(searchViewModel: HomeViewModel,navController: NavController) {

    searchViewModel.readCardDataBase()
    val cardList = searchViewModel.customDataBase.collectAsState().value

    Scaffold (
        modifier = Modifier
            .fillMaxSize(),
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate(BottomNavigationScreen.Search.route)},
                backgroundColor = colorResource(id = R.color.strong_blue),
                content = {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            )
        },
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Adding City")
                },
                elevation = AppBarDefaults.TopAppBarElevation,
                backgroundColor = colorResource(id = R.color.baby_blue),
                contentColor = colorResource(id = R.color.white),
                navigationIcon = {
                    IconButton(onClick = { navController.navigate(BottomNavigationScreen.Search.route){
                        popUpTo(navController.graph.findStartDestination().id){
                            saveState = true
                        }
                        launchSingleTop = true
                    } }) {
                        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription =null )
                    }
                }
            )
        }
            ){

        Box(
            modifier = Modifier
                .padding(it)
        ) {
            Image(
                painter = painterResource(id = R.drawable.back ),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(4.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = "Manage Cities",
                    fontSize = 32.sp,
                    fontStyle = FontStyle.Normal,
                    color = colorResource(id = R.color.white),
                    modifier = Modifier.padding(25.dp)
                )


                Spacer(modifier = Modifier.padding(2.dp))

            LazyColumn(){

                items(items = cardList){ cardWeather ->

                    AddingCityCard(cardWeather = cardWeather, navController = navController,  searchViewModel = searchViewModel)

                }
            }

            }

        }
    }
}



@Composable
fun AddingCityCard(cardWeather: CardWeatherFinal?, navController: NavController,  searchViewModel: HomeViewModel) {

    val context = LocalContext.current

    val archive = SwipeAction(
        onSwipe = {
            searchViewModel.deleteCardDataBase(cardWeather)

            //Using of Custom dialog : ( if u want to use before deleting )
//    val openDialog = remember { mutableStateOf(true) }
//    if (openDialog.value) {
//        CustomDialog(
//            onConfirmClicked = { searchViewModel.deleteCardDataBase(cardWeather)},
//            onDismiss ={openDialog.value = false}  )
//    }

        },
        icon ={ Icon(imageVector = Icons.Filled.Delete,
            contentDescription = null,
            modifier = Modifier
                .padding(16.dp)
                .height(50.dp)
                .width(55.dp),
            tint = Color.White ) },
        background = Color.Red
    )

    SwipeableActionsBox(endActions = listOf(archive), swipeThreshold = 50.dp) {

        Card (
            modifier = Modifier
                .height(120.dp)
                .fillMaxWidth()
                .padding(8.dp)
                .clickable {
                    searchViewModel.saveData(context, cardWeather)
                    navController.navigate(route = BottomNavigationScreen.Home.route)
                    Toast
                        .makeText(context, "${cardWeather?.name}", Toast.LENGTH_SHORT)
                        .show()
                },
            shape = RoundedCornerShape(40.dp),
            elevation = 10.dp

        ){
            Row(
                modifier = Modifier.padding(20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = "${cardWeather?.name}",
                    fontSize = 25.sp,
                    fontStyle = FontStyle.Normal,
                    color = colorResource(id = R.color.close_black),
                    modifier = Modifier.weight(1f)
                )

                Text(
                    modifier = Modifier.padding(end = 20.dp),
                    text = "${cardWeather?.temp_c}\u00B0",
                    fontSize = 35.sp,
                    fontStyle = FontStyle.Normal,
                    color = colorResource(id = R.color.close_black)
                )

                val painter = rememberAsyncImagePainter(
                    model = "https:${cardWeather?.icon}"
                )
                val imageState = painter.state
                Image(
                    painter = painter,
                    contentDescription = "icon state",
                    modifier = Modifier
                        .width(40.dp)
                        .height(40.dp)
                )
                if (imageState is AsyncImagePainter.State.Loading){
                    CircularProgressIndicator()
                }
            }
        }
    }
}


//Dialog alert message for deleting :
@Composable
fun CustomDialog(
    onConfirmClicked: () -> Unit,
    onDismiss: () -> Unit,
) {
    Dialog(
        onDismissRequest = onDismiss,
    ) {
        Surface(
            shape = MaterialTheme.shapes.medium,
            color = MaterialTheme.colors.surface,
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                // TITLE
                Text(text = "Delete", style = MaterialTheme.typography.subtitle1)

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .verticalScroll(rememberScrollState())
                        .weight(weight = 1f, fill = false)
                        .padding(vertical = 16.dp)
                ) {
                    Text(
                        text = "Are you sure?",
                        style = MaterialTheme.typography.body2
                    )
                }

                // BUTTONS
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                    TextButton(onClick = onDismiss) {
                        Text(text = "Cancel")
                    }
                    TextButton(onClick = onConfirmClicked) {
                        Text(text = "OK")
                    }
                }
            }
        }
    }
}
