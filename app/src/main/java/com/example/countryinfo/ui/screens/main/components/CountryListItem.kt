package com.example.countryinfo.ui.screens.main.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.countryinfo.domain.model.Country

@Composable
fun CountryListItem(country: Country){
    Text(text = country.name?.common ?: "No name")
}