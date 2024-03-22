package com.example.wifirecons.room.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable


@Entity
data class ReconData(

    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,

    @SerializedName("hotspots")
    val hotspots: List<Hotspot> ?= null,

    @SerializedName("message")
    val message: String? = null

) : Serializable