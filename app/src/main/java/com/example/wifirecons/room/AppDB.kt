package com.example.wifirecons.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.wifirecons.room.converters.Converter
import com.example.wifirecons.room.models.ReconData

@Database(
    entities = [ReconData::class],
    version = 2,
    exportSchema = false
)
@TypeConverters(Converter::class)

abstract class AppDB : RoomDatabase() {

    abstract fun appDao(): AppDao

    companion object {
        fun createService(ctx: Context): AppDB {
            return Room.databaseBuilder(ctx, AppDB::class.java, AppDB::class.simpleName.toString())
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build()
        }
    }


}