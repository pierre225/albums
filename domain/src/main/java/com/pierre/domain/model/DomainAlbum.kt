package com.pierre.domain.model

/**
 Albums field accessible from the UI to be converted
  */
data class DomainAlbum(
    private val title : String,
    private val imageUrl : String,
    private val thumbnailUrl: String
)