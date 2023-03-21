package com.example.weatherapp.ui.search

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.weatherapp.R
import com.example.weatherapp.model.CardWeatherFinal
import com.example.weatherapp.ui.home.viewmodel.HomeViewModel
import com.example.weatherapp.ui.navigation.BottomNavigationScreen
import com.example.weatherapp.utils.Constants



@Composable
fun LetsSearch(navController: NavController, searchViewModel: HomeViewModel) {

    val context = LocalContext.current

    val searchView =  searchViewModel.customLocationDetails.collectAsState()
    val searchViewCurrent =  searchViewModel.customCurrentDetails.collectAsState()

    val textSearch = remember {
        mutableStateOf("")
    }


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.weathersearch),
            contentDescription = "State Weather",
            modifier = Modifier.padding(32.dp),
            contentScale = ContentScale.Fit
        )

        Spacer(modifier = Modifier.padding(top = 50.dp))

        OutlinedTextField(
            value = textSearch.value,
            onValueChange = {
                textSearch.value = it
                Log.d("TAG", "LetsSearch:${it} ")
                searchViewModel.getLocationDetailsFinally(Constants.API_KEY,it,"no")
                searchViewModel.getCurrentDetailsFinally(Constants.API_KEY,it,"no")
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                )
            },
            placeholder = {
                Text(text = stringResource(id = R.string.search_start))
            },
            modifier = Modifier
                .height(70.dp)
                .padding(8.dp),
            shape = RoundedCornerShape(32.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Black,
                unfocusedBorderColor = Color.Black
            )
        )
        Spacer(modifier = Modifier.padding(top = 50.dp))

        Button(
            colors = ButtonDefaults.buttonColors(colorResource(id = R.color.strong_blue)),
            modifier = Modifier.width(160.dp).height(45.dp),
            onClick = {

                searchView.value
                searchViewModel.addCardDataBase(
                    CardWeatherFinal(
                        searchView.value?.country ?: "",
                        searchView.value?.name ?:"",
                        searchView.value?.localtime ?:"",
                        searchViewCurrent.value?.temp_c ?:0.0,
                        searchViewCurrent.value?.condition?.text ?:"",
                        searchViewCurrent.value?.condition?.icon ?:"",
                        searchViewCurrent.value?.wind_kph ?:0.0,
                        searchViewCurrent.value?.pressure_mb ?:0.0,
                        searchViewCurrent.value?.cloud ?:0.0,
                        searchViewCurrent.value?.humidity ?:0.0
                    )
                )

                searchViewModel.saveData(context = context,
                    weatherFinal = CardWeatherFinal(
                        searchView.value?.country ?: "",
                        searchView.value?.name ?:"",
                        searchView.value?.localtime ?:"",
                        searchViewCurrent.value?.temp_c ?:0.0,
                        searchViewCurrent.value?.condition?.text ?:"",
                        searchViewCurrent.value?.condition?.icon ?:"",
                        searchViewCurrent.value?.wind_kph ?:0.0,
                        searchViewCurrent.value?.pressure_mb ?:0.0,
                        searchViewCurrent.value?.cloud ?:0.0,
                        searchViewCurrent.value?.humidity ?:0.0
                    )
                )

                navController.navigate(route = BottomNavigationScreen.Home.route)

            }
        ) {
            Text(text = "Search", color = colorResource(id = R.color.white), fontSize = 18.sp)

        }
    }

}

