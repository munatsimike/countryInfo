package com.example.countryinfo.data.common

import com.example.countryinfo.domain.model.Country
import com.example.countryinfo.data.remote.repo.MyResponse

interface CountryRepository {
    suspend fun getAllCountries(): MyResponse<List<Country>>
    suspend fun saveCountriesToLocalDB(countryList: List<Country>)
}