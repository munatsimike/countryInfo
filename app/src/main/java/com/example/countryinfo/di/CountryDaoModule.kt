package com.example.countryinfo.di

import com.example.countryinfo.data.local.dao.CountryDao
import com.example.countryinfo.data.local.db.AppLocalDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CountryDaoModule {
    @Provides
    fun provideCountryDao(appLocalDatabase: AppLocalDatabase): CountryDao =
        appLocalDatabase.countryDao
}