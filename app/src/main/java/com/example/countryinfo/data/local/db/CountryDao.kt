package com.example.countryinfo.data.local.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.countryinfo.domain.model.Country

@Dao
interface CountryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveCountriesFromAPi(countries: Country)

    @Query("SELECT * FROM country")
    fun getAllCountries(): LiveData<List<Country>>
}