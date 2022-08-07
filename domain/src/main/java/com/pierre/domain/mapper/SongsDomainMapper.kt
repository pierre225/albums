package com.pierre.domain.mapper

import com.pierre.data.repository.model.DataSong
import com.pierre.domain.model.DomainSong

class SongsDomainMapper {

    fun toDomain(dataSong: DataSong) = DomainSong(
        title = dataSong.title,
        imageUrl = dataSong.imageUrl,
        thumbnailUrl = dataSong.thumbnailUrl
    )

    fun toDomains(dataSongs: List<DataSong>) = dataSongs.map(this::toDomain)

}