package com.example.countryinfo.ui.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.countryinfo.data.local.repo.LocalCountryRepository
import com.example.countryinfo.data.remote.repo.RemoteCountryRepository
import com.example.countryinfo.domain.model.Country
import com.example.countryinfo.ui.ViewState
import com.example.countryinfo.ui.viewmodel.CountryViewModel
import com.example.countryinfo.util.extensions.NetworkUtility.jsonToCountryList
import com.example.countryinfo.util.test.MockLocalRepository
import com.example.countryinfo.util.test.MockRemoteRepository
import com.example.countryinfo.util.test.TestUtilities.jsonCountryGerman
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)

class CountryViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var countryViewModel: CountryViewModel
    private lateinit var localCountryRepositoryImp: LocalCountryRepository
    private lateinit var remoteCountryRepositoryImp: RemoteCountryRepository

    @Before
    fun setup() {
        localCountryRepositoryImp = MockLocalRepository()
        remoteCountryRepositoryImp = MockRemoteRepository()
        Dispatchers.setMain(Dispatchers.Unconfined)
        countryViewModel = CountryViewModel(localCountryRepositoryImp, remoteCountryRepositoryImp)
    }

    @Test
    fun should_Return_live_Data_from_localDb() {
        val result = countryViewModel.fetchCountriesFromLocalDb()
        assertThat(result).isNotNull()
        assertThat(result).isInstanceOf(LiveData::class.java)
    }

    @Test
    fun should_observe_livedata_success_state(){
        val data = MutableLiveData(jsonCountryGerman().jsonToCountryList())
        countryViewModel.observeLocalDbLiveData(data)
        assertThat(countryViewModel.countries.value).isEqualTo(ViewState.Success(data = data.value!!))
    }

    @Test
    fun should_observe_livedata_error_state(){
        val data = MutableLiveData<List<Country>>()
        data.value = null
        countryViewModel.observeLocalDbLiveData(data)
        assertThat(countryViewModel.countries.value).isInstanceOf(ViewState.Failure::class.java)
    }
}
