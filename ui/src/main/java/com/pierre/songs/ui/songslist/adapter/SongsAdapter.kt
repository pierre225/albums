package com.pierre.songs.ui.songslist.adapter

import android.view.ViewGroup
import com.pierre.songs.ui.base.BaseAdapter
import com.pierre.songs.ui.model.UiSong
import com.pierre.songs.ui.songslist.viewholder.SongViewHolder

class SongsAdapter(private val onItemClick : ((UiSong) -> Unit)): BaseAdapter<UiSong>(onItemClick) {

    override fun create(parent: ViewGroup, viewType: Int) =
        SongViewHolder(parent)

}
