package com.example.countryinfo.data.remote.repo

import com.example.countryinfo.data.common.CountryRepository
import com.example.countryinfo.data.common.MockRepositoryImp
import com.example.countryinfo.data.remote.api.CountryAPiService
import com.example.countryinfo.domain.model.Country
import com.example.countryinfo.util.extensions.NetworkUtility.jsonToCountryList
import com.example.countryinfo.util.test.TestUtilities
import com.example.countryinfo.util.test.TestUtilities.jsonCountryColumbia
import com.example.countryinfo.util.test.TestUtilities.jsonCountryEstonia
import com.example.countryinfo.util.test.TestUtilities.jsonCountryPeru
import com.example.countryinfo.util.test.TestUtilities.jsonEuropeanCountries
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class RemoteCountryRepositoryImpTest {
    private lateinit var mockCountryApiService: CountryAPiService
    private lateinit var remoteCountryRepository: CountryRepository
    private lateinit var mockLocalRepositoryImp: MockRepositoryImp

    @Before
    fun setup() {
        mockCountryApiService = mockk()
        mockLocalRepositoryImp = MockRepositoryImp()
        remoteCountryRepository =
            RemoteCountryRepositoryImp(mockCountryApiService, mockLocalRepositoryImp)
    }

    @Test
    fun should_Return_A_List_Of_Countries() {
        val testCountriesList = jsonEuropeanCountries().jsonToCountryList()
        val countriesList = getCountryList(
            { mockCountryApiService.getAllCountries() },
            { remoteCountryRepository.getAllCountries() },
            testCountriesList
        )
        assertThat(countriesList.isNotEmpty()).isTrue()
        assertThat(countriesList).isEqualTo(testCountriesList)
    }

    @Test
    fun should_search_countries_by_currency() {
        val testCountry = jsonCountryPeru().jsonToCountryList()
        val countryList = getCountryList(
            { mockCountryApiService.searchByCountryCurrency("peruvian") },
            { remoteCountryRepository.searchByCountryCurrency("peruvian") },
            testCountry
        )

        assertThat(countryList).isNotEmpty()
        assertThat(countryList.all { it.currencies.PEN?.name == "Peruvian sol" }).isTrue()
    }

    @Test
    fun should_search_countries_by_country_name() {
        val testCountry = TestUtilities.jsonCountryGerman().jsonToCountryList()
        val countryList = getCountryList(
            { mockCountryApiService.searchByCountryName("Deutschland") },
            { remoteCountryRepository.searchByCountryName("Deutschland") },
            testCountry
        )

        assertThat(countryList).isNotEmpty()
        assertThat(countryList[0].name.common).isEqualTo("Germany")
    }


    @Test
    fun should_search_countries_by_country_code() {
        val testCountry = jsonCountryColumbia().jsonToCountryList()
        val country = getCountryList(
            { mockCountryApiService.searchByCountryCode("170") },
            { remoteCountryRepository.searchByCountryCode(170) },
            testCountry
        )

        assertThat(country).isNotNull()
        assertThat(country[0].ccn3 == "170").isTrue()
    }

    @Test
    fun should_search_countries_by_region() {
        val country = getCountryList(
            { mockCountryApiService.searchByCountryRegion("Europe") },
            { remoteCountryRepository.searchByCountryRegion("Europe") },
            jsonEuropeanCountries().jsonToCountryList()
        )

        assertThat(country).isNotNull()
        assertThat(country.all { it.region == "Europe" }).isTrue()
    }

    @Test
    fun should_search_countries_by_CapitalInfo() {
        val country = getCountryList(
            { mockCountryApiService.searchByCapital("Tallinn") },
            { remoteCountryRepository.searchByCapital("Tallinn") },
            jsonCountryEstonia().jsonToCountryList()
        )

        assertThat(country).isNotNull()
        assertThat(country[0].capital.contains("Tallinn")).isTrue()
    }

    private fun getCountryList(
        mockAPiCall: suspend () -> Response<List<Country>>,
        repositoryCall: suspend () -> MyResponse<List<Country>>,
        countryList: List<Country>
    ): List<Country> {
        coEvery { mockAPiCall.invoke() } returns Response.success(
            countryList
        )

        val result = runBlocking { repositoryCall.invoke() }
        require(result is MyResponse.Success)
        return result.data
    }
}
