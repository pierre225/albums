package com.pierre.songs.ui.songslist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pierre.domain.GetSongsUseCase
import com.pierre.domain.model.DomainSong
import com.pierre.songs.ui.mapper.SongsUiMapper
import com.pierre.songs.ui.model.UiSongState
import com.pierre.songs.ui.utils.ExceptionUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SongsViewModel @Inject constructor(
    private val mapper: SongsUiMapper,
    private val getSongsUseCase: GetSongsUseCase
) : ViewModel() {

    private val _state = MutableLiveData<UiSongState>()
    val state: LiveData<UiSongState> = _state

    fun fetchSongs() {
        viewModelScope.launch {
            _state.postValue(UiSongState.UiLoadingState)

            try {
                _state.postValue(UiSongState.UiSongResultsState(mapper.toUis(getSongsUseCase.invoke())))
            } catch (e: Exception) {
                e.printStackTrace()
                _state.postValue(UiSongState.UiErrorState(
                    message = ExceptionUtils.messageFromException(e),
                    retry = ExceptionUtils.canRetry(e)
                ))
            }
        }
    }
}