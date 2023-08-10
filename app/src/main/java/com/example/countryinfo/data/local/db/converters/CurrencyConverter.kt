package com.example.countryinfo.data.local.db.converters

import androidx.room.TypeConverter
import com.example.countryinfo.domain.model.countryDetails.currency.Currency
import com.google.gson.Gson

class CurrencyConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromCurrencyToString(currency: Currency): String {
        return gson.toJson(currency)
    }

    @TypeConverter
    fun fromStringToCurrency(currency: String): Currency {
        return gson.fromJson(currency, Currency::class.java)
    }
}