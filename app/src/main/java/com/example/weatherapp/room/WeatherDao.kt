package com.example.weatherapp.room


import androidx.room.*
import com.example.weatherapp.model.CardWeatherFinal
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherDao {


    //To insert card weather final in data base :
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCardWeatherFinal(cardWeatherFinal: CardWeatherFinal)

    //To read all data for card weather final :
    @Query("SELECT * FROM weather_table")
    fun readAllCardWeatherFinal() : Flow<List<CardWeatherFinal>>

    //To delete card weather final in data base :
    @Delete
    suspend fun deleteCardWeatherFinal(cardWeatherFinal: CardWeatherFinal?)


}