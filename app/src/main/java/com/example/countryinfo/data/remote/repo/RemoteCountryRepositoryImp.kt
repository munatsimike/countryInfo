package com.example.countryinfo.data.remote.repo

import com.example.countryinfo.data.local.repo.LocalCountryRepository
import com.example.countryinfo.data.remote.api.CountryAPiService
import com.example.countryinfo.domain.model.Country
import com.example.countryinfo.util.exceptions.APiException
import com.example.countryinfo.util.general.MyError
import retrofit2.Response
import javax.inject.Inject

class RemoteCountryRepositoryImp @Inject constructor(
    private val countryAPiService: CountryAPiService,
    private val localRepository: LocalCountryRepository
) : RemoteCountryRepository {

    override suspend fun refreshDataFromApi() {
        try {
            saveCountriesFromApiToLocalDb(getAllCountriesFromApi())
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun getAllCountriesFromApi(): List<Country> {
        return executeApiCall { countryAPiService.getAllCountries() }
    }

    suspend fun searchByCountryName(countryName: String): List<Country> {
        return executeApiCall { countryAPiService.searchByCountryName(countryName) }
    }

    suspend fun searchByCountryCode(countryCode: Int): List<Country> {
        return executeApiCall { countryAPiService.searchByCountryCode(countryCode.toString()) }
    }

    suspend fun searchByCountryCurrency(countryCurrency: String): List<Country> {
        return executeApiCall { countryAPiService.searchByCountryCurrency(countryCurrency) }
    }

    suspend fun searchByCapital(capitalInfo: String): List<Country> {
        return executeApiCall { countryAPiService.searchByCapital(capitalInfo) }
    }

    suspend fun searchByCountryRegion(countryRegion: String): List<Country> {
        return executeApiCall { countryAPiService.searchByCountryRegion(countryRegion) }
    }

    override suspend fun saveCountriesFromApiToLocalDb(countryList: List<Country>) {
        localRepository.saveCountriesFromApiToLocalDb(countryList)
    }

    private suspend fun executeApiCall(apiCall: suspend () -> Response<List<Country>>): List<Country> {
        return try {
            val result = apiCall.invoke()

            if (result.isSuccessful) {
                validateResponse(result)
            } else {
                throw APiException(MyError.ApiErrorInfo(result.code(), result.message()))
            }

            // catch any other exceptions if any
        } catch (e: Exception) {
            throw e
        }
    }

    // if api response body is null return an empty list else return response body
    private fun validateResponse(
        apiResponse: Response<List<Country>>
    ): List<Country> {
        return apiResponse.body().takeIf { it != null } ?: emptyList()
    }

}