package com.example.weatherapp.network

import com.example.weatherapp.model.ApiDetailsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {
    @GET("v1/current.json")
    suspend fun weatherState(
        @Query("key") apiKey: String,
        @Query("q") cityName: String?,
        @Query("aqi") no: String
    ): Response<ApiDetailsResponse>
}