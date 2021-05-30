package com.dicoding.capstone.core.data.source.local

import com.dicoding.capstone.core.data.source.local.entity.PixelEntity
import com.dicoding.capstone.core.data.source.local.room.PixelDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val pixelDao: PixelDao) {
    fun getAllPixel(): Flow<List<PixelEntity>> =
        pixelDao.getAllPixel()

    fun getFavoritePixel(): Flow<List<PixelEntity>> =
        pixelDao.getFavoritePixel()

    suspend fun insertPixel(tourismList: List<PixelEntity>) =
        pixelDao.insertPixel(tourismList)

    fun setFavoritePixel(
        pixelEntity: PixelEntity,
        newState: Boolean
    ) {
        pixelEntity.isFavorite = newState
        pixelDao.updateFavoritePixel(pixelEntity)
    }
}