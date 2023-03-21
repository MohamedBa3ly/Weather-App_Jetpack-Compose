package com.example.weatherapp.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.weatherapp.model.CardWeatherFinal
import com.example.weatherapp.model.CurrentDetails
import com.example.weatherapp.model.LocationDetails


@Database(entities = [CardWeatherFinal::class], version = 1, exportSchema = false)
abstract class WeatherDataBase: RoomDatabase() {

    abstract fun weatherDao():WeatherDao

    companion object{
        @Volatile
        private var Instance : WeatherDataBase?=null
        fun getInstance(context: Context):WeatherDataBase{
            val tempInstance = Instance
            if(tempInstance!=null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(context.applicationContext,
                    WeatherDataBase::class.java,
                    "Weather_DataBase").build()
                Instance = instance
                return instance
            }
        }
    }
}