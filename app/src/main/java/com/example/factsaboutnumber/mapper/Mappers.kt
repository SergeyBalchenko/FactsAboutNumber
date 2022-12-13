package com.example.factsaboutnumber.mapper

import com.example.factsaboutnumber.db.entities.NumberInfo
import com.example.factsaboutnumber.network.model.NumberResponse

fun NumberResponse.mapToInfo(number: Int): NumberInfo {
    return NumberInfo(
        number = number,
        text = text
    )
}