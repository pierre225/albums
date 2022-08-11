package com.pierre.songs.ui.base

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil

abstract class BasePagingAdapter<T : Any>(
    private val onItemClick: ((T) -> Unit)? = null,
    diffCallback: DiffUtil.ItemCallback<T>
) : PagingDataAdapter<T, BaseViewHolder<T>>(
    diffCallback
) {

    abstract fun create(parent: ViewGroup, viewType: Int): BaseViewHolder<T>

    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) {
        getItem(position)?.also { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        create(parent, viewType).apply {
            if (onItemClick != null) setOnClickListener(onItemClick)
        }

}
