package com.example.factsaboutnumber.repository

import com.example.factsaboutnumber.db.dao.NumberDao
import com.example.factsaboutnumber.db.entities.NumberInfo
import com.example.factsaboutnumber.mapper.mapToInfo
import com.example.factsaboutnumber.network.NumberApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class NumberRepositoryRoom(
    private val numberApi: NumberApi,
    private val numberDao: NumberDao
) : NumberRepository {

    override fun subscribeToNumberInfo(): Flow<List<NumberInfo>> {
        return numberDao.subscribeToNumberInfo()
    }

    override suspend fun search(number: Int?): Result<Unit> =
        withContext(Dispatchers.IO) {
        try {
            val result = if (number != null) {
                numberApi.getInfo(number)
            } else {
                numberApi.getRandomInfo()
            }
            if (result.isSuccessful) {
                val numberResponse = result.body()!!
                numberDao.putNumberInfo(numberResponse.mapToInfo(number ?: 0)) // TODO: get random value if we search it
                return@withContext Result.success(Unit)
            } else {
                return@withContext Result.failure(Exception("Can't find a number info"))
            }
        } catch (e: Exception) {
            val ex = e
            return@withContext Result.failure(e)
        }
    }

    override suspend fun getNumberDetails(number: Int): Result<NumberInfo> =
        withContext(Dispatchers.IO) {
            try {
                val result = numberApi.getInfo(number)
                if (result.isSuccessful) {
                    val numberResponse = result.body()!!
                    return@withContext Result.success(numberResponse.mapToInfo(number))
                } else {
                    return@withContext Result.failure(Exception("Can't find a number info"))
                }
            } catch (e: Exception) {
                return@withContext Result.failure(e)
            }
        }
}