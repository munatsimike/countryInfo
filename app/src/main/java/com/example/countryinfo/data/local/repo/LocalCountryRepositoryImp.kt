package com.example.countryinfo.data.local.repo

import com.example.countryinfo.data.common.CountryRepository
import com.example.countryinfo.data.remote.repo.MyResponse
import com.example.countryinfo.domain.model.Country

class LocalCountryRepositoryImp: CountryRepository{
    override suspend fun getAllCountries(): MyResponse<List<Country>> {
        TODO("Not yet implemented")
    }

    override suspend fun saveCountriesToLocalDB(countryList: List<Country>) {
        TODO("Not yet implemented")
    }
}