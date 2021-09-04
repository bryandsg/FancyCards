package com.hashiradev.fancycards.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FancyCardDao {

    @Query("SELECT * FROM FANCYCARD")
    fun getAll(): LiveData<List<FancyCard>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun  insert(fancyCard: FancyCard)
}