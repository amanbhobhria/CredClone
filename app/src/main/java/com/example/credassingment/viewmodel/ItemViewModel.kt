package com.example.credassingment.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.credassingment.api.Repository
import com.example.credassingment.model.Item
import kotlinx.coroutines.launch

class ItemViewModel(private val repository: Repository) : ViewModel() {

    private val _items = MutableLiveData<List<Item>>()
    val items: LiveData<List<Item>> get() = _items

    init {
        fetchItems()
    }

    private fun fetchItems() {
        viewModelScope.launch {
            try {
                Log.d("MainActivity1", "Fetching items...")
                val response = repository.fetchItems()  // This should return ItemResponse
                _items.value = response.items  // Set the list of items
            } catch (e: Exception) {
                Log.e("MainActivity1", "Error fetching items: ${e.message}")
                Log.e("MainActivity1", "Error fetching items: ${e.toString()}")
                // Handle error
            }
        }
    }

}
