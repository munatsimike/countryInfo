package com.example.countryinfo.util.exceptions

import com.example.countryinfo.util.general.MyError

class APiException(errorInfo: MyError) : Exception() {
}