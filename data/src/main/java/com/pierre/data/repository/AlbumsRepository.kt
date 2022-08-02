package com.pierre.data.repository

import com.pierre.data.remote.RemoteDataSource
import javax.inject.Singleton

@Singleton
class AlbumsRepository(private val remoteDataSource: RemoteDataSource) {

    suspend fun albums() = remoteDataSource.remoteAlbums()
}