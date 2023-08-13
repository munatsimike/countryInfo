package com.example.countryinfo.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import com.example.countryinfo.data.local.repo.LocalCountryRepository
import com.example.countryinfo.data.remote.repo.RemoteCountryRepository
import com.example.countryinfo.domain.model.Country
import com.example.countryinfo.ui.ViewState
import com.example.countryinfo.util.exceptions.APiException
import com.example.countryinfo.util.general.MyError
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountryViewModel @Inject constructor(
    private val localCountryRepositoryImp: LocalCountryRepository,
    private val remoteCountryRepositoryImp: RemoteCountryRepository
) : ViewModel() {

    private val _countries: MutableStateFlow<ViewState<List<Country>>> =
        MutableStateFlow(ViewState.Loading)
    val countries: StateFlow<ViewState<List<Country>>> = _countries

    init {
        refreshDataFromApi()
        observeLocalDbLiveData(fetchCountriesFromLocalDb())
    }

    fun fetchCountriesFromLocalDb(): LiveData<List<Country>> {
        return localCountryRepositoryImp.getAllCountriesFromLocalDb()
    }

    fun observeLocalDbLiveData(data: LiveData<List<Country>>) {
        viewModelScope.launch {
            data.asFlow()
                .map {
                ViewState.Success(it)
            }.catch {
               _countries.value = ViewState.Failure(handleLocalDbError(it))

            }.collect { successViewState ->
                _countries.value = successViewState
            }
        }
    }

    private fun handleLocalDbError(error: Throwable): MyError {
        return MyError.OtherError(error.message.toString())
    }

    private fun refreshDataFromApi() {
        viewModelScope.launch {
            try {
                remoteCountryRepositoryImp.refreshDataFromApi()
            } catch (apiError: APiException) {
                Log.e(apiError.message, "")
            } catch (e: Exception) {
                Log.e("", e.message.toString())
            }
        }
    }
}