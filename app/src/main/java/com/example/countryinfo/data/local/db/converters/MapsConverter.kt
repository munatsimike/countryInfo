package com.example.countryinfo.data.local.db.converters

import androidx.room.TypeConverter
import com.example.countryinfo.domain.model.countryDetails.Maps
import com.google.gson.Gson

class MapsConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromMapsToString(maps: Maps): String {
        return gson.toJson(maps)
    }

    @TypeConverter
    fun fromStringToMpas(maps: String): Maps {
        return gson.fromJson(maps, Maps::class.java)
    }
}