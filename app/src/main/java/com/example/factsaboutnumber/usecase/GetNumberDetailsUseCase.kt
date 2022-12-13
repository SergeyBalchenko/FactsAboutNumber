package com.example.factsaboutnumber.usecase

import com.example.factsaboutnumber.db.entities.NumberInfo
import com.example.factsaboutnumber.repository.NumberRepository

class GetNumberDetailsUseCase(
    private val numberRepository: NumberRepository
) {

    suspend fun invoke(number: Int): Result<NumberInfo> {
        return numberRepository.getNumberDetails(number)
    }
}