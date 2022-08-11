package com.pierre.data.repository.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

sealed class DataSong(
    open val id: Int,
    open val title: String,
    open val imageUrl: String,
    open val thumbnailUrl: String) {

    data class RemoteSong(
        @SerializedName("albumId") val albumId : Int,
        @SerializedName("id") override val id : Int,
        @SerializedName("title") override val title : String,
        @SerializedName("url") override val imageUrl : String,
        @SerializedName("thumbnailUrl") override val thumbnailUrl : String): DataSong(id, title, imageUrl, thumbnailUrl)

    @Entity(tableName = "songs")
    data class RoomSong(
        @PrimaryKey override val id: Int,
        @ColumnInfo(name = "title") override val title : String,
        @ColumnInfo(name = "image_url") override val imageUrl : String,
        @ColumnInfo(name = "thumbnail_url") override val thumbnailUrl: String): DataSong(id, title, imageUrl, thumbnailUrl)
}