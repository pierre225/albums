package com.pierre.songs.ui.songslist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pierre.domain.GetPagedSongsUseCase
import com.pierre.songs.ui.mapper.SongsUiMapper
import com.pierre.songs.ui.model.UiSongsState
import com.pierre.songs.ui.utils.ExceptionUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PagedSongsViewModel @Inject constructor(
    private val mapper: SongsUiMapper,
    private val getPagedSongsUseCase: GetPagedSongsUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow<UiSongsState>(UiSongsState.UiLoadingState)
    val state: StateFlow<UiSongsState> = _state

    /**
     * Tries to retrieved the paged songs update the state accordingly
     */
    fun fetchPagedSongs() {
        viewModelScope.launch {
            // initialize with a loading state
            _state.emit(UiSongsState.UiLoadingState)

            try {
                getPagingDataSongs()
            } catch (e: Exception) {
                onGetPagedSongsError(e)
            }
        }
    }

    /**
     * Use the getSongsUseCase in order to get the flow of PagingData of Domain song
     * Then map the domain song, collect the flow and emit in as a successful result
     */
    private suspend fun getPagingDataSongs() {
        getPagedSongsUseCase.invoke()
            .map { mapper.mapPagingDataToDomain(it) }
            .collectLatest {
                _state.emit(UiSongsState.UiSongsResultsState(it))
            }
    }

    /**
     * If there is an error along the way, map this error and send it
     */
    private suspend fun onGetPagedSongsError(e: Exception) {
        e.printStackTrace()
        _state.emit(
            UiSongsState.UiErrorState(
                message = ExceptionUtils.messageFromException(e),
                retry = ExceptionUtils.canRetry(e)
            )
        )
    }
}