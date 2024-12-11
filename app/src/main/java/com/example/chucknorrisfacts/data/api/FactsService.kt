package com.example.chucknorrisfacts.data.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface FactsService {

    @GET("categories")
    suspend fun getCategories(): Response<List<String>>

    @GET("random")
    suspend fun getRandomByCategory(@Query("category") category: String):
            Response<FactResponse>

}