package com.example.countryinfo.data.local.db.converters

import androidx.room.TypeConverter
import com.example.countryinfo.domain.model.countryDetails.Name
import com.google.gson.Gson

class NameConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromNameToString(name: Name): String {
        return gson.toJson(name)
    }

    @TypeConverter
    fun fromStringToName(name: String): Name {
        return gson.fromJson(name, Name::class.java)
    }
}