package com.pierre.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pierre.data.room.model.RoomSong

@Dao
interface SongDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSongs(songs: List<RoomSong>)

    @Query("SELECT * FROM songs")
    suspend fun getAllSongs(): List<RoomSong>

}