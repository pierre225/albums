package com.pierre.data.repository

import androidx.paging.PagingSource
import com.pierre.data.remote.RemoteDataSource
import com.pierre.data.repository.model.DataSong
import com.pierre.data.room.datasource.RoomDataSource

interface SongsRepository {

    suspend fun preloadSongs()

    fun getPagedSongs(): PagingSource<Int, out DataSong>

}

internal class SongsRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val roomDataSource: RoomDataSource
) : SongsRepository {

    /**
     * Checks if there are songs in the DB, if not request it online and populate the DB
     * Would be nice to implement a cache logic that would require a reload every X days
     */
    override suspend fun preloadSongs() {
        val soundsCount = roomDataSource.getSongsCount()
        if (soundsCount == 0) {
            remoteDataSource.remoteSongs()?.also { roomDataSource.insertSongs(it) }
        }
    }

    /**
     * Get a paging source of data songs
     */
    override fun getPagedSongs(): PagingSource<Int, out DataSong> =
        roomDataSource.getPagedSongs()

}
