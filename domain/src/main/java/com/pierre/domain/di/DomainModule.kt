package com.pierre.domain.di

import com.pierre.data.repository.SongsRepository
import com.pierre.domain.GetPagedSongsUseCase
import com.pierre.domain.mapper.SongsDomainMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Provides
    @Singleton
    fun domainMapper() = SongsDomainMapper()

    @Provides
    @Singleton
    fun songsPagedUseCase(
        repository: SongsRepository,
        domainMapper: SongsDomainMapper
    ) = GetPagedSongsUseCase(repository, domainMapper)

}
