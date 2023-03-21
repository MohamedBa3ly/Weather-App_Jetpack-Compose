package com.example.weatherapp.ui.home


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import com.example.weatherapp.R
import com.example.weatherapp.model.CardWeatherFinal
import com.example.weatherapp.ui.navigation.BottomNavigationScreen



//City name and date in home Screen :
@Composable
fun CityNameAndDate( lastSearch:CardWeatherFinal?) {

Column(
    modifier = Modifier
        .fillMaxWidth()
        .padding(24.dp),
    horizontalAlignment = Alignment.CenterHorizontally
) {
    Text(
        text = "${lastSearch?.name}, ${lastSearch?.country}",
        fontSize = 32.sp,
        fontStyle = FontStyle.Normal,
        color = Color.White,
    )

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Today, ",
            fontSize = 18.sp,
            fontStyle = FontStyle.Normal,
            color = Color.White
        )

        Text(
            text = "${lastSearch?.localtime}",
            fontSize = 18.sp,
            fontStyle = FontStyle.Normal,
            color = Color.White
        )
    }
}
}

//Icon And Temp in home Screen :
@Composable
fun IconAndTemp(lastSearch:CardWeatherFinal?) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
        ) {

        Text(
            text = "${lastSearch?.text} ",
            fontSize = 20.sp,
            fontStyle = FontStyle.Normal,
            color = Color.White,
            modifier = Modifier.padding(top = 20.dp, end = 10.dp, bottom = 5.dp)
        )

        //Coil image :
        val painter = rememberAsyncImagePainter(
            model = "https:${lastSearch?.icon}"
        )
        val imageState = painter.state
        Image(
            painter = painter,
            contentDescription = "Icon weather state",
            modifier = Modifier
                .width(80.dp)
                .height(80.dp)
                .padding(end = 10.dp),
            contentScale = ContentScale.Crop

        )
        if (imageState is AsyncImagePainter.State.Loading){
            CircularProgressIndicator()
        }

        Text(
            modifier = Modifier.padding(start = 15.dp),
            text = "${lastSearch?.temp_c}\u00B0",
            fontSize = 80.sp,
            fontStyle = FontStyle.Normal,
            color = Color.White
        )

    }
}


