package com.example.countryinfo.data.local.db.converters

import androidx.room.TypeConverter
import com.example.countryinfo.domain.model.countryDetails.CoatOfArms
import com.google.gson.Gson

class CoatOfArmsConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromCoatOfArmsToString(coatOfArms: CoatOfArms): String {
        return gson.toJson(coatOfArms)
    }

    @TypeConverter
    fun fromStringToCoatOfArms(coatOfArms: String): CoatOfArms {
        return gson.fromJson(coatOfArms, CoatOfArms::class.java)
    }
}