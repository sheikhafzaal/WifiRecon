package com.example.wifirecons.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface AppDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertData(reconData: Data)

    @Query("select * from Data")
    fun fetchData(): LiveData<List<Data>>

}