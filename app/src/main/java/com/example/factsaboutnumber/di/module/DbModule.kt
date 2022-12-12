package com.example.factsaboutnumber.di.module

import android.content.Context
import com.example.factsaboutnumber.db.NumberDatabase
import com.example.factsaboutnumber.db.dao.NumberDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DbModule {

    @Provides
    @Singleton
    fun provideDatabase(
        context: Context
    ): NumberDatabase {
        return NumberDatabase.getInstance(context)
    }

    @Provides
    @Singleton
    fun provideNUmberDao(
        database: NumberDatabase
    ): NumberDao {
        return database.numberDao
    }
}