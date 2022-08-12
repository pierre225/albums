package com.pierre.songs.ui.songslist.viewholder

import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.pierre.songs.ui.base.BaseViewHolder
import com.pierre.songs.ui.songslist.model.UiSong
import com.pierre.ui.R
import com.pierre.ui.databinding.SongViewHolderBinding

class SongViewHolder(parent: ViewGroup) :
    BaseViewHolder<UiSong>(parent, R.layout.song_view_holder) {

    private val binding = SongViewHolderBinding.bind(itemView)

    override fun bind(item: UiSong) {
        super.bind(item)

        binding.apply {
            title.text = item.title
            Glide.with(root.context)
                .load(glideUrl(item.preview))
                .placeholder(R.drawable.placeholder)
                .into(thumbnail);
        }
    }

    // Needed to get the image
    private fun glideUrl(url: String) =
        GlideUrl(url, LazyHeaders.Builder().addHeader("User-Agent", "android").build())
}