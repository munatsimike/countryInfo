package com.example.countryinfo.data.local.db.converters

import androidx.room.TypeConverter
import com.example.countryinfo.domain.model.countryDetails.nativeLanguage.Demonyms
import com.google.gson.Gson

class DemonymsConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromDemonymsToString(demonyms: Demonyms): String {
        return gson.toJson(demonyms)
    }

    @TypeConverter
    fun fromStringToDemonyms(demonyms: String): Demonyms {
        return gson.fromJson(demonyms, Demonyms::class.java)
    }
}