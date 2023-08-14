package com.example.countryinfo.ui.screens.main.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.countryinfo.ui.viewmodel.CountryViewModel

@Composable
fun MainScreen(countryViewModel: CountryViewModel = hiltViewModel()) {
    val countries = countryViewModel.countries.collectAsState()
    CountryList(countries.value)
}
