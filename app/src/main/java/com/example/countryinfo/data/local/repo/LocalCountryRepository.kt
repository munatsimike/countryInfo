package com.example.countryinfo.data.local.repo

import androidx.lifecycle.LiveData
import com.example.countryinfo.data.common.CountryRepository
import com.example.countryinfo.domain.model.Country

interface LocalCountryRepository:CountryRepository {
    fun getAllCountriesFromLocalDb(): LiveData<List<Country>>
}