package com.example.edvora_rides.repository

import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import com.bumptech.glide.Glide
import com.example.edvora_rides.model.Rides
import com.example.edvora_rides.model.User
import com.example.edvora_rides.network.RidesApi
import com.example.edvora_rides.network.RidesRetrofitClientInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class RidesRepository {

    fun getUser(): MutableLiveData<User?>? {
        val mutableLiveData: MutableLiveData<User?> = MutableLiveData<User?>()

        val ridesApi: RidesApi = RidesRetrofitClientInstance.getRetrofitInstance()
            ?.create(RidesApi::class.java)!!
        val call: Call<User> = ridesApi.getUserDetails()
        call.enqueue(object : Callback<User?> {
            override fun onResponse(call: Call<User?>, response: Response<User?>) {
                mutableLiveData.value = response.body()
            }
            override fun onFailure(call: Call<User?>, t: Throwable) {
                t.message
            }
        })
        return mutableLiveData
    }

    fun getRides(): MutableLiveData<List<Rides?>> {
        val mutableLiveData: MutableLiveData<List<Rides?>> = MutableLiveData<List<Rides?>>()

        val ridesApi: RidesApi = RidesRetrofitClientInstance.getRetrofitInstance()
            ?.create(RidesApi::class.java)!!
        val call: Call<List<Rides>> = ridesApi.getAvailableRides()
        call.enqueue(object : Callback<List<Rides>> {
            override fun onResponse(call: Call<List<Rides>>, response: Response<List<Rides>>) {
                mutableLiveData.value = response.body()
            }
            override fun onFailure(call: Call<List<Rides>>, t: Throwable) {
                t.message
            }
        })
        return mutableLiveData
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getCurrentDate(): String {
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")
        val formatted = current.format(formatter)
        return formatted
    }
}
