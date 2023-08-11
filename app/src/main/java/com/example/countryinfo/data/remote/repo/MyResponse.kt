package com.example.countryinfo.data.remote.repo

import com.example.countryinfo.domain.model.Country
import com.example.countryinfo.util.general.MyError

sealed class MyResponse<out T> {
    data class Success(val data: List<Country>) : MyResponse<List<Country>>()
    data class Failure(val error: MyError) : MyResponse<Nothing>()
}