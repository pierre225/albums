package com.pierre.domain.mapper

import com.pierre.data.remote.model.RemoteSong
import com.pierre.domain.model.DomainSong

class SongsDomainMapper {

    fun toDomain(remoteSong: RemoteSong) = DomainSong(
        title = remoteSong.title,
        imageUrl = remoteSong.url,
        thumbnailUrl = remoteSong.thumbnailUrl
    )

    fun toDomains(remoteSongs: List<RemoteSong>) = remoteSongs.map(this::toDomain)

}