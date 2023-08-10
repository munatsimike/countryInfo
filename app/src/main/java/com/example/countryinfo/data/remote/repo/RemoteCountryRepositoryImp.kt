package com.example.countryinfo.data.remote.repo

import com.example.countryinfo.data.common.CountryRepository
import com.example.countryinfo.data.remote.api.ApiErrorInfo
import com.example.countryinfo.data.remote.api.CountryAPiService
import com.example.countryinfo.domain.model.Country
import com.example.countryinfo.util.exceptions.APiException
import retrofit2.Response
import javax.inject.Inject

class RemoteCountryRepositoryImp @Inject constructor(
    private val countryAPiService: CountryAPiService, private val localRepository: CountryRepository
) : CountryRepository {


    suspend fun refreshDataFromApi() {
        when (val response = getAllCountries()) {
            is MyResponse.Success -> {
                saveCountriesToLocalDB(response.data)
            }

            is MyResponse.Failure -> {
                throw APiException(response.error)
            }

            else -> {}
        }
    }

    override suspend fun getAllCountries(): MyResponse<List<Country>> {
        return executeApiCall { countryAPiService.getAllCountries() }
    }

     suspend fun searchByCountryName(countryName: String): MyResponse<List<Country>> {
        return executeApiCall { countryAPiService.searchByCountryName(countryName) }
    }

     suspend fun searchByCountryCode(countryCode: Int): MyResponse<List<Country>> {
        return executeApiCall { countryAPiService.searchByCountryCode(countryCode.toString()) }
    }

     suspend fun searchByCountryCurrency(countryCurrency: String): MyResponse<List<Country>> {
        return executeApiCall { countryAPiService.searchByCountryCurrency(countryCurrency) }
    }

     suspend fun searchByCapital(capitalInfo: String): MyResponse<List<Country>> {
        return executeApiCall { countryAPiService.searchByCapital(capitalInfo) }
    }

     suspend fun searchByCountryRegion(countryRegion: String): MyResponse<List<Country>> {
        return executeApiCall { countryAPiService.searchByCountryRegion(countryRegion) }
    }

    override suspend fun saveCountriesToLocalDB(countryList: List<Country>) {
        localRepository.saveCountriesToLocalDB(countryList)
    }

    private suspend fun executeApiCall(apiCall: suspend () -> Response<List<Country>>): MyResponse<List<Country>> {
        return try {
            val result = apiCall.invoke()

            if (result.isSuccessful) {
                MyResponse.Success(validateResponse(result))
            } else {
                MyResponse.Failure(ApiErrorInfo(code = result.code(), error = result.message()))
            }

            // catch any other exceptions if any
        } catch (e: Exception) {
            MyResponse.Failure(ApiErrorInfo(code = 500, e.message ?: "An unknown error occurred"))
        }
    }

    // if api response body is null return an empty list else return response body
    private fun validateResponse(
        apiResponse: Response<List<Country>>
    ): List<Country> {
        return apiResponse.body().takeIf { it != null } ?: emptyList()
    }

}