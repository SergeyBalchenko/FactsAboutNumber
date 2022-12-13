package com.example.factsaboutnumber.repository

import com.example.factsaboutnumber.db.entities.NumberInfo
import kotlinx.coroutines.flow.Flow

interface NumberRepository {

    /**
     * subscribe to numberInfo updates
     */
    fun subscribeToNumberInfo(): Flow<List<NumberInfo>>

    /**
     * try to search and save locally
     * if numberInfo is null return random info result
     */
    suspend fun search(number: Int? = null): Result<Unit>

    /**
     * return info details by number
     */
    suspend fun getNumberDetails(number: Int): Result<NumberInfo>
}