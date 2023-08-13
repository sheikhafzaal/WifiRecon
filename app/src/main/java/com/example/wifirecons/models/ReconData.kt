package com.example.wifirecons.models

import com.google.gson.annotations.SerializedName


data class ReconData(

    @SerializedName("hotspots")
    val hotspots: List<Hotspot>,

    @SerializedName("message")
    val message: String

)