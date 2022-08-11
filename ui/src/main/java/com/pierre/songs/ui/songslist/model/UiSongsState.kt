package com.pierre.songs.ui.songslist.model

import androidx.annotation.StringRes
import androidx.paging.PagingData

sealed class UiSongsState {

    data class UiSongsResultsState(val pagedSongs : PagingData<UiSong>) : UiSongsState()
    object UiLoadingState : UiSongsState()
    data class UiErrorState(@StringRes val message: Int, val retry: Boolean) : UiSongsState()

}


