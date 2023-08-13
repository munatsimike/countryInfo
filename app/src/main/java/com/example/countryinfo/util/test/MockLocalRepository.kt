package com.example.countryinfo.util.test

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.countryinfo.data.local.repo.LocalCountryRepository
import com.example.countryinfo.domain.model.Country
import com.example.countryinfo.util.extensions.NetworkUtility.jsonToCountryList
import com.example.countryinfo.util.test.TestUtilities.jsonEuropeanCountries

class MockLocalRepository : LocalCountryRepository {

    private val _countries = MutableLiveData(jsonEuropeanCountries().jsonToCountryList())
    private val countries: LiveData<List<Country>> = _countries

    override fun getAllCountriesFromLocalDb(): LiveData<List<Country>> {
        return countries
    }

    override suspend fun saveCountriesFromApiToLocalDb(countryList: List<Country>) {
        _countries.postValue(countryList)
    }
}