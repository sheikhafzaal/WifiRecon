package com.example.wifirecons.models

import com.google.gson.annotations.SerializedName


data class Hotspot(
    val bssids: List<Long>,
    val created_at: Int,
    val hotspot_type: Int,
    val id: Int,

    @SerializedName("location.city")
    val city: Int,

    @SerializedName("location.country")
    val country: String,

    @SerializedName("location.latitude")
    val latitude: Double,

    @SerializedName("location.longitude")
    val longitude: Double,

    val `public`: Boolean,

    @SerializedName("quality.last_seen")
    val last_seen: Int,

    @SerializedName("quality.p_exists")
    val p_exists: Double,

    @SerializedName("quality.p_internet")
    val p_internet: Double,

    @SerializedName("quality.rating")
    val rating: Int,

    @SerializedName("security.password")
    val password: String,

    @SerializedName("security.type")
    val type: Int,

    val ssid: String,

    @SerializedName("statistics.last_connection_time")
    val last_connection_time: Int,

    @SerializedName("user.badges")
    val badges: List<UserBadge>,

    @SerializedName("user.id")
    val user_id: Int,

    @SerializedName("user.name")
    val user_name: String,

    @SerializedName("user.score")
    val score: Int,

    @SerializedName("venue.id")
    val venue_id: String,

    @SerializedName("venue.name")
    val venue_name: String

)