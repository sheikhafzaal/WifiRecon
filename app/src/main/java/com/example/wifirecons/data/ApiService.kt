package com.example.wifirecons.data

import com.example.wifirecons.room.models.ReconData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiService {

    @GET
    fun fetchWifiListByLatLong(
        @Url url: String
    ): Call<ReconData>

}