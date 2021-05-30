package com.dicoding.capstone.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListPixelResponse(

    @field:SerializedName("total")
    val total: Int,

    @field:SerializedName("totalHits")
    val totalHits: Int,

    @field:SerializedName("hits")
    val hits: List<PixelResponse>
)
