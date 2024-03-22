package com.example.wifirecons.room.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ReconData(

    @SerializedName("hotspots")
    val hotspots: List<Hotspot>? = null,

    @SerializedName("message")
    val message: String? = null

) : Serializable