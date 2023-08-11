package com.example.countryinfo.data.local.db.converters

import androidx.room.TypeConverter
import com.example.countryinfo.domain.model.countryDetails.nativeLanguage.Idd
import com.google.gson.Gson

class IddConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromIddToString(idd: Idd): String {
        return gson.toJson(idd)
    }

    @TypeConverter
    fun fromStringToIdd(idd: String): Idd {
        return gson.fromJson(idd, Idd::class.java)
    }
}