package com.dicoding.capstone.mypixel.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dicoding.capstone.core.domain.usecase.PixelUseCase

class HomeViewModel(pixelUseCase: PixelUseCase) : ViewModel() {

    val pixel = pixelUseCase.getAllPixel().asLiveData()

}