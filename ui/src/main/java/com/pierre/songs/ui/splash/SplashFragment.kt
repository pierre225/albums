package com.pierre.songs.ui.splash

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.pierre.songs.ui.base.BaseFragment
import com.pierre.songs.ui.splash.model.PreloadState
import com.pierre.ui.R
import com.pierre.ui.databinding.FragmentSplashBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : BaseFragment() {

    private val preloadSongsViewModel: PreloadSongsViewModel by viewModels()

    private lateinit var binding: FragmentSplashBinding

    override fun initBinding(inflater: LayoutInflater): ViewBinding {
        binding = FragmentSplashBinding.inflate(inflater)
        return binding
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d("testtest", "created")

        lifecycleScope.launchWhenStarted {
            preloadSongsViewModel.state.collect { handleState(it) }
        }

        preloadSongsViewModel.preloadSongs()
    }

    private fun handleState(state: PreloadState) {
        when (state) {
            is PreloadState.Error -> displayError(state.message) { preloadSongsViewModel.preloadSongs() }
            is PreloadState.Loading -> displayLoading(true)
            is PreloadState.Success -> onSuccess()
        }
    }

    private fun onSuccess() {
        displayLoading(false)
        findNavController().navigate(R.id.splashToSongs)
    }

}