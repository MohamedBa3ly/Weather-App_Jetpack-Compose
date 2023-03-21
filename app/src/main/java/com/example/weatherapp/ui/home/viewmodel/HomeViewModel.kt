package com.example.weatherapp.ui.home.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.*
import com.example.weatherapp.model.*
import com.example.weatherapp.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
     private val repository: WeatherRepository
): ViewModel() {

    private var _customLocationDetails : MutableStateFlow<LocationDetails?> = MutableStateFlow(null)
    var customLocationDetails: StateFlow<LocationDetails?> = _customLocationDetails.asStateFlow()

    private var _customCurrentDetails : MutableStateFlow<CurrentDetails?> = MutableStateFlow(null)
    var customCurrentDetails: StateFlow<CurrentDetails?> = _customCurrentDetails.asStateFlow()


//    private var _customX : MutableList<CardWeatherFinal?> = mutableListOf()
//    val customX : List<CardWeatherFinal?> = _customX
//
//    fun addX(cardWeather: CardWeatherFinal?) {
//         _customX.add(cardWeather)
//    }

    //Selected :
//    private var _selectedX : MutableState<CardWeatherFinal?>  = mutableStateOf(null)
//    val selectedX : State<CardWeatherFinal?> = _selectedX
//
//
//
//    fun onSelected(cardWeather: CardWeatherFinal?) {
//        _selectedX.value = cardWeather
//    }


    //Database :
    private var _customDataBase : MutableStateFlow<List<CardWeatherFinal?>> = MutableStateFlow(emptyList())
    var customDataBase: StateFlow<List<CardWeatherFinal?>> = _customDataBase.asStateFlow()

    //Selected data base :
    private var _selectedCustomDataBase : MutableStateFlow<CardWeatherFinal?> = MutableStateFlow(null)
    var selectedCustomDataBase: StateFlow<CardWeatherFinal?> = _selectedCustomDataBase.asStateFlow()



    //Fun to get location details:
    fun getLocationDetailsFinally(apiKey: String, cityName: String?, no: String){
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.getLocationDetails(apiKey,cityName, no)
            result.collect{
                _customLocationDetails.value = it.toData()
            }
        }
    }

    //Fun to get current details:
    fun getCurrentDetailsFinally(apiKey: String, cityName: String?, no: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.getCurrentDetails(apiKey,cityName, no)
            result.collect{
                _customCurrentDetails.value = it.toData()
            }
        }
    }

    fun addCardDataBase(cardWeather: CardWeatherFinal){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addConditionDetails(cardWeather)
        }
    }

    fun readCardDataBase() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.readAllConditionDetails().collect{
                Log.d("TAGGG", "Adding222: $it")
                _customDataBase.value = it
            }
        }
    }

    fun deleteCardDataBase(cardWeather: CardWeatherFinal?) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteConditionDetails(cardWeather)
        }
    }

    //Data Store :
    //Fun to save data by Data Store :
    fun saveData(context: Context,weatherFinal: CardWeatherFinal? ){
        viewModelScope.launch(Dispatchers.IO) {
            repository.saveData(context,weatherFinal)
        }
    }

    //Fun to read data by Data Store :
    fun readData(context: Context){
        viewModelScope.launch(Dispatchers.IO) {
            repository.readData(context).collect{
                _selectedCustomDataBase.value = it
            }
        }
    }

}