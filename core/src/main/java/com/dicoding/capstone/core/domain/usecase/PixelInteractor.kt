package com.dicoding.capstone.core.domain.usecase

import com.dicoding.capstone.core.domain.model.Pixel
import com.dicoding.capstone.core.domain.repository.IPixelRepository

class PixelInteractor(private val pixelRepository: IPixelRepository) :
    PixelUseCase {

    override fun getAllPixel() = pixelRepository.getAllPixel()

    override fun searchPixel(query: String) = pixelRepository.searchPixel(query)

    override fun getFavoritePixel() = pixelRepository.getFavoritePixel()

    override fun setFavoritePixel(pixel: Pixel, state: Boolean) =
        pixelRepository.setFavoritePixel(pixel, state)
}