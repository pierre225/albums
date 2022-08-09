package com.pierre.songs.ui.model

import androidx.annotation.StringRes

sealed class UiSongState {

    data class UiSongResultsState(val songs: List<UiSong>) : UiSongState()
    object UiLoadingState : UiSongState()
    data class UiErrorState(@StringRes val message: Int, val retry: Boolean) : UiSongState()

}


