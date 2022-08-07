package com.pierre.data.di

import android.content.Context
import android.util.Log
import androidx.room.Room
import androidx.room.RoomDatabase
import com.pierre.data.room.AppDatabase
import com.pierre.data.room.dao.SongDao
import com.pierre.data.room.datasource.RoomDataSource
import com.pierre.data.remote.mapper.RemoteMapper
import com.pierre.data.room.mapper.RoomMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object RoomModule {

    @Provides
    fun roomDatabase(@ApplicationContext context: Context) =
            Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                "songs-db"
            ).build()

    @Provides
    fun songDao(appDatabase: AppDatabase) =
        appDatabase.songDao()

    @Provides
    fun roomMapper() = RoomMapper()

    @Provides
    fun roomDataSource(
        songDao: SongDao,
        roomMapper: RoomMapper
    ) = RoomDataSource(roomMapper, songDao)

}
