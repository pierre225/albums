package com.pierre.songs.ui.songslist.adapter

import android.util.Log
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.pierre.songs.ui.model.UiSong
import com.pierre.songs.ui.songslist.viewholder.SongViewHolder

class TestPagingAdapter(private val onItemClick : ((UiSong) -> Unit)): PagingDataAdapter<UiSong, SongViewHolder>(
    diffCallback) {

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        getItem(position)?.also { holder.bind(it) }.also { Log.d("testtest", itemCount.toString()) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        SongViewHolder(parent)


    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<UiSong>() {

            override fun areItemsTheSame(oldItem: UiSong, newItem: UiSong) =
                oldItem.title == newItem.title // todo id

            override fun areContentsTheSame(oldItem: UiSong, newItem: UiSong) =
                oldItem == newItem
        }
    }
}
