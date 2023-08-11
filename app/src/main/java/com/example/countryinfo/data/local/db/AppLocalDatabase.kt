package com.example.countryinfo.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.countryinfo.data.local.db.converters.CapitalInfoConverter
import com.example.countryinfo.data.local.db.converters.CarConverter
import com.example.countryinfo.data.local.db.converters.CoatOfArmsConverter
import com.example.countryinfo.data.local.db.converters.CurrencyConverter
import com.example.countryinfo.data.local.db.converters.DemonymsConverter
import com.example.countryinfo.data.local.db.converters.FlagsConverter
import com.example.countryinfo.data.local.db.converters.GiniConverter
import com.example.countryinfo.data.local.db.converters.IddConverter
import com.example.countryinfo.data.local.db.converters.LanguageConverter
import com.example.countryinfo.data.local.db.converters.ListDoubleConverter
import com.example.countryinfo.data.local.db.converters.ListToStringConverter
import com.example.countryinfo.data.local.db.converters.MapsConverter
import com.example.countryinfo.data.local.db.converters.NameConverter
import com.example.countryinfo.data.local.db.converters.PostalCodeConverter
import com.example.countryinfo.data.local.db.converters.TranslationsConverter
import com.example.countryinfo.domain.model.Country

@Database(entities = [Country::class], version = 1)
@TypeConverters(
    CapitalInfoConverter::class,
    ListToStringConverter::class,
    CarConverter::class,
    CoatOfArmsConverter::class,
    DemonymsConverter::class,
    CurrencyConverter::class,
    FlagsConverter::class,
    GiniConverter::class,
    IddConverter::class,
    LanguageConverter::class,
    ListDoubleConverter::class,
    ListToStringConverter::class,
    MapsConverter::class,
    NameConverter::class,
    PostalCodeConverter::class,
    TranslationsConverter::class
)
abstract class AppLocalDatabase : RoomDatabase() {
    abstract val countryDao: CountryDao
}