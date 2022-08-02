package com.pierre.domain.di

import com.pierre.data.repository.SongsRepository
import com.pierre.domain.GetSongsUseCase
import com.pierre.domain.mapper.SongsDomainMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Provides
    fun domainMapper() = SongsDomainMapper()

    @Provides
    fun songsUseCase(
        repository: SongsRepository,
        domainMapper: SongsDomainMapper
    ) = GetSongsUseCase(repository, domainMapper)

}
