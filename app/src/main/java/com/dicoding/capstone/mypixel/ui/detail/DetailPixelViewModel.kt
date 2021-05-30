package com.dicoding.capstone.mypixel.ui.detail

import androidx.lifecycle.ViewModel
import com.dicoding.capstone.core.domain.model.Pixel
import com.dicoding.capstone.core.domain.usecase.PixelUseCase

class DetailPixelViewModel(private val pixelUseCase: PixelUseCase) : ViewModel() {
    fun setFavoriteTourism(pixel: Pixel, newStatus: Boolean) =
        pixelUseCase.setFavoritePixel(pixel, newStatus)
}