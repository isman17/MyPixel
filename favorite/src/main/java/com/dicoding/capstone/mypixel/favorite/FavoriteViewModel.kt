package com.dicoding.capstone.mypixel.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dicoding.capstone.core.domain.usecase.PixelUseCase

class FavoriteViewModel(pixelUseCase: PixelUseCase) : ViewModel() {

    val favoritePixel = pixelUseCase.getFavoritePixel().asLiveData()

}