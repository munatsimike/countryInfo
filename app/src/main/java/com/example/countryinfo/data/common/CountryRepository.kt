package com.example.countryinfo.data.common

import com.example.countryinfo.domain.model.Country

interface CountryRepository {
    suspend fun saveCountriesFromApiToLocalDb(countryList: List<Country>)
}