package com.example.edvora_rides.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class RidesViewModelFactory(

    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RidesViewModel::class.java)) {
            return RidesViewModel( application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}