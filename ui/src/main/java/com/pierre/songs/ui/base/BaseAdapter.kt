package com.pierre.songs.ui.base

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<T : Any>(private val onItemClick : ((T) -> Unit)? = null) : RecyclerView.Adapter<BaseViewHolder<T>>() {

    protected var items = emptyList<T>()

    abstract fun create(parent: ViewGroup, viewType: Int): BaseViewHolder<T>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<T> =
        create(parent, viewType).apply {
            if (onItemClick != null) setOnClickListener(onItemClick)
        }

    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    fun updateItems(items: List<T>) {
        this.items = items
        // todo check when paging
        notifyDataSetChanged()
    }

}