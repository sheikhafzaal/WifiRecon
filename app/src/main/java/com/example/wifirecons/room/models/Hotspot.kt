package com.example.wifirecons.room.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.wifirecons.room.converters.Converter
import com.google.gson.annotations.SerializedName
import java.io.Serializable


@Entity
data class Hotspot(

//    @TypeConverters(Converter::class)
    val bssids: List<Long>? = null,
    val created_at: Int? = null,
    val hotspot_type: Int? = null,

    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,

    @SerializedName("location.city")
    val city: Int? = null,

    @SerializedName("location.country")
    val country: String? = null,

    @SerializedName("location.latitude")
    val latitude: Double? = null,

    @SerializedName("location.longitude")
    val longitude: Double? = null,

    val `public`: Boolean? = null,

    @SerializedName("quality.last_seen")
    val last_seen: Int? = null,

    @SerializedName("quality.p_exists")
    val p_exists: Double? = null,

    @SerializedName("quality.p_internet")
    val p_internet: Double? = null,

    @SerializedName("quality.rating")
    val rating: Int? = null,

    @SerializedName("security.password")
    val password: String? = null,

    @SerializedName("security.type")
    val type: Int? = null,

    val ssid: String? = null,

    @SerializedName("statistics.last_connection_time")
    val last_connection_time: Int? = null,

//    @TypeConverters(Converter::class)
    @SerializedName("user.badges")
    val badges: List<UserBadge> = ArrayList(),

    @SerializedName("user.id")
    val user_id: Int? = null,

    @SerializedName("user.name")
    val user_name: String? = null,

    @SerializedName("user.score")
    val score: Int? = null,

    @SerializedName("venue.id")
    val venue_id: String? = null,

    @SerializedName("venue.name")
    val venue_name: String? = null

) : Serializable