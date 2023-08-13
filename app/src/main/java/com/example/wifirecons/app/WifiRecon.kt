package com.example.wifirecons.app

import android.app.Application
import android.content.Context
import com.example.wifirecons.room.AppDB
import com.example.wifirecons.room.AppDao

class WifiRecon : Application() {


    companion object {
        lateinit var context: Context
        lateinit var appDao:AppDao
    }

    override fun onCreate() {
        super.onCreate()


        context = this@WifiRecon
        appDao = AppDB.createService(this).appDao()


    }
}