package com.example.factsaboutnumber.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NumberInfo(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val number: Int,

    val text: String
)