package com.pierre.songs.ui.songslist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.viewbinding.ViewBinding
import com.pierre.songs.ui.base.BaseFragment
import com.pierre.songs.ui.songslist.model.UiSong
import com.pierre.songs.ui.songslist.model.UiSongsState
import com.pierre.songs.ui.songslist.adapter.SongsAdapter
import com.pierre.ui.databinding.FragmentSongsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SongsFragment : BaseFragment() {

    private val pagedSongsViewModel: PagedSongsViewModel by viewModels()

    private lateinit var binding: FragmentSongsBinding

    override fun initBinding(inflater: LayoutInflater): ViewBinding {
        binding = FragmentSongsBinding.inflate(inflater)
        return binding
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.songsList.adapter = SongsAdapter(::onSongClicked)

        // Listen ui state events
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch { pagedSongsViewModel.state.collect { handleState(it) } }
            }
        }

        pagedSongsViewModel.fetchPagedSongs()
    }

    // Display the right ui depending on the state
    private fun handleState(state: UiSongsState) {
        when (state) {
            is UiSongsState.UiErrorState -> displayError(state.message) { pagedSongsViewModel.fetchPagedSongs() }
            is UiSongsState.UiLoadingState -> displayLoading(true)
            is UiSongsState.UiSongsResultsState -> displayResults(state)
        }
    }

    // Shows the paged data
    private fun displayResults(resultsState: UiSongsState.UiSongsResultsState) {
        displayLoading(false)
        (binding.songsList.adapter as? SongsAdapter)?.submitData(viewLifecycleOwner.lifecycle, resultsState.pagedSongs)
    }

    // Only show a toast, it should probably open a detailed page
    private fun onSongClicked(song: UiSong) {
        Toast.makeText(context, song.title, Toast.LENGTH_LONG).show()
    }
}