package com.example.weatherapp.repository


import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.doublePreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.LiveData
import com.example.weatherapp.model.CardWeatherFinal
import com.example.weatherapp.model.ConditionDetails
import com.example.weatherapp.model.CurrentDetails
import com.example.weatherapp.model.LocationDetails
import com.example.weatherapp.network.WeatherApiService
import com.example.weatherapp.room.WeatherDao
import com.example.weatherapp.utils.State
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject
//Data store will use to store weather's city :
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("Selected")

class WeatherRepositoryImpl @Inject constructor(
private val weatherDao: WeatherDao,
private val api: WeatherApiService
): WeatherRepository {

    override suspend fun addConditionDetails(cardWeatherFinal: CardWeatherFinal) {
        weatherDao.addCardWeatherFinal(cardWeatherFinal)
    }

    override fun readAllConditionDetails(): Flow<List<CardWeatherFinal>> {
        return weatherDao.readAllCardWeatherFinal()
    }

    override suspend fun deleteConditionDetails(cardWeatherFinal: CardWeatherFinal?) {
        weatherDao.deleteCardWeatherFinal(cardWeatherFinal)
    }


    override suspend fun getLocationDetails(
        apiKey: String,
        cityName: String?,
        no: String
    ): Flow<State<LocationDetails>> {
        return flow {
            emit(State.Loading)
            val result = api.weatherState(apiKey,cityName, no)
            if (result.isSuccessful) {
                emit(State.Success(result.body()?.location))
            } else {
                emit(State.Error("Failed to found weather state"))
            }
        }
    }

    override suspend fun getCurrentDetails(
        apiKey: String,
        cityName: String?,
        no: String
    ): Flow<State<CurrentDetails>> {
        return flow {
            emit(State.Loading)
            val result = api.weatherState(apiKey,cityName, no)
            if (result.isSuccessful) {
                emit(State.Success(result.body()?.current))
            } else {
                emit(State.Error("Failed to found weather state"))
            }
        }
    }

    //Fun to save profile data by Data Store :
    override suspend fun saveData(context:Context,weatherFinal: CardWeatherFinal?) {
        context.dataStore.edit {
            it[stringPreferencesKey("country")] = weatherFinal?.country ?:"Country"
            it[stringPreferencesKey("name")] = weatherFinal?.name ?:"Name"
            it[stringPreferencesKey("time")] = weatherFinal?.localtime ?: "Time"
            it[doublePreferencesKey("temp")] = (weatherFinal?.temp_c ?: "Temp") as Double
            it[stringPreferencesKey("text")] = weatherFinal?.text ?: "Text"
            it[stringPreferencesKey("icon")] = weatherFinal?.icon ?: "Icon"
            it[doublePreferencesKey("wind")] = (weatherFinal?.wind_kph ?: "Wind") as Double
            it[doublePreferencesKey("pressure")] = (weatherFinal?.pressure_mb ?: "Pressure") as Double
            it[doublePreferencesKey("cloud")] = (weatherFinal?.cloud ?: "Cloud") as Double
            it[doublePreferencesKey("humidity")] = (weatherFinal?.humidity ?: "Humidity") as Double
        }
    }

    //Fun to read profile data by Data Store :
    override suspend fun readData(context: Context) = context.dataStore.data.map {
        CardWeatherFinal(
            country = it[stringPreferencesKey("country")],
            name = it[stringPreferencesKey("name")]!!,
            localtime = it[stringPreferencesKey("time")],
            temp_c = it[doublePreferencesKey("temp")],
            text = it[stringPreferencesKey("text")],
            icon = it[stringPreferencesKey("icon")],
            wind_kph = it[doublePreferencesKey("wind")],
            pressure_mb = it[doublePreferencesKey("pressure")],
            cloud = it[doublePreferencesKey("cloud")],
            humidity = it[doublePreferencesKey("humidity")]
        )
    }



}