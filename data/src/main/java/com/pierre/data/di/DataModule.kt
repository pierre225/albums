package com.pierre.data.di

import com.pierre.data.remote.RemoteDataSource
import com.pierre.data.repository.SongsRepository
import com.pierre.data.repository.SongsRepositoryImpl
import com.pierre.data.room.datasource.RoomDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DataModule {

    @Provides
    @Singleton
    fun repository(
        remoteDataSource: RemoteDataSource,
        roomDataSource: RoomDataSource
    ): SongsRepository = SongsRepositoryImpl(remoteDataSource, roomDataSource)

}
