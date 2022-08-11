package com.pierre.data.room.datasource

import androidx.paging.PagingSource
import com.pierre.data.repository.model.DataSong
import com.pierre.data.room.dao.SongDao
import com.pierre.data.room.mapper.RoomMapper

interface RoomDataSource {

    suspend fun getSongsCount() : Int

    fun getPagedSongs() : PagingSource<Int, DataSong.RoomSong>

    suspend fun insertSongs(dataSongs : List<DataSong.RemoteSong>)
}

internal class RoomDataSourceImpl(
    private val mapper: RoomMapper,
    private val songDao: SongDao) : RoomDataSource {

    override suspend fun getSongsCount() = songDao.getSongsCount()

    override fun getPagedSongs() = songDao.pagedSongs()

    override suspend fun insertSongs(dataSongs : List<DataSong.RemoteSong>) =
        songDao.insertSongs(mapper.toRoom(dataSongs))

}