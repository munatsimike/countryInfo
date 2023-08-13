package com.example.countryinfo.data.local.repo

import androidx.lifecycle.LiveData
import com.example.countryinfo.data.local.db.CountryDao
import com.example.countryinfo.domain.model.Country
import javax.inject.Inject

class LocalCountryRepositoryImp @Inject constructor(private val countryDao: CountryDao) :
    LocalCountryRepository {

    override suspend fun saveCountriesFromApiToLocalDb(countryList: List<Country>) {
        countryDao.saveCountriesFromAPi(countryList)
    }

    override fun getAllCountriesFromLocalDb(): LiveData<List<Country>> =
        countryDao.getAllCountries()
}