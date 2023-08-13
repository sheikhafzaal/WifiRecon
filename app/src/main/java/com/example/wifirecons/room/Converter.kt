package com.example.wifirecons.room

import androidx.room.TypeConverter
import com.example.wifirecons.models.ReconData
import com.google.gson.Gson

class Converters {
    private val gson = Gson()

    @TypeConverter
    fun toEntityValue(json: String?): ReconData? {
        return gson.fromJson(json, ReconData::class.java)
    }

    @TypeConverter
    fun toDatabaseValue(`object`: ReconData?): String? {
        return gson.toJson(`object`)
    }
}