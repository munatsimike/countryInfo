package com.example.countryinfo.data.remote.api

import com.example.countryinfo.domain.model.Country
import com.example.countryinfo.util.extensions.NetworkUtility.jsonToCountryList
import com.example.countryinfo.util.test.TestUtilities.jsonEuropeanCountries
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Named

@HiltAndroidTest
class CountryServiceTest {

    @get:Rule
    var hilt = HiltAndroidRule(this)

    @Inject
    @Named("testApiCountryService")
    lateinit var countryAPiService: CountryAPiService

    @Inject
    lateinit var mockWebServer: MockWebServer

    @Before
    fun setup() {
        hilt.inject()
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun success_APi_Call_Should_Return_A_List_of_Countries() {
        val testCountries = jsonEuropeanCountries()
        val response: Response<List<Country>> =
            httpResponse(testCountries) { countryAPiService.getAllCountries() }
        assertThat(response.isSuccessful).isTrue()
        assertThat(response.body()).isNotEmpty()
        assertThat(response.body()).isEqualTo(testCountries.jsonToCountryList())
    }

    @Test
    fun should_Not_Crash_if_List_of_Countries_is_Empty() {
        val response = httpResponse("[]") { countryAPiService.getAllCountries() }
        assertThat(response.isSuccessful).isTrue()
        assertThat(response.body()).isEmpty()
    }

    // return a list of countries from the web server
    private fun httpResponse(
        jsonResponse: String, httpCall: suspend () -> Response<List<Country>>
    ): Response<List<Country>> {
        mockWebServer.enqueue(
            MockResponse().setResponseCode(200).setBody(jsonResponse)
        )
        return runBlocking { httpCall() }
    }
}
