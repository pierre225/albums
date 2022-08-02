package com.pierre.domain.di

import com.pierre.data.repository.AlbumsRepository
import com.pierre.domain.GetAlbumsUseCase
import com.pierre.domain.mapper.AlbumsDomainMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Provides
    fun domainMapper() = AlbumsDomainMapper()

    @Provides
    fun albumsUseCase(
        repository: AlbumsRepository,
        domainMapper: AlbumsDomainMapper
    ) = GetAlbumsUseCase(repository, domainMapper)

}
