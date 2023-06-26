package org.d3if0006.asesment1.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface LaundyDao {

    @Insert
    fun insert(laundry: LaundryEntity)

    @Query("SELECT * FROM laundry ORDER BY id DESC")
    fun getLastLaundry(): LiveData<List<LaundryEntity>>

    @Query("DELETE FROM laundry")
    fun clearData()




}