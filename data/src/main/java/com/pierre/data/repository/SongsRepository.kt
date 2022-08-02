package com.pierre.data.repository

import com.pierre.data.remote.RemoteDataSource
import javax.inject.Singleton

@Singleton
class SongsRepository(private val remoteDataSource: RemoteDataSource) {

    suspend fun songs() = remoteDataSource.remoteSongs()
}