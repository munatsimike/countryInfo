package com.example.countryinfo.util.test

import com.example.countryinfo.data.remote.repo.RemoteCountryRepository
import com.example.countryinfo.domain.model.Country
import com.example.countryinfo.util.extensions.NetworkUtility.jsonToCountryList
import com.example.countryinfo.util.test.TestUtilities.jsonEuropeanCountries

class MockRemoteRepository : RemoteCountryRepository {
    private val mockLocalRepository = MockLocalRepository()

    override suspend fun getAllCountriesFromApi(): List<Country> {
        return jsonEuropeanCountries().jsonToCountryList()
    }

    override suspend fun refreshDataFromApi() {
        saveCountriesFromApiToLocalDb(getAllCountriesFromApi())
    }

    override suspend fun saveCountriesFromApiToLocalDb(countryList: List<Country>) {
        mockLocalRepository.saveCountriesFromApiToLocalDb(countryList)
    }
}