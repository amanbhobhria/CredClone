package com.example.credassingment.api

import com.example.credassingment.model.ItemResponse



class Repository(private val apiService: ApiService) {
    suspend fun fetchItems(): ItemResponse {
        return apiService.getItems()  // This returns ItemResponse
    }
}
