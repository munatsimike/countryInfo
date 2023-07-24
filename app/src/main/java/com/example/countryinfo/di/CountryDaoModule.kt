package com.example.countryinfo.di

import com.example.countryinfo.data.local.dao.CountryDao
import com.example.countryinfo.data.local.db.AppLocalDatabase
import dagger.Provides
import dagger.hilt.InstallIn
import javax.inject.Singleton

@InstallIn(Singleton::class)
object CountryDaoModule {
    @Provides
    fun provideCountryDao(appLocalDatabase: AppLocalDatabase): CountryDao =
        appLocalDatabase.countryDao
}