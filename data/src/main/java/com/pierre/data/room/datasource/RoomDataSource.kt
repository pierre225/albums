package com.pierre.data.room.datasource

import com.pierre.data.repository.model.DataSong
import com.pierre.data.room.dao.SongDao
import com.pierre.data.room.mapper.RoomMapper

internal class RoomDataSource(
    private val mapper: RoomMapper,
    private val songDao: SongDao) {

    suspend fun getAllSongs() = mapper.toData(songDao.getAllSongs())

    suspend fun insertSongs(dataSongs : List<DataSong>) = songDao.insertSongs(mapper.toRoom(dataSongs))

}