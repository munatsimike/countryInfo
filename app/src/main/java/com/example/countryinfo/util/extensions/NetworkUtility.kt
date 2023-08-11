package com.example.countryinfo.util.extensions

import com.example.countryinfo.domain.model.Country
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object NetworkUtility {


    // check if a list of countries is json format
    private fun String.isJsonCountryList(): Boolean {
        return try {
            Gson().fromJson<List<Country>>(this, object : TypeToken<List<Country>>() {}.type)
            true
        } catch (e: Exception) {
            false
        }
    }

    // convert a list of countries from json to list
    fun String.jsonToCountryList(): List<Country> {
        if (!this.isJsonCountryList()) {
            throw IllegalArgumentException("provided string is not a valid json country list")
        }
        return Gson().fromJson(this, object : TypeToken<List<Country>>() {}.type)
    }
}