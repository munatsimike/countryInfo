package com.example.countryinfo.data.remote.repo

import com.example.countryinfo.data.common.CountryRepository
import com.example.countryinfo.domain.model.Country

interface RemoteCountryRepository : CountryRepository {
    suspend fun getAllCountriesFromApi(): List<Country>
    suspend fun refreshDataFromApi()
}