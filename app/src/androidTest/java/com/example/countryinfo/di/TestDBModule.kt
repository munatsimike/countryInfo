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
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class TestDBModule {

    @Provides
    @Singleton
    @Named("test_db")
    fun provideTestDB(@ApplicationContext context: Context): AppLocalDatabase {
        return Room.databaseBuilder(context, AppLocalDatabase::class.java, "test_db")
            .allowMainThreadQueries().build()
    }


}