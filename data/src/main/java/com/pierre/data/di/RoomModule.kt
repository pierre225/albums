package com.pierre.data.di

import android.content.Context
import androidx.room.Room
import com.pierre.data.room.AppDatabase
import com.pierre.data.room.dao.SongDao
import com.pierre.data.room.datasource.RoomDataSource
import com.pierre.data.room.datasource.RoomDataSourceImpl
import com.pierre.data.room.mapper.RoomMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object RoomModule {

    @Provides
    @Singleton
    fun roomDatabase(@ApplicationContext context: Context) =
            Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                "songs-db"
            ).build()

    @Provides
    @Singleton
    fun songDao(appDatabase: AppDatabase) =
        appDatabase.songDao()

    @Provides
    @Singleton
    fun roomMapper() = RoomMapper()

    @Provides
    @Singleton
    fun roomDataSource(
        songDao: SongDao,
        roomMapper: RoomMapper
    ) : RoomDataSource = RoomDataSourceImpl(roomMapper, songDao)

}
