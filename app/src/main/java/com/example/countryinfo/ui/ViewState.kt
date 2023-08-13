package com.example.countryinfo.ui

import com.example.countryinfo.domain.model.Country
import com.example.countryinfo.util.general.MyError

sealed class ViewState<out T> {
    data class Success(val data: List<Country>) : ViewState<List<Country>>()
    data class Failure(val error: MyError) : ViewState<Nothing>()
    object Loading : ViewState<Nothing>()
}