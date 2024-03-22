package com.example.wifirecons.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.wifirecons.room.models.Hotspot
import com.example.wifirecons.room.models.ReconData

@Dao
interface AppDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertData(reconData: ReconData)

    @Query("select * from recondata")
    fun fetchReconData(): LiveData<List<ReconData>>

}