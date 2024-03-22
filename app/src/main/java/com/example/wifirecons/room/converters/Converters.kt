package com.example.wifirecons.room.converters

import androidx.room.TypeConverter
import com.example.wifirecons.room.models.Hotspot
import com.example.wifirecons.room.models.UserBadge
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converter {

    @TypeConverter
    fun fromHotspotList(hotspots: List<Hotspot>?): String? {
        if (hotspots == null) return null
        return Gson().toJson(hotspots)
    }

    @TypeConverter
    fun toHotspotList(hotspotString: String?): List<Hotspot>? {
        if (hotspotString == null) return null
        val type = object : TypeToken<List<Hotspot>>() {}.type
        return Gson().fromJson(hotspotString, type)
    }


    @TypeConverter
    fun fromLongList(value: List<Long>?): String? {
        val gson = Gson()
        val type = object : TypeToken<List<Long>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toLongList(value: String?): List<Long>? {
        val gson = Gson()
        val type = object : TypeToken<List<Long>>() {}.type
        return gson.fromJson(value, type)
    }

    @TypeConverter
    fun fromUserBadgeList(value: List<UserBadge>?): String? {
        val gson = Gson()
        val type = object : TypeToken<List<UserBadge>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toUserBadgeList(value: String?): List<UserBadge>? {
        val gson = Gson()
        val type = object : TypeToken<List<UserBadge>>() {}.type
        return gson.fromJson(value, type)
    }


}