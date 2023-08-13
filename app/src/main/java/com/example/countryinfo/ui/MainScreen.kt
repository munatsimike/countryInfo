package com.example.countryinfo.ui

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.countryinfo.ui.viewmodel.CountryViewModel
import com.example.countryinfo.util.general.MyError

object MainScreen {
    @Composable
    fun DisplayCountries(countryViewModel: CountryViewModel = hiltViewModel()) {
        val countries = countryViewModel.countries.collectAsState()

        LazyColumn {
            when (val data = countries.value) {
                is ViewState.Success -> {
                    data.data.forEach { country ->
                        item {
                            Text(text = country.name?.common ?: "No name")
                        }
                    }
                }

                is ViewState.Failure -> {
                    item { Text(text = data.error.toString()) }
                }

                is ViewState.Loading ->{
                    item { Text(text = "Loading") }
                }
            }
        }
    }
}