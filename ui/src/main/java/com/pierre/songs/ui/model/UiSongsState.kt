package com.pierre.songs.ui.model

import androidx.annotation.StringRes
import androidx.paging.Pager
import androidx.paging.PagingData

sealed class UiSongState {

    //data class UiSongResultsState(val songs: List<UiSong>) : UiSongState()
    data class UiSongResultsState(val pagedSongs : PagingData<UiSong>) : UiSongState()
    object UiLoadingState : UiSongState()
    data class UiErrorState(@StringRes val message: Int, val retry: Boolean) : UiSongState()

}


