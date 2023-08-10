package com.example.countryinfo.data.local.db.converters

import androidx.room.TypeConverter
import com.example.countryinfo.domain.model.countryDetails.CapitalInfo
import com.google.gson.Gson

class CapitalInfoConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromCapitalInfoToString(capitalInfo: CapitalInfo): String {
        return gson.toJson(capitalInfo)
    }

    @TypeConverter
    fun fromStringCapitalInfo(str: String): CapitalInfo {
        return gson.fromJson(str, CapitalInfo::class.java)
    }
}