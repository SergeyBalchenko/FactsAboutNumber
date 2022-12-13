package com.example.factsaboutnumber.usecase

import com.example.factsaboutnumber.repository.NumberRepository

class SearchNumberInfoUseCase(
    private val numberRepository: NumberRepository
) {

    suspend fun invoke(number: Int? = null): Result<Unit> {
        // TODO: add some verification of input number
        return numberRepository.search(number)
    }
}