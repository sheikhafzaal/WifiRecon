package com.example.wifirecons.room.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.wifirecons.room.converters.Converter
import com.google.gson.annotations.SerializedName
import java.io.Serializable


@Entity
data class Hotspot(

    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,

    @TypeConverters(Converter::class)
    var bssids: List<Long>? = null,

    var created_at: Int? = null,

    var hotspot_type: Int? = null,

    @SerializedName("location.city")
    var city: Int? = null,

    @SerializedName("location.country")
    var country: String? = null,

    @SerializedName("location.latitude")
    var latitude: Double? = null,

    @SerializedName("location.longitude")
    var longitude: Double? = null,

    var `public`: Boolean? = null,

    @SerializedName("quality.last_seen")
    var last_seen: Int? = null,

    @SerializedName("quality.p_exists")
    var p_exists: Double? = null,

    @SerializedName("quality.p_internet")
    var p_internet: Double? = null,

    @SerializedName("quality.rating")
    var rating: Int? = null,

    @SerializedName("security.password")
    var password: String? = null,

    @SerializedName("security.type")
    var type: Int? = null,

    var ssid: String? = null,

    @SerializedName("statistics.last_connection_time")
    var last_connection_time: Int? = null,

    @TypeConverters(Converter::class)
    @SerializedName("user.badges")
    var badges: List<UserBadge>? = null,

    @SerializedName("user.id")
    var user_id: Int? = null,

    @SerializedName("user.name")
    var user_name: String? = null,

    @SerializedName("user.score")
    var score: Int? = null,

    @SerializedName("venue.id")
    var venue_id: String? = null,

    @SerializedName("venue.name")
    var venue_name: String? = null

) : Serializable