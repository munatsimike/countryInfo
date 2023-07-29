package com.example.countryinfo.data.remote.api

import com.example.countryinfo.domain.model.Country
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CountryAPiService {
    @GET("All")
    suspend fun getAllCountries(): Response<List<Country>>

    @GET("name/{name}?fullText=true")
    suspend fun searchByCountryName(@Path("name") countryName: String): Response<List<Country>>

    @GET("alpha/{code}")
    suspend fun searchByCountryCode(@Path("code") countryCode: String): Response<List<Country>>

    @GET("currency/{currency}")
    suspend fun searchByCountryCurrency(@Path("currency") countryCurrency: String): Response<List<Country>>

    @GET("capital/{capital}")
    suspend fun searchByCapital(@Path("capital") capitalInfo: String): Response<List<Country>>

    @GET("region/{region}")
    suspend fun searchByCountryRegion(@Path("region") countryRegion: String): Response<List<Country>>

}