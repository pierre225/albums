package com.pierre.data.repository.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Data song as a remote model or a room model
 * The Data song fields must be annoted as Transient to avoid gson to consider the parent class while serializing
 * (This is because the parent and child classes have some common fields name title, id...)
 */
sealed class DataSong(
    @Transient open val id: Int,
    @Transient open val title: String,
    @Transient open val imageUrl: String,
    @Transient open val thumbnailUrl: String) {

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