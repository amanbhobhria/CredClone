package com.example.credassingment.api

import com.example.credassingment.model.ItemResponse
import retrofit2.http.GET

interface ApiService {
    @GET("test_mint")
    suspend fun getItems(): ItemResponse
}