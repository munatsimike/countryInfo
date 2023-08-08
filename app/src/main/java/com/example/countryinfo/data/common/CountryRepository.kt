package com.example.countryinfo.data.common

import com.example.countryinfo.domain.model.Country
import com.example.countryinfo.data.remote.repo.MyResponse

interface CountryRepository {
    suspend fun getAllCountries(): MyResponse<List<Country>>
    suspend fun searchByCountryName(countryName: String): MyResponse<List<Country>>
    suspend fun searchByCountryCode(countryCode: Int): MyResponse<List<Country>>
    suspend fun searchByCountryCurrency(countryCurrency: String): MyResponse<List<Country>>
    suspend fun searchByCapital(capitalInfo: String): MyResponse<List<Country>>
    suspend fun searchByCountryRegion(countryRegion: String): MyResponse<List<Country>>
    suspend fun saveCountriesToLocalDB(countryList: List<Country>)
}