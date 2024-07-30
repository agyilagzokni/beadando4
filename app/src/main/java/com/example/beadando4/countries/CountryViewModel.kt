package com.example.beadando4.countries

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class CountryViewModel : ViewModel() {

    private val _countries = MutableLiveData<List<Country>>()

    val countries: LiveData<List<Country>> = _countries

    init {
        getCountryNames()
    }

    private fun getCountryNames() {
        viewModelScope.launch {
            _countries.value = CountryApi.retrofitService.getCountries()
                .filter { country -> country.unMember }
                .sortedBy { it.name.name }
        }
    }


}