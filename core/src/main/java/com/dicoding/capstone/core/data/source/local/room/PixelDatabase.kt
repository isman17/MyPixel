package com.dicoding.capstone.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dicoding.capstone.core.data.source.local.entity.PixelEntity

@Database(entities = [PixelEntity::class], version = 1, exportSchema = false)
abstract class PixelDatabase : RoomDatabase() {

    abstract fun pixelDao(): PixelDao

}