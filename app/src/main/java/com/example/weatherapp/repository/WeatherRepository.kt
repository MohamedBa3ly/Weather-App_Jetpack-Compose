package com.example.weatherapp.repository


import android.content.Context
import androidx.lifecycle.LiveData
import com.example.weatherapp.model.CardWeatherFinal
import com.example.weatherapp.model.ConditionDetails
import com.example.weatherapp.model.CurrentDetails
import com.example.weatherapp.model.LocationDetails
import com.example.weatherapp.utils.State
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {

    suspend fun addConditionDetails(cardWeatherFinal: CardWeatherFinal)
    fun readAllConditionDetails() : Flow<List<CardWeatherFinal>>
    suspend fun deleteConditionDetails(cardWeatherFinal: CardWeatherFinal?)

    //Data Store for save and read :
    suspend fun saveData(context: Context, weatherFinal: CardWeatherFinal?)
    suspend fun readData(context: Context): Flow<CardWeatherFinal?>


    suspend fun getLocationDetails(apiKey: String,cityName: String?, no: String): Flow<State<LocationDetails>>
    suspend fun getCurrentDetails(apiKey: String,cityName: String?, no: String): Flow<State<CurrentDetails>>
}