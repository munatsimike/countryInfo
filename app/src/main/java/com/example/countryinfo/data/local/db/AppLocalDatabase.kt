package com.example.countryinfo.data.local.db

import androidx.room.RoomDatabase
import com.example.countryinfo.data.local.dao.CountryDao

abstract class  AppLocalDatabase: RoomDatabase() {
    abstract val countryDao: CountryDao
}