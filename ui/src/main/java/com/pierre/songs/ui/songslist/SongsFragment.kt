package com.pierre.songs.ui.songslist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.viewbinding.ViewBinding
import com.pierre.songs.ui.base.BaseFragment
import com.pierre.songs.ui.model.UiSong
import com.pierre.songs.ui.model.UiSongState
import com.pierre.songs.ui.songslist.adapter.SongsAdapter
import com.pierre.ui.databinding.FragmentSongsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SongsFragment : BaseFragment() {

    private val songsViewModel : SongsViewModel by viewModels()

    private lateinit var binding: FragmentSongsBinding

    private val songsAdapter by lazy {
        SongsAdapter(::onSongClicked).also { binding.songsList.adapter = it }
    }

    override fun initBinding(inflater: LayoutInflater): ViewBinding {
        binding = FragmentSongsBinding.inflate(inflater)
        return binding
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        songsViewModel.state.observe(viewLifecycleOwner) {
            when (it) {
                is UiSongState.UiErrorState -> displayError(it.message) { songsViewModel.fetchSongs() }
                is UiSongState.UiLoadingState -> displayLoading(true)
                is UiSongState.UiSongResultsState -> displayResults(it.songs)
            }
        }

        songsViewModel.fetchSongs()
    }

    private fun displayResults(songs: List<UiSong>) {
        displayLoading(false)
        songsAdapter.updateItems(songs)
    }

    private fun onSongClicked(song: UiSong) {
        Toast.makeText(context, song.title, Toast.LENGTH_LONG).show()
    }
}