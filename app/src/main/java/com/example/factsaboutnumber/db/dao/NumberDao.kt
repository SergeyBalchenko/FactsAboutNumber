package com.example.factsaboutnumber.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.factsaboutnumber.db.entities.NumberInfo
import kotlinx.coroutines.flow.Flow

@Dao
interface NumberDao {

    @Query("SELECT * FROM NumberInfo")
    fun getNumberInfo(): Flow<List<NumberInfo>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun putNumberInfo(numberInfo: NumberInfo)
}