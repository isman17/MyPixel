package com.dicoding.capstone.core.utils

import com.dicoding.capstone.core.data.source.local.entity.PixelEntity
import com.dicoding.capstone.core.data.source.remote.response.PixelResponse
import com.dicoding.capstone.core.domain.model.Pixel

object DataMapper {
    fun mapResponsesToEntities(input: List<PixelResponse>): List<PixelEntity> {
        val pixelList =
            ArrayList<PixelEntity>()
        input.map { response ->
            val pixelEntity = PixelEntity(
                pixelId = response.id,
                pageURL = response.pageURL,
                tags = response.tags,
                largeImageURL = response.largeImageURL,
                imageWidth = response.imageWidth,
                imageHeight = response.imageHeight,
                views = response.views,
                downloads = response.downloads,
                likes = response.likes,
                comments = response.comments,
                user_id = response.user_id,
                user = response.user,
                userImageURL = response.userImageURL,
                isFavorite = false
            )
            pixelList.add(pixelEntity)
        }
        return pixelList
    }

    fun mapEntitiesToDomain(input: List<PixelEntity>): List<Pixel> =
        input.map { item ->
            Pixel(
                pixelId = item.pixelId,
                pageURL = item.pageURL,
                tags = item.tags,
                largeImageURL = item.largeImageURL,
                imageWidth = item.imageWidth,
                imageHeight = item.imageHeight,
                views = item.views,
                downloads = item.downloads,
                likes = item.likes,
                comments = item.comments,
                user_id = item.user_id,
                user = item.user,
                userImageURL = item.userImageURL,
                isFavorite = item.isFavorite
            )
        }

    fun mapDomainToEntity(input: Pixel) =
        PixelEntity(
            pixelId = input.pixelId,
            pageURL = input.pageURL,
            tags = input.tags,
            largeImageURL = input.largeImageURL,
            imageWidth = input.imageWidth,
            imageHeight = input.imageHeight,
            views = input.views,
            downloads = input.downloads,
            likes = input.likes,
            comments = input.comments,
            user_id = input.user_id,
            user = input.user,
            userImageURL = input.userImageURL,
            isFavorite = input.isFavorite
        )
}