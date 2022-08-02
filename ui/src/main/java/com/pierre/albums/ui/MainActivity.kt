package com.pierre.albums.ui

import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.pierre.ui.R
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val albumsViewModel : AlbumsViewModel by viewModels()

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

        albumsViewModel.albums.observe(this) {
            Toast.makeText(applicationContext, it?.size?.toString(), Toast.LENGTH_LONG).show()
        }

        albumsViewModel.fetchAlbums()
    }

    override fun onResume() {
        super.onResume()

    }
}