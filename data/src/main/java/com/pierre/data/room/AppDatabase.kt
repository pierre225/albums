package com.pierre.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.pierre.data.room.dao.SongDao
import com.pierre.data.room.model.RoomSong

@Database(entities = [RoomSong::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun songDao(): SongDao
}
