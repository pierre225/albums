package com.pierre.data.repository

import android.util.Log
import com.pierre.data.remote.RemoteDataSource
import com.pierre.data.repository.model.DataSong
import com.pierre.data.room.datasource.RoomDataSource

interface SongsRepository {

    suspend fun getAllSongs(): List<DataSong>

}

internal class SongsRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val roomDataSource: RoomDataSource
) : SongsRepository {

    override suspend fun getAllSongs(): List<DataSong> =
        roomDataSource.getAllSongs().ifEmpty {
            remoteDataSource.remoteSongs().also {
                roomDataSource.insertSongs(it) }
        }
}
