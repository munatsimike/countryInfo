package com.example.countryinfo.data.local.db.converters

import androidx.room.TypeConverter
import com.example.countryinfo.domain.model.countryDetails.Translations
import com.google.gson.Gson

class TranslationsConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromTranslationsToString(translations: Translations): String {
        return gson.toJson(translations)
    }

    @TypeConverter
    fun fromStringToTranslations(str: String): Translations {
        return gson.fromJson(str, Translations::class.java)
    }
}