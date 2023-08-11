package com.example.countryinfo.data.local.db.converters

import androidx.room.TypeConverter
import com.example.countryinfo.domain.model.countryDetails.PostalCode
import com.google.gson.Gson

class PostalCodeConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromPostalCodeToString(postalCode: PostalCode): String {
        return gson.toJson(postalCode)
    }

    @TypeConverter
    fun fromStringToPostalCode(postalCode: String): PostalCode {
        return gson.fromJson(postalCode, PostalCode::class.java)
    }
}