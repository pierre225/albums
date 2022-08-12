package com.pierre.songs.ui.songslist.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.pierre.songs.ui.base.BasePagingAdapter
import com.pierre.songs.ui.songslist.model.UiSong
import com.pierre.songs.ui.songslist.viewholder.SongViewHolder

/**
 * UiSong paging adapter
 * 2 UiSongs are considered the same if they have the same id
 */
class SongsAdapter(onItemClick: ((UiSong) -> Unit)) :
    BasePagingAdapter<UiSong>(onItemClick, diffCallback) {

    override fun create(parent: ViewGroup, viewType: Int) =
        SongViewHolder(parent)

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<UiSong>() {

            override fun areItemsTheSame(oldItem: UiSong, newItem: UiSong) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: UiSong, newItem: UiSong) =
                oldItem == newItem
        }
    }
}
