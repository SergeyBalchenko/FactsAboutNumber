package com.example.factsaboutnumber.di.module

import com.example.factsaboutnumber.db.dao.NumberDao
import com.example.factsaboutnumber.network.NumberApi
import com.example.factsaboutnumber.repository.NumberRepository
import com.example.factsaboutnumber.repository.NumberRepositoryRoom
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideNumberRepository(
        numberApi: NumberApi,
        numberDao: NumberDao
    ): NumberRepository {
        return NumberRepositoryRoom(
            numberApi,
            numberDao
        )
    }
}