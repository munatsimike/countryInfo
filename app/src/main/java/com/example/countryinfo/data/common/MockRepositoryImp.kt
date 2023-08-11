package com.example.countryinfo.data.common

import com.example.countryinfo.data.remote.repo.MyResponse
import com.example.countryinfo.domain.model.Country

class MockRepositoryImp : CountryRepository {

    override suspend fun getAllCountries(): MyResponse<List<Country>> {
        TODO("Not yet implemented")
    }

    suspend fun searchByCountryName(countryName: String): MyResponse<List<Country>> {
        TODO("Not yet implemented")
    }

    suspend fun searchByCountryCode(countryCode: Int): MyResponse<List<Country>> {
        TODO("Not yet implemented")
    }

    suspend fun searchByCountryCurrency(countryCurrency: String): MyResponse<List<Country>> {
        TODO("Not yet implemented")
    }

    suspend fun searchByCapital(capitalInfo: String): MyResponse<List<Country>> {
        TODO("Not yet implemented")
    }

    suspend fun searchByCountryRegion(countryRegion: String): MyResponse<List<Country>> {
        TODO("Not yet implemented")
    }

    override suspend fun saveCountriesToLocalDB(countryList: List<Country>) {
        TODO("Not yet implemented")
    }


}