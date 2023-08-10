package com.example.countryinfo.data.local.db.converters

import androidx.room.TypeConverter
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class ListToStringConverter {
    @TypeConverter
    fun fromListToString(strList: List<String>): String {
        return Json.encodeToString(strList)
    }

    @TypeConverter
    fun fromStringToList(str: String): List<String> {
        return Json.decodeFromString(str)

    }
}