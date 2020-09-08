package com.pavlov.demo_translator.ui.search.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.pavlov.demo_translator.core.api.data.Word

class SearchAdapter(private val onClickListener: (Word, Int) -> Unit) :
    PagingDataAdapter<Word, RecyclerView.ViewHolder>(
        object : DiffUtil.ItemCallback<Word>() {
            override fun areItemsTheSame(
                oldItem: Word,
                newItem: Word
            ): Boolean = oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: Word,
                newItem: Word
            ): Boolean = oldItem.id == newItem.id
        }) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder = when (viewType) {
        0 -> SearchItemViewHolder(parent, onClickListener)
        else -> MeaningItemViewHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        if(item == null) {
            //holder.bindPlaceholder()
        } else {
            if (holder is SearchItemViewHolder) {
                holder.bind(item)
            } else if (holder is MeaningItemViewHolder) {
                val meaning = item.meanings!!.first()
                holder.bind(meaning, item, onClickListener)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        return when {
            item?.meanings == null || item.meanings!!.size == 1 -> 1
            else -> 0
        }
    }

}


