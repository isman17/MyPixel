package com.dicoding.capstone.core.data.source.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "pixel")
data class PixelEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "pixelId")
    var pixelId: Int,

    @ColumnInfo(name = "pageURL")
    var pageURL: String,

    @ColumnInfo(name = "tags")
    var tags: String,

    @ColumnInfo(name = "largeImageURL")
    var largeImageURL: String,

    @ColumnInfo(name = "imageWidth")
    var imageWidth: Int,

    @ColumnInfo(name = "imageHeight")
    var imageHeight: Int,

    @ColumnInfo(name = "views")
    var views: Int,

    @ColumnInfo(name = "downloads")
    var downloads: Int,

    @ColumnInfo(name = "likes")
    var likes: Int,

    @ColumnInfo(name = "comments")
    var comments: Int,

    @ColumnInfo(name = "user_id")
    var user_id: Int,

    @ColumnInfo(name = "user")
    var user: String,

    @ColumnInfo(name = "userImageURL")
    var userImageURL: String,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false
) : Parcelable