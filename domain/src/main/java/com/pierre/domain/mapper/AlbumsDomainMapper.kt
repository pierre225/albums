package com.pierre.domain.mapper

import com.pierre.data.remote.model.RemoteAlbum
import com.pierre.domain.model.DomainAlbum

class AlbumsDomainMapper {

    fun toDomain(remoteAlbum: RemoteAlbum) = DomainAlbum(
        title = remoteAlbum.title,
        imageUrl = remoteAlbum.url,
        thumbnailUrl = remoteAlbum.thumbnailUrl
    )

    fun toDomains(remoteAlbums: List<RemoteAlbum>) = remoteAlbums.map(this::toDomain)

}