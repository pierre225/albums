package com.pierre.data.remote.mapper

import com.pierre.data.remote.model.RemoteSong
import com.pierre.data.repository.model.DataSong
import com.pierre.data.room.model.RoomSong

internal class RemoteMapper {

    fun toData(roomSongs: List<RemoteSong>) = roomSongs.map {
        DataSong(
            id = it.id,
            title = it.title,
            imageUrl = it.url,
            thumbnailUrl = it.thumbnailUrl
        )
    }
}
