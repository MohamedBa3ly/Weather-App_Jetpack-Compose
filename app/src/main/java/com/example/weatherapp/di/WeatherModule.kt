package com.example.weatherapp.di

import android.app.Application
import com.example.weatherapp.network.WeatherApiService
import com.example.weatherapp.room.WeatherDao
import com.example.weatherapp.room.WeatherDataBase
import com.example.weatherapp.utils.Constants
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object WeatherModule {

    @Provides
    @Singleton
    fun provideMyApi():WeatherApiService{
        return Retrofit.Builder()
            .addConverterFactory(
                MoshiConverterFactory.create(
                    Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
                )
            )
            .baseUrl(Constants.BASE_URL)
            .build()
            .create(WeatherApiService::class.java)
    }

    @Provides
    @Singleton
    fun getInstanceDB(context: Application): WeatherDataBase{
        return WeatherDataBase.getInstance(context)
    }

    @Provides
    @Singleton
    fun getMoviesDao(appDao: WeatherDataBase): WeatherDao{
        return appDao.weatherDao()
    }

}