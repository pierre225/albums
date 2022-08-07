package com.pierre.data.di

import com.pierre.data.remote.RemoteDataSource
import com.pierre.data.remote.mapper.RemoteMapper
import com.pierre.data.repository.SongsRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object RemoteModule {

    @Provides
    fun remoteDataSource(remoteMapper: RemoteMapper) = RemoteDataSource(remoteMapper)

    @Provides
    fun remoteMapper() = RemoteMapper()

}
