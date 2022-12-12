package com.example.factsaboutnumber.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.factsaboutnumber.db.dao.NumberDao
import com.example.factsaboutnumber.db.entities.NumberInfo

@Database(
    entities =[
        NumberInfo::class
    ],
    version = 1
)
abstract class NumberDatabase : RoomDatabase() {

    abstract val numberDao: NumberDao

    companion object {
        @Volatile
        private var INSTANCE: NumberDatabase? = null

        fun getInstance(context: Context): NumberDatabase {
            synchronized(this) {
                return INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    NumberDatabase::class.java,
                    "number_db"
                ).build().also {
                    INSTANCE = it
                }
            }
        }
    }
}