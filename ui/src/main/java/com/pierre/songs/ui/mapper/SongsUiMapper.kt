package com.pierre.songs.ui.mapper

import com.pierre.domain.model.DomainSong
import com.pierre.songs.ui.model.UiSong

class SongsUiMapper {

    fun toUi(domainSong: DomainSong) = UiSong(
        title = domainSong.title,
        image = domainSong.imageUrl,
        preview = domainSong.thumbnailUrl
    )

    fun toUis(domainSongs: List<DomainSong>) = domainSongs.map(this::toUi)

}