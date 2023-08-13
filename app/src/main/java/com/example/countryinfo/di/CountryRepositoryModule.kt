package com.example.countryinfo.di

import com.example.countryinfo.data.local.db.CountryDao
import com.example.countryinfo.data.local.repo.LocalCountryRepository
import com.example.countryinfo.data.local.repo.LocalCountryRepositoryImp
import com.example.countryinfo.data.remote.api.CountryAPiService
import com.example.countryinfo.data.remote.repo.RemoteCountryRepository
import com.example.countryinfo.data.remote.repo.RemoteCountryRepositoryImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object CountryRepositoryModule {
    @Provides
    fun provideLocalRepository(countryDao: CountryDao): LocalCountryRepository =
        LocalCountryRepositoryImp(countryDao)

    @Provides
    fun provideRemoteRepository(
        countryAPiService: CountryAPiService,
        localRepository: LocalCountryRepository
    ): RemoteCountryRepository = RemoteCountryRepositoryImp(countryAPiService, localRepository)
}