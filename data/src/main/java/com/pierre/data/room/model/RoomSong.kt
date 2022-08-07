package com.pierre.data.room.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "songs")
data class RoomSong(
    @PrimaryKey val id: Int,
    @ColumnInfo (name = "title") val title : String,
    @ColumnInfo (name = "image_url") val imageUrl : String,
    @ColumnInfo (name = "thumbnail_url") val thumbnailUrl: String)