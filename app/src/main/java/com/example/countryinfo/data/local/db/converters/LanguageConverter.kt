package com.example.countryinfo.data.local.db.converters

import androidx.room.TypeConverter
import com.example.countryinfo.domain.model.countryDetails.Maps
import com.example.countryinfo.domain.model.countryDetails.nativeLanguage.Language
import com.google.gson.Gson

class LanguageConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromLanguageToString(language: Language): String {
        return gson.toJson(language)
    }

    @TypeConverter
    fun fromStringToLanguage(language: String): Language {
        return gson.fromJson(language, Language::class.java)
    }
}