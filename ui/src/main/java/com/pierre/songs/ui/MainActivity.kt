package com.pierre.songs.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.pierre.ui.R
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val songsViewModel : SongsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val iv = findViewById<ImageView>(R.id.test)
//        val url = GlideUrl(
//            "https://via.placeholder.com/150.gif/771796", LazyHeaders.Builder()
//                .addHeader("User-Agent", "android")
//                .build()
//        )
//
//        Glide.with(this)
//            .load("https://via.placeholder.com/150/24f355")
//            .into(iv);

        songsViewModel.songs.observe(this) {
            Toast.makeText(applicationContext, it?.size?.toString(), Toast.LENGTH_LONG).show()
        }

        songsViewModel.fetchSongs()
    }

    override fun onResume() {
        super.onResume()

    }
}