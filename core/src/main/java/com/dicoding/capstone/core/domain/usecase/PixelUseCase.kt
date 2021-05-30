package com.dicoding.capstone.core.domain.usecase

import com.dicoding.capstone.core.data.Resource
import com.dicoding.capstone.core.domain.model.Pixel
import kotlinx.coroutines.flow.Flow

interface PixelUseCase {

    fun getAllPixel(): Flow<Resource<List<Pixel>>>

    fun searchPixel(query: String): Flow<Resource<List<Pixel>>>

    fun getFavoritePixel(): Flow<List<Pixel>>

    fun setFavoritePixel(pixel: Pixel, state: Boolean)
}