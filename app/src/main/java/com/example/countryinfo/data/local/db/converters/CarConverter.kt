package com.example.countryinfo.data.local.db.converters

import androidx.room.TypeConverter
import com.example.countryinfo.domain.model.countryDetails.nativeLanguage.Car
import com.google.gson.Gson

class CarConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromCarToString(car: Car): String {
        return gson.toJson(car)
    }

    @TypeConverter
    fun fromStringToCar(car: String): Car {
        return gson.fromJson(car, Car::class.java)
    }
}