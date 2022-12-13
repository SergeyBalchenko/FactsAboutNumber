package com.example.factsaboutnumber.usecase

import com.example.factsaboutnumber.db.entities.NumberInfo
import com.example.factsaboutnumber.repository.NumberRepository
import kotlinx.coroutines.flow.Flow

class SubscribeToNumberInfoUseCase(
    private val numberRepository: NumberRepository
) {

    fun invoke(): Flow<List<NumberInfo>> {
        return numberRepository.subscribeToNumberInfo()
    }
}