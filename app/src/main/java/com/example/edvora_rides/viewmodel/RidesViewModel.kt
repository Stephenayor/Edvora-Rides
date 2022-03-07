package com.example.edvora_rides.viewmodel

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.edvora_rides.model.Rides
import com.example.edvora_rides.model.User
import com.example.edvora_rides.repository.RidesRepository
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class RidesViewModel(application: Application) : AndroidViewModel(application) {
    private var ridesRepository: RidesRepository? = null
    private var mutableLiveData: MutableLiveData<User?>? = null
    private var mutableLiveDataRides: MutableLiveData<List<Rides?>>? = null


    fun getUser(): LiveData<User?>? {
        ridesRepository = RidesRepository()
        if (mutableLiveData == null) {
            mutableLiveData = ridesRepository!!.getUser()
        }
        return mutableLiveData
    }

    fun getNearestRide(): MutableLiveData<List<Rides?>>? {
        ridesRepository = RidesRepository()
        if (mutableLiveDataRides == null) {
            mutableLiveDataRides = ridesRepository!!.getRides()
        }
        return mutableLiveDataRides
    }

    fun getUpcomingRide(): LiveData<List<Rides?>>? {
        ridesRepository = RidesRepository()
        if (mutableLiveDataRides == null) {
            mutableLiveDataRides = ridesRepository!!.getRides()
        }
        return mutableLiveDataRides
    }

    fun getPastRide(): LiveData<List<Rides?>>? {
        ridesRepository = RidesRepository()
        if (mutableLiveDataRides == null) {
            mutableLiveDataRides = ridesRepository!!.getRides()
        }
        return mutableLiveDataRides
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getCurrentDate(): String? {
       return ridesRepository?.getCurrentDate()
    }

}