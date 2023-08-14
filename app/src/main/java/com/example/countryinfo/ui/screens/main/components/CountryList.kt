package com.example.countryinfo.ui.screens.main.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.countryinfo.domain.model.Country
import com.example.countryinfo.ui.ViewState
import com.example.countryinfo.ui.screens.components.ErrorContent
import com.example.countryinfo.ui.screens.components.LoadingContent

@Composable
fun CountryList(countries: ViewState<List<Country>>, modifier: Modifier = Modifier) {
    LazyColumn {
        item {
            when (countries) {
                is ViewState.Success -> {
                    countries.data.forEach { country ->
                        CountryListItem(country)
                    }
                }

                is ViewState.Failure -> {
                    ErrorContent(countries.error.toString())
                }

                is ViewState.Loading -> {
                    LoadingContent()
                }
            }
        }
    }
}