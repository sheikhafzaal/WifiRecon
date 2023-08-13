package com.example.wifirecons.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Data::class], version = 1, exportSchema = false)
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