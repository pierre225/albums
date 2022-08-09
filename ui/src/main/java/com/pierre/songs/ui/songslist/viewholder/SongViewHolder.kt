package com.pierre.songs.ui.songslist.viewholder

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pierre.songs.ui.base.BaseViewHolder
import com.pierre.songs.ui.model.UiSong
import com.pierre.ui.R
import com.pierre.ui.databinding.SongViewHolderBinding

class SongViewHolder(parent: ViewGroup): BaseViewHolder<UiSong>(parent, R.layout.song_view_holder) {

    private val binding = SongViewHolderBinding.bind(itemView)

    override fun bind(item: UiSong) {
        super.bind(item)

        binding.apply {
            title.text = item.title
        }

        // todo glide extension

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
    }
}