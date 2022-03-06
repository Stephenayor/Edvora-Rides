package com.example.edvora_rides.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.edvora_rides.model.User
import com.example.edvora_rides.repository.RidesRepository

class RidesViewModel(application: Application) : AndroidViewModel(application) {
    private var ridesRepository: RidesRepository? = null
    private var mutableLiveData: MutableLiveData<User?>? = null


    fun getNearestRides(): LiveData<User?>? {
        ridesRepository = RidesRepository()
        if (mutableLiveData == null) {
            mutableLiveData = ridesRepository!!.getNearestRides()
        }
        return mutableLiveData
    }

}