//Result in home Screen :
@Composable
fun Result( lastSearch:CardWeatherFinal?) {

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .padding(4.dp),
            horizontalArrangement = Arrangement.Center

        ) {
            Box(modifier = Modifier
                .width(200.dp)
                .height(120.dp) ){
                Image(
                    painter = painterResource(id = R.drawable.recback),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(10.dp)
                        .width(190.dp)
                        .height(100.dp)
                )
                Column (
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                        ){
                    Text(text = "Wind",
                        fontSize = 23.sp,
                        fontStyle = FontStyle.Normal,
                        color = colorResource(id = R.color.close_black),
                        modifier = Modifier.padding(top = 13.dp)
                    )

                    Row(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.wind),
                            contentDescription = "Wind",
                            modifier = Modifier
                                .padding(start = 20.dp, top = 11.dp)
                                .width(40.dp)
                                .height(40.dp)
                        )

                        Text(
                            text = "${lastSearch?.wind_kph} km/h",
                            fontSize = 23.sp,
                            fontStyle = FontStyle.Normal,
                            color = colorResource(id = R.color.close_black),
                            modifier = Modifier.padding(start = 2.dp, top = 12.dp)
                        )
                    }
                }
            }

            Box(modifier = Modifier
                .width(200.dp)
                .height(120.dp) ){
                Image(
                    painter = painterResource(id = R.drawable.recback),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(10.dp)
                        .width(190.dp)
                        .height(100.dp)
                )
                Column (
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Text(text = "Pressure",
                        fontSize = 23.sp,
                        fontStyle = FontStyle.Normal,
                        color = colorResource(id = R.color.close_black),
                        modifier = Modifier.padding(top = 13.dp)
                    )

                    Row(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.pressure),
                            contentDescription = "Pressure",
                            modifier = Modifier
                                .padding(start = 20.dp, top = 11.dp)
                                .width(40.dp)
                                .height(40.dp)
                        )

                        Text(
                            text = "${lastSearch?.pressure_mb} hpa",
                            fontSize = 23.sp,
                            fontStyle = FontStyle.Normal,
                            color = colorResource(id = R.color.close_black),
                            modifier = Modifier.padding(start = 2.dp, top = 12.dp)
                        )
                    }
                }

            }

        }

        Row(
            modifier = Modifier
                .padding(4.dp),
            horizontalArrangement = Arrangement.Center

        ) {
            Box(modifier = Modifier
                .width(200.dp)
                .height(120.dp) ){
                Image(
                    painter = painterResource(id = R.drawable.recback),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(10.dp)
                        .width(190.dp)
                        .height(100.dp)
                )

                Column (
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Text(text = "Clouds",
                        fontSize = 23.sp,
                        fontStyle = FontStyle.Normal,
                        color = colorResource(id = R.color.close_black),
                        modifier = Modifier.padding(top = 13.dp)
                    )

                    Row(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.clouds),
                            contentDescription = "Clouds",
                            modifier = Modifier
                                .padding(start = 20.dp, top = 11.dp)
                                .width(40.dp)
                                .height(40.dp)
                        )

                        Text(
                            text = "${lastSearch?.cloud} %",
                            fontSize = 23.sp,
                            fontStyle = FontStyle.Normal,
                            color = colorResource(id = R.color.close_black),
                            modifier = Modifier.padding(start = 5.dp, top = 12.dp)
                        )
                    }
                }
            }

            Box(modifier = Modifier
                .width(200.dp)
                .height(120.dp) ){
                Image(
                    painter = painterResource(id = R.drawable.recback),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(10.dp)
                        .width(190.dp)
                        .height(100.dp)
                )

                Column (
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Text(text = "Humidity",
                        fontSize = 23.sp,
                        fontStyle = FontStyle.Normal,
                        color = colorResource(id = R.color.close_black),
                        modifier = Modifier.padding(top = 13.dp)
                    )

                    Row(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.humidity),
                            contentDescription = "Humidity",
                            modifier = Modifier
                                .padding(start = 20.dp, top = 11.dp)
                                .width(40.dp)
                                .height(40.dp)
                        )

                        Text(
                            text = "${lastSearch?.humidity} %",
                            fontSize = 23.sp,
                            fontStyle = FontStyle.Normal,
                            color = colorResource(id = R.color.close_black),
                            modifier = Modifier.padding(start = 5.dp, top = 12.dp)
                        )
                    }
                }
            }

        }

    }

}


//Final Home in home Screen :
@Composable
fun FinalHome(lastSearch: CardWeatherFinal?, navController: NavController) {

    var showMenu by remember {
        mutableStateOf(false)
    }

    Scaffold (
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Home")
                },
                elevation = AppBarDefaults.TopAppBarElevation,
                backgroundColor = colorResource(id = R.color.baby_blue),
                contentColor = colorResource(id = R.color.white),
                actions = {

                    IconButton(onClick = { showMenu = !showMenu}){
                        Icon(Icons.Filled.Menu, contentDescription = null , tint = colorResource(id = R.color.white) )
                    }

                    DropdownMenu(expanded = showMenu , onDismissRequest = { showMenu = false }) {
                        DropdownMenuItem(onClick = { /*TODO*/ }) {
                            Text(text = "Favourite")
                        }

                        DropdownMenuItem(onClick = { /*TODO*/ }) {
                            Text(text = "Help")
                        }

                    }
                },
                navigationIcon = {
                IconButton(onClick = { navController.navigate(BottomNavigationScreen.Add.route){
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
            modifier = Modifier.padding(it)
        ) {
            Image(
                painter = painterResource(id =R.drawable.back ),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(4.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                if(lastSearch?.text?.isNotEmpty() == true){
                    CityNameAndDate(lastSearch)
                    IconAndTemp(lastSearch)
                    Spacer(modifier = Modifier.padding(20.dp))
                    Result(lastSearch)
                }else{
                    Text(text = " Data Not Found", textAlign = TextAlign.Center, fontSize = 30.sp,
                    color = colorResource(id = R.color.white))
                }


            }
        }

    }




}