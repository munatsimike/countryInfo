package com.example.countryinfo.data.local.db.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class ListDoubleConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromListToString(doubles: List<Double>): String {
        return Json.encodeToString(doubles)
    }

    @TypeConverter
    fun fromStringToList(doubles: String): List<Double> {
        return Json.decodeFromString(doubles)
    }
}