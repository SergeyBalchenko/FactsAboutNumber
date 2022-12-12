package com.example.factsaboutnumber.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface NumberApi {

    @GET("{id}")
    suspend fun getInfo(
        @Path("id") number: Int
    ): Response<Unit>

    @GET("random/math")
    suspend fun getRandomInfo(): Response<Unit>
}