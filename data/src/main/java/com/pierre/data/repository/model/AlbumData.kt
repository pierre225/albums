package com.pierre.data.repository.model

/**
 AlbumData does not contains the url value as we won't show it in the app
 (We will remove it while mapping)
  */
data class AlbumData(
    private val albumId : Int,
    private val id : Int,
    private val title : String,
    private val albumUrl : String
)