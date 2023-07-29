package com.example.countryinfo.di

import android.content.Context
import androidx.room.Room
import com.example.countryinfo.data.local.db.AppLocalDatabase
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
    fun provideAppDb(@ApplicationContext context: Context): AppLocalDatabase =
        Room.databaseBuilder(context = context, AppLocalDatabase::class.java, "country_db").build()
}