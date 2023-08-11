package com.example.countryinfo.data.local.repo

import com.example.countryinfo.data.common.CountryRepository
import com.example.countryinfo.data.local.db.CountryDao
import com.example.countryinfo.data.remote.api.Constants.UNKNOWN_ERROR_MESSAGE
import com.example.countryinfo.data.remote.repo.MyResponse
import com.example.countryinfo.domain.model.Country
import com.example.countryinfo.util.general.MyError
import javax.inject.Inject

class LocalCountryRepositoryImp @Inject constructor(private val countryDao: CountryDao) :
    CountryRepository {
    override suspend fun getAllCountries(): MyResponse<List<Country>> {
        return try {
            MyResponse.Success(countryDao.getAllCountries().value!!)
        } catch (e: Exception) {
            MyResponse.Failure(
                MyError.OtherError(e.message ?: UNKNOWN_ERROR_MESSAGE)
            )
        }
    }

    override suspend fun saveCountriesToLocalDB(countryList: List<Country>) {
        for (country in countryList) countryDao.saveCountriesFromAPi(country)
    }
}