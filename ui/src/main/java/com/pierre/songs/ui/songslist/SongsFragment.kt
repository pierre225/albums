package com.pierre.songs.ui.songslist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import com.pierre.songs.ui.base.BaseFragment
import com.pierre.songs.ui.songslist.model.UiSong
import com.pierre.songs.ui.songslist.model.UiSongsState
import com.pierre.songs.ui.songslist.adapter.SongsAdapter
import com.pierre.ui.databinding.FragmentSongsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SongsFragment : BaseFragment() {

    private val pagedSongsViewModel: PagedSongsViewModel by viewModels()

    private lateinit var binding: FragmentSongsBinding

    // Creating the adapter and directly set it to the recycler view
    private val songsAdapter by lazy {
        SongsAdapter(::onSongClicked).also { binding.songsList.adapter = it }
    }

    override fun initBinding(inflater: LayoutInflater): ViewBinding {
        binding = FragmentSongsBinding.inflate(inflater)
        return binding
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launchWhenStarted {
            pagedSongsViewModel.state.collect { handleState(it) }
        }

        pagedSongsViewModel.fetchPagedSongs()
    }

    private fun handleState(state: UiSongsState) {
        when (state) {
            is UiSongsState.UiErrorState -> displayError(state.message) { pagedSongsViewModel.fetchPagedSongs() }
            is UiSongsState.UiLoadingState -> displayLoading(true)
            is UiSongsState.UiSongsResultsState -> displayResults(state)
        }
    }

    private fun displayResults(resultsState: UiSongsState.UiSongsResultsState) {
        displayLoading(false)
        songsAdapter.submitData(viewLifecycleOwner.lifecycle, resultsState.pagedSongs)
    }

    private fun onSongClicked(song: UiSong) {
        Toast.makeText(context, song.title, Toast.LENGTH_LONG).show()
    }
}