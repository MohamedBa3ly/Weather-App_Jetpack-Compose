package com.example.weatherapp.model


import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize


@JsonClass(generateAdapter = true)
data class ApiDetailsResponse(
    var location: LocationDetails,
    var current: CurrentDetails
)



data class LocationDetails(
    val name: String,
    val country: String?,
    val localtime: String?,
    )


data class CurrentDetails(
    val temp_c: Double,
    var condition: ConditionDetails,
    val wind_kph: Double?,
    val pressure_mb: Double?,
    val cloud: Double?,
    val humidity: Double?
)

data class ConditionDetails(
    val text: String?,
    val icon: String?
)


@Entity(tableName = "weather_table")
@Parcelize
data class CardWeatherFinal(
    val country: String?,
    @PrimaryKey val name: String,
    val localtime: String?,
    val temp_c: Double?,
    val text: String?,
    val icon: String?,
    val wind_kph: Double?,
    val pressure_mb: Double?,
    val cloud: Double?,
    val humidity: Double?
):Parcelable