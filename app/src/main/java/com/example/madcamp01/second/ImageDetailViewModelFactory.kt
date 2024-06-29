package com.example.madcamp01.second

import ImageDetailViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.madcamp01.DB.Repository.DataRepository

class ImageDetailViewModelFactory(
    private val repository: DataRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ImageDetailViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ImageDetailViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
