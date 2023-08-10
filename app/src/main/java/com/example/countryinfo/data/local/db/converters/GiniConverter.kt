package com.example.countryinfo.data.local.db.converters

import androidx.room.TypeConverter
import com.example.countryinfo.domain.model.countryDetails.nativeLanguage.Car
import com.example.countryinfo.domain.model.countryDetails.nativeLanguage.Gini
import com.google.gson.Gson

class GiniConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromGiniToString(gini: Gini): String {
        return gson.toJson(gini)
    }

    @TypeConverter
    fun fromStringToGini(gini: String): Gini {
        return gson.fromJson(gini, Gini::class.java)
    }
}