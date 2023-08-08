package com.example.countryinfo.data.common

import com.example.countryinfo.data.remote.repo.MyResponse
import com.example.countryinfo.domain.model.Country
import com.google.gson.Gson

class MockRepositoryImp : CountryRepository {
    
    override suspend fun getAllCountries(): MyResponse<List<Country>> {
        TODO("Not yet implemented")
    }

    override suspend fun searchByCountryName(countryName: String): MyResponse<List<Country>> {
        TODO("Not yet implemented")
    }

    override suspend fun searchByCountryCode(countryCode: Int): MyResponse<List<Country>> {
        TODO("Not yet implemented")
    }

    override suspend fun searchByCountryCurrency(countryCurrency: String): MyResponse<List<Country>> {
        TODO("Not yet implemented")
    }

    override suspend fun searchByCapital(capitalInfo: String): MyResponse<List<Country>> {
        TODO("Not yet implemented")
    }

    override suspend fun searchByCountryRegion(countryRegion: String): MyResponse<List<Country>> {
        TODO("Not yet implemented")
    }

    override suspend fun saveCountriesToLocalDB(countryList: List<Country>) {
        TODO("Not yet implemented")
    }


}