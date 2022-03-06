package com.example.edvora_rides.repository

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.bumptech.glide.Glide
import com.example.edvora_rides.model.Rides
import com.example.edvora_rides.model.User
import com.example.edvora_rides.network.RidesApi
import com.example.edvora_rides.network.RidesRetrofitClientInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RidesRepository {


    fun getUser(): MutableLiveData<User?>? {
        val mutableLiveData: MutableLiveData<User?> = MutableLiveData<User?>()

        val ridesApi: RidesApi = RidesRetrofitClientInstance.getRetrofitInstance()
            ?.create(RidesApi::class.java) !!
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
            ?.create(RidesApi::class.java) !!
        val call: Call<List<Rides>> = ridesApi.getAvailableRides()
        call.enqueue(object : Callback<List<Rides>> {
            override fun onResponse(call: Call<List<Rides>>, response: Response<List<Rides>>) {
                Log.d("API CALL", "STATUS 250")
                mutableLiveData.value = response.body()
            }

            override fun onFailure(call: Call<List<Rides>>, t: Throwable) {
                t.message

            }

        })

        return mutableLiveData
    }}
