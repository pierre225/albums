package com.pierre.domain

import com.pierre.data.repository.AlbumsRepository
import com.pierre.domain.mapper.AlbumsDomainMapper
import com.pierre.domain.model.DomainAlbum

// todo abstract use case with invoke // idk sam ??
class GetAlbumsUseCase(
    private val albumsRepository: AlbumsRepository,
    private val albumsDomainMapper: AlbumsDomainMapper) {

    suspend fun invoke() = albumsRepository
        .albums()
        .body()
        ?.map { albumsDomainMapper.toDomain(it) }
}