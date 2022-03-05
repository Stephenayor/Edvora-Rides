package com.example.edvora_rides.network

import com.example.edvora_rides.model.User
import retrofit2.Call
import retrofit2.http.GET

interface RidesApi {

    @GET("user")
    fun getUserDetails(): Call<User>
}
