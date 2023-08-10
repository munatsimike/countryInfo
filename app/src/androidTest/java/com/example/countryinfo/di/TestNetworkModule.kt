package com.example.countryinfo.di

import com.example.countryinfo.data.remote.api.CountryAPiService
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.mockwebserver.MockWebServer
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TestNetworkModule {

    @Singleton
    @Provides
    fun provideMockWebServer(): MockWebServer = MockWebServer()

    @Provides
    @Named("testApiCountryService")
    fun provideCountryApiService(moshi: Moshi,  mockWebserver: MockWebServer): CountryAPiService {
        val retrofit = Retrofit.Builder()
            .baseUrl(mockWebserver.url("/"))
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

        return retrofit.create(CountryAPiService::class.java)
    }
}