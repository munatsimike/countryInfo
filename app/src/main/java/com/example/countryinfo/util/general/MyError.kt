package com.example.countryinfo.util.general

sealed class MyError {
    data class ApiErrorInfo(val errorCode: Int, val errorMessage: String) : MyError()
    data class OtherError(val errorMessage: String) : MyError()
}