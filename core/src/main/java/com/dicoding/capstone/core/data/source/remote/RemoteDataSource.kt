package com.dicoding.capstone.core.data.source.remote

import android.util.Log
import com.dicoding.capstone.core.BuildConfig
import com.dicoding.capstone.core.data.source.remote.network.ApiResponse
import com.dicoding.capstone.core.data.source.remote.network.ApiService
import com.dicoding.capstone.core.data.source.remote.response.PixelResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {

    suspend fun getAllPixel(): Flow<ApiResponse<List<PixelResponse>>> {
        //get data from remote api
        return flow {
            try {
                val response = apiService.getList(BuildConfig.API_KEY)
                val dataArray = response.hits
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.hits))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun searchPixel(query: String): Flow<ApiResponse<List<PixelResponse>>> {
        //search data from remote api
        return flow {
            try {
                val response = apiService.searchList(BuildConfig.API_KEY, query)
                val dataArray = response.hits
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.hits))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}