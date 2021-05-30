package com.dicoding.capstone.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Pixel(
    val pixelId: Int,
    val pageURL: String,
    val tags: String,
    val largeImageURL: String,
    val imageWidth: Int,
    val imageHeight: Int,
    val views: Int,
    val downloads: Int,
    val likes: Int,
    val comments: Int,
    val user_id: Int,
    val user: String,
    val userImageURL: String,
    val isFavorite: Boolean
) : Parcelable
