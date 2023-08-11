package com.example.countryinfo.data.local.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.countryinfo.data.local.db.CountryDao
import com.example.countryinfo.data.remote.repo.MyResponse
import com.example.countryinfo.domain.model.Country
import com.example.countryinfo.util.extensions.NetworkUtility.jsonToCountryList
import com.example.countryinfo.util.test.TestUtilities.jsonEuropeanCountries
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidTest
import io.mockk.coEvery
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@HiltAndroidTest
class LocalCountryRepositoryImpTest {

    private lateinit var mockKCountryDao: CountryDao
    private lateinit var localCountryRepositoryImp: LocalCountryRepositoryImp
    private lateinit var data: List<Country>

    @Before
    fun setUp() {
        data = jsonEuropeanCountries().jsonToCountryList()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun should_save_fetch_countries_to_room() {
        mockKCountryDao = mockk()
        localCountryRepositoryImp = LocalCountryRepositoryImp(mockKCountryDao)

        val mutableLiveDataResponse = MutableLiveData(data)
        val liveDataResponse: LiveData<List<Country>> = mutableLiveDataResponse

        coEvery { mockKCountryDao.getAllCountries() } returns liveDataResponse
        coEvery { localCountryRepositoryImp.saveCountriesToLocalDB(data) } just runs

        runTest {
            localCountryRepositoryImp.saveCountriesToLocalDB(data)
            val result2 = localCountryRepositoryImp.getAllCountries()

            assertThat(result2).isInstanceOf(MyResponse.Success::class.java)
            val response = (result2 as MyResponse.Success).data
            assertThat(response).isEqualTo(data)
        }
    }
}
