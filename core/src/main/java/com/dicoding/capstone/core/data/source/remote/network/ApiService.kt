package com.dicoding.capstone.core.data.source.remote.network

import com.dicoding.capstone.core.data.source.remote.response.ListPixelResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/api/")
    suspend fun getList(
        @Query("key") key: String
    ): ListPixelResponse

    @GET("/api/")
    suspend fun searchList(
        @Query("key") key: String,
        @Query("q") q: String
    ): ListPixelResponse

}