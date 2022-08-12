package com.pierre.songs.ui.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pierre.domain.usecases.PreloadSongsUseCase
import com.pierre.songs.ui.songslist.model.UiSongsState
import com.pierre.songs.ui.splash.model.PreloadState
import com.pierre.songs.ui.utils.ExceptionUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PreloadSongsViewModel @Inject constructor(
    private val preloadSongsUseCase: PreloadSongsUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow<PreloadState>(PreloadState.Loading)
    val state: StateFlow<PreloadState> = _state

    fun preloadSongs() {
        viewModelScope.launch {
            _state.emit(PreloadState.Loading)
            try {
                preload()
            } catch(e: Exception) {
                onPreloadError(e)
            }
        }
    }

    private suspend fun preload() {
        preloadSongsUseCase.invoke()
        _state.emit(PreloadState.Success)
    }

    /**
     * If there is an error along the way, map this error and send it
     */
    private suspend fun onPreloadError(e: Exception) {
        e.printStackTrace()
        _state.emit(
            PreloadState.Error(
                message = ExceptionUtils.messageFromException(e),
                retry = ExceptionUtils.canRetry(e)
            )
        )
    }
}