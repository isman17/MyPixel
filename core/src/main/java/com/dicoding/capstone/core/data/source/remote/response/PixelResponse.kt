package com.dicoding.capstone.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class PixelResponse (

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("pageURL")
    val pageURL: String,

    @field:SerializedName("tags")
    val tags: String,

    @field:SerializedName("largeImageURL")
    val largeImageURL: String,

    @field:SerializedName("imageWidth")
    val imageWidth: Int,

    @field:SerializedName("imageHeight")
    val imageHeight: Int,

    @field:SerializedName("views")
    val views: Int,

    @field:SerializedName("downloads")
    val downloads: Int,

    @field:SerializedName("likes")
    val likes: Int,

    @field:SerializedName("comments")
    val comments: Int,

    @field:SerializedName("user_id")
    val user_id: Int,

    @field:SerializedName("user")
    val user: String,

    @field:SerializedName("userImageURL")
    val userImageURL: String
)