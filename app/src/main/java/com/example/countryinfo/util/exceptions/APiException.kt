package com.example.countryinfo.util.exceptions

import com.example.countryinfo.data.remote.api.ApiErrorInfo

class APiException(errorInfo: ApiErrorInfo) : Exception() {
}