package com.pierre.domain

import com.pierre.data.repository.SongsRepository
import com.pierre.domain.mapper.SongsDomainMapper

// todo abstract use case with invoke // idk sam ??
class GetSongsUseCase(
    private val songsRepository: SongsRepository,
    private val songsDomainMapper: SongsDomainMapper) {

    suspend fun invoke() = songsRepository
        .songs()
        .body()
        ?.map { songsDomainMapper.toDomain(it) }
}