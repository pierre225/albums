package com.pierre.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pierre.data.repository.model.DataSong
import com.pierre.data.room.dao.SongDao

@Database(entities = [DataSong.RoomSong::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun songDao(): SongDao
}
