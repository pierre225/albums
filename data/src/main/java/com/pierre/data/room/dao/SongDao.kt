package com.pierre.data.room.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pierre.data.repository.model.DataSong

@Dao
interface SongDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSongs(songs: List<DataSong.RoomSong>)

    @Query("SELECT * FROM songs")
    suspend fun getAllSongs(): List<DataSong.RoomSong>

    @Query("SELECT * FROM songs")
    fun pagedSongs(): PagingSource<Int, DataSong.RoomSong>

}