package com.pierre.data.room.mapper

import com.pierre.data.repository.model.DataSong

internal class RoomMapper {

    fun toRoom(dataSongs: List<DataSong>) = dataSongs.map {
        DataSong.RoomSong(
            id = it.id,
            title = it.title,
            imageUrl = it.imageUrl,
            thumbnailUrl = it.thumbnailUrl
        )
    }

}