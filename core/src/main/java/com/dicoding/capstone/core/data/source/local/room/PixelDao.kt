package com.dicoding.capstone.core.data.source.local.room

import androidx.room.*
import com.dicoding.capstone.core.data.source.local.entity.PixelEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PixelDao {

    @Query("SELECT * FROM pixel")
    fun getAllPixel(): Flow<List<PixelEntity>>

    @Query("SELECT * FROM pixel where isFavorite = 1")
    fun getFavoritePixel(): Flow<List<PixelEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPixel(tourism: List<PixelEntity>)

    @Update
    fun updateFavoritePixel(tourism: PixelEntity)
}