package com.pierre.data.repository

import androidx.paging.PagingSource
import com.pierre.data.remote.RemoteDataSource
import com.pierre.data.repository.model.DataSong
import com.pierre.data.room.datasource.RoomDataSource

interface SongsRepository {

    //todo initial fetch
    // suspend fun getAllSongs(): List<DataSong>

    fun getPagedSongs(): PagingSource<Int, out DataSong>

}

internal class SongsRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val roomDataSource: RoomDataSource
) : SongsRepository {

//    override suspend fun getAllSongs(): List<DataSong> =
//        roomDataSource.getAllSongs().ifEmpty {
//            remoteDataSource.remoteSongs().also {
//                roomDataSource.insertSongs(it) }
//        }

    override fun getPagedSongs(): PagingSource<Int, out DataSong> =
        roomDataSource.getPagedSongs()

}
