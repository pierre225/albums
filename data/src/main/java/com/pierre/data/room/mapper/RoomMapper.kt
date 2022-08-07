package com.pierre.data.room.mapper

import com.pierre.data.repository.model.DataSong
import com.pierre.data.room.model.RoomSong

internal class RoomMapper {

    fun toData(roomSongs: List<RoomSong>) = roomSongs.map {
        DataSong(
            id = it.id,
            title = it.title,
            imageUrl = it.imageUrl,
            thumbnailUrl = it.thumbnailUrl
        )
    }

    fun toRoom(dataSongs: List<DataSong>) = dataSongs.map {
        RoomSong(
            id = it.id,
            title = it.title,
            imageUrl = it.imageUrl,
            thumbnailUrl = it.thumbnailUrl
        )
    }

}