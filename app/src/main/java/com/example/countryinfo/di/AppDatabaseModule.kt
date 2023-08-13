package com.example.countryinfo.di

import android.content.Context
import androidx.room.Room
import com.example.countryinfo.data.local.db.AppLocalDatabase
import com.example.countryinfo.data.local.db.CountryDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppDatabaseModule {
    @Provides
    @Singleton
    fun provideAppDb(@ApplicationContext context: Context): AppLocalDatabase {
      return  Room.databaseBuilder(context = context, AppLocalDatabase::class.java, "country_db").build()
    }
    @Provides
    fun provideCountryDao(appLocalDatabase: AppLocalDatabase): CountryDao =
        appLocalDatabase.countryDao

}