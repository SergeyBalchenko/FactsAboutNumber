package com.example.factsaboutnumber.network.model

/**
 * "text": "5 is the number of kyu (pupil) grades in judo.",
 * "number": 5,
 * "found": true,
 * "type": "trivia"
 */
data class NumberResponse(
    val text: String,
    val number: Int,
    val found: Boolean,
    val type: String
)