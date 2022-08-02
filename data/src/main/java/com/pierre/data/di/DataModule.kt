package com.pierre.data.di

import com.pierre.data.remote.RemoteDataSource
import com.pierre.data.repository.AlbumsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    fun remoteDataSource() = RemoteDataSource()

    @Provides
    fun repository(remoteDataSource: RemoteDataSource) = AlbumsRepository(remoteDataSource)
}
