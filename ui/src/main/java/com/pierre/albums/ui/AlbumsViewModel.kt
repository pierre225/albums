package com.pierre.albums.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pierre.domain.GetAlbumsUseCase
import com.pierre.domain.model.DomainAlbum
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlbumsViewModel @Inject constructor(private val getAlbumsUseCase: GetAlbumsUseCase): ViewModel() {

    private val _albums = MutableLiveData<List<DomainAlbum>>() // todo uiAlbum
    val albums : LiveData<List<DomainAlbum>> = _albums // mapper de ce coté la

    // todo le view model mapp les domainAlbum en ce que veut
    fun fetchAlbums() {
        viewModelScope.launch { // todo mapper
            _albums.postValue(getAlbumsUseCase.invoke())
        }
    }

}