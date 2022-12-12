package com.example.factsaboutnumber.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NumberInfo(

    @PrimaryKey
    val id: Int,

)