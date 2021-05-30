package com.dicoding.capstone.core.data

import com.dicoding.capstone.core.data.source.local.LocalDataSource
import com.dicoding.capstone.core.data.source.remote.RemoteDataSource
import com.dicoding.capstone.core.data.source.remote.network.ApiResponse
import com.dicoding.capstone.core.data.source.remote.response.PixelResponse
import com.dicoding.capstone.core.domain.model.Pixel
import com.dicoding.capstone.core.domain.repository.IPixelRepository
import com.dicoding.capstone.core.utils.AppExecutors
import com.dicoding.capstone.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PixelRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IPixelRepository {

    override fun getAllPixel(): Flow<Resource<List<Pixel>>> =
        object :
            NetworkBoundResource<List<Pixel>, List<PixelResponse>>() {
            override fun loadFromDB(): Flow<List<Pixel>> {
                return localDataSource.getAllPixel().map { DataMapper.mapEntitiesToDomain(it) }
            }

            override fun shouldFetch(data: List<Pixel>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<PixelResponse>>> =
                remoteDataSource.getAllPixel()

            override suspend fun saveCallResult(data: List<PixelResponse>) {
                val tourismList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertPixel(tourismList)
            }
        }.asFlow()

    override fun searchPixel(query: String): Flow<Resource<List<Pixel>>> =
        object :
            NetworkBoundResource<List<Pixel>, List<PixelResponse>>() {
            override fun loadFromDB(): Flow<List<Pixel>> {
                return localDataSource.getAllPixel().map { DataMapper.mapEntitiesToDomain(it) }
            }

            override fun shouldFetch(data: List<Pixel>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<PixelResponse>>> =
                remoteDataSource.searchPixel(query)

            override suspend fun saveCallResult(data: List<PixelResponse>) {
                val tourismList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertPixel(tourismList)
            }
        }.asFlow()

    override fun getFavoritePixel(): Flow<List<Pixel>> {
        return localDataSource.getFavoritePixel().map { DataMapper.mapEntitiesToDomain(it) }

    }

    override fun setFavoritePixel(pixel: Pixel, state: Boolean) {
        val pixelEntity = DataMapper.mapDomainToEntity(pixel)
        appExecutors.diskIO().execute { localDataSource.setFavoritePixel(pixelEntity, state) }
    }

}
