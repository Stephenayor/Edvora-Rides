package com.example.edvora_rides.model

import com.google.gson.annotations.SerializedName

data class User (
        @SerializedName("station_code" ) var stationCode : Int?    = null,
        @SerializedName("name"         ) var name        : String? = null,
        @SerializedName("url"          ) var url         : String? = null
)