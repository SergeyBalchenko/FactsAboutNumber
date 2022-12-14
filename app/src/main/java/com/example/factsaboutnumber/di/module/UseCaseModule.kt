package com.example.factsaboutnumber.di.module

import com.example.factsaboutnumber.repository.NumberRepository
import com.example.factsaboutnumber.usecase.GetNumberDetailsUseCase
import com.example.factsaboutnumber.usecase.SearchNumberInfoUseCase
import com.example.factsaboutnumber.usecase.SubscribeToNumberInfoUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UseCaseModule {

    @Provides
    @Singleton
    fun provideSearchNumberInfoUseCase(
        numberRepository: NumberRepository
    ): SearchNumberInfoUseCase {
        return SearchNumberInfoUseCase(
            numberRepository
        )
    }

    @Provides
    @Singleton
    fun provideSubscribeToNumberInfoUseCase(
        numberRepository: NumberRepository
    ): SubscribeToNumberInfoUseCase {
        return SubscribeToNumberInfoUseCase(
            numberRepository
        )
    }

    @Provides
    @Singleton
    fun provideGetNumberDetailsUseCase(
        numberRepository: NumberRepository
    ): GetNumberDetailsUseCase {
        return GetNumberDetailsUseCase(numberRepository)
    }
}