package com.example.edvora_rides.repository

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.bumptech.glide.Glide
import com.example.edvora_rides.model.User
import com.example.edvora_rides.network.RidesApi
import com.example.edvora_rides.network.RidesRetrofitClientInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RidesRepository {


    fun getNearestRides(): MutableLiveData<User?>? {
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
}