package com.example.countryinfo.data.remote.api

import com.example.countryinfo.domain.model.Country
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
        val response: Response<List<Country>> =
            httpResponse(jsonResponse()) { countryAPiService.getAllCountries() }
        assertThat(response.isSuccessful).isTrue()
        assertThat(response.body()).isNotEmpty()
        assertThat(response.body()?.get(0)?.name?.common).isEqualTo("Germany")
    }

    @Test
    fun should_Not_Crash_if_List_of_Countries_is_Empty() {
        val response = httpResponse("[]") { countryAPiService.getAllCountries() }
        assertThat(response.isSuccessful).isTrue()
        assertThat(response.body()).isEmpty()
    }

    // return a list of countries from the web server
    private

    fun httpResponse(
        jsonResponse: String, httpCall: suspend () -> Response<List<Country>>
    ): Response<List<Country>> {
        mockWebServer.enqueue(MockResponse().setResponseCode(200).setBody(jsonResponse))
        return runBlocking { httpCall() }
    }

    // a json object to imitate the actual json object from the webserver
    private fun jsonResponse(): String {
        return """[{"name":{"common":"Germany","official":"Federal Republic of Germany","nativeName":{"deu":{"official":"Bundesrepublik Deutschland","common":"Deutschland"}}},"tld":[".de"],"cca2":"DE","ccn3":"276","cca3":"DEU","cioc":"GER","independent":true,"status":"officially-assigned","unMember":true,"currencies":{"EUR":{"name":"Euro","symbol":"€"}},"idd":{"root":"+4","suffixes":["9"]},"capital":["Berlin"],"altSpellings":["DE","Federal Republic of Germany","Bundesrepublik Deutschland"],"region":"Europe","subregion":"Western Europe","languages":{"deu":"German"},"translations":{"ara":{"official":"جمهورية ألمانيا الاتحادية","common":"ألمانيا"},"bre":{"official":"Republik Kevreadel Alamagn","common":"Alamagn"},"ces":{"official":"Spolková republika Německo","common":"Německo"},"cym":{"official":"Federal Republic of Germany","common":"Germany"},"deu":{"official":"Bundesrepublik Deutschland","common":"Deutschland"},"est":{"official":"Saksamaa Liitvabariik","common":"Saksamaa"},"fin":{"official":"Saksan liittotasavalta","common":"Saksa"},"fra":{"official":"République fédérale d'Allemagne","common":"Allemagne"},"hrv":{"official":"Njemačka Federativna Republika","common":"Njemačka"},"hun":{"official":"Német Szövetségi Köztársaság","common":"Németország"},"ita":{"official":"Repubblica federale di Germania","common":"Germania"},"jpn":{"official":"ドイツ連邦共和国","common":"ドイツ"},"kor":{"official":"독일 연방 공화국","common":"독일"},"nld":{"official":"Bondsrepubliek Duitsland","common":"Duitsland"},"per":{"official":"جمهوری فدرال آلمان","common":"آلمان"},"pol":{"official":"Republika Federalna Niemiec","common":"Niemcy"},"por":{"official":"República Federal da Alemanha","common":"Alemanha"},"rus":{"official":"Федеративная Республика Германия","common":"Германия"},"slk":{"official":"Nemecká spolková republika","common":"Nemecko"},"spa":{"official":"República Federal de Alemania","common":"Alemania"},"srp":{"official":"Савезна Република Немачка","common":"Немачка"},"swe":{"official":"Förbundsrepubliken Tyskland","common":"Tyskland"},"tur":{"official":"Almanya Federal Cumhuriyeti","common":"Almanya"},"urd":{"official":"وفاقی جمہوریہ جرمنی","common":"جرمنی"},"zho":{"official":"德意志联邦共和国","common":"德国"}},"latlng":[51.0,9.0],"landlocked":false,"borders":["AUT","BEL","CZE","DNK","FRA","LUX","NLD","POL","CHE"],"area":357114.0,"demonyms":{"eng":{"f":"German","m":"German"},"fra":{"f":"Allemande","m":"Allemand"}},"flag":"\uD83C\uDDE9\uD83C\uDDEA","maps":{"googleMaps":"https://goo.gl/maps/mD9FBMq1nvXUBrkv6","openStreetMaps":"https://www.openstreetmap.org/relation/51477"},"population":83240525,"gini":{"2016":31.9},"fifa":"GER","car":{"signs":["DY"],"side":"right"},"timezones":["UTC+01:00"],"continents":["Europe"],"flags":{"png":"https://flagcdn.com/w320/de.png","svg":"https://flagcdn.com/de.svg","alt":"The flag of Germany is composed of three equal horizontal bands of black, red and gold."},"coatOfArms":{"png":"https://mainfacts.com/media/images/coats_of_arms/de.png","svg":"https://mainfacts.com/media/images/coats_of_arms/de.svg"},"startOfWeek":"monday","capitalInfo":{"latlng":[52.52,13.4]},"postalCode":{"format":"#####","regex":"^(\\d{5})$"}}]"""
    }
}
