package com.example.countryinfo.data.local.db.converters

import androidx.room.TypeConverter
import com.example.countryinfo.domain.model.countryDetails.Flags
import com.google.gson.Gson

class FlagsConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromFlagsToString(flags: Flags): String {
        return gson.toJson(flags)
    }

    @TypeConverter
    fun fromStringToFlags(str: String): Flags {
        return gson.fromJson(str, Flags::class.java)
    }
}