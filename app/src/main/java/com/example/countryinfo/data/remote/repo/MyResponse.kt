package com.example.countryinfo.data.remote.repo

import com.example.countryinfo.data.remote.api.ApiErrorInfo
import com.example.countryinfo.domain.model.Country

sealed class MyResponse<out T> {
    data class Success(val data: List<Country>) : MyResponse<List<Country>>()
    data class Failure(val error: ApiErrorInfo) : MyResponse<Nothing>()
}