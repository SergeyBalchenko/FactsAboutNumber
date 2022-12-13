package com.example.factsaboutnumber.network

import com.example.factsaboutnumber.network.model.NumberResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface NumberApi {

    @GET("{id}")
    @Headers("Content-Type: application/json")
    suspend fun getInfo(
        @Path("id") number: Int
    ): Response<NumberResponse>

    @GET("random/math")
    @Headers("Content-Type: application/json")
    suspend fun getRandomInfo(): Response<NumberResponse>
}