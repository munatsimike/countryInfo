package com.example.countryinfo.data.local.db

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.countryinfo.data.local.repo.LocalCountryRepositoryImp
import com.example.countryinfo.util.extensions.NetworkUtility.jsonToCountryList
import com.example.countryinfo.util.test.TestUtilities.jsonEuropeanCountries
import com.example.countryinfo.util.test.getOrAwaitValue
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named

@HiltAndroidTest
class CountryDaoTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    lateinit var localRepository: LocalCountryRepositoryImp

    @Inject
    @Named("test_db")
    lateinit var localDB: AppLocalDatabase

    @Inject
    lateinit var countryDao: CountryDao

    @Before
    fun setup() {
        hiltRule.inject()

    }

    @After
    fun tearDown() {
        localDB.close()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun should_Read_And_Write_To_DB() {
        val countries = jsonEuropeanCountries().jsonToCountryList()

        var result = countryDao.getAllCountries().getOrAwaitValue()
        assertThat(result.size).isEqualTo(0)

        runTest {
            for (country in countries) {
                countryDao.saveCountriesFromAPi(country)
            }
        }
        result = countryDao.getAllCountries().getOrAwaitValue()

        assertThat(result.size).isGreaterThan(0)

    }
}