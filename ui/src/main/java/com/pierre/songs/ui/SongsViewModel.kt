package com.pierre.songs.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pierre.domain.GetSongsUseCase
import com.pierre.domain.model.DomainSong
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SongsViewModel @Inject constructor(private val getSongsUseCase: GetSongsUseCase): ViewModel() {

    private val _songs = MutableLiveData<List<DomainSong>>() // todo uiAlbum
    val songs : LiveData<List<DomainSong>> = _songs // mapper de ce coté la

    // todo le view model mapp les domainAlbum en ce que veut
    fun fetchSongs() {
        viewModelScope.launch { // todo mapper
            _songs.postValue(getSongsUseCase.invoke())
        }
    }
}