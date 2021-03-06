package com.pavlov.demo_translator.meaning_search.ui.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.pavlov.demo_translator.meaning_common.ui.model.MeaningClickListener
import com.pavlov.demo_translator.meaning_search.repository.dto.Word
import com.pavlov.demo_translator.meaning_search.ui.model.toModel

class WordAdapter(private val meaningClickListener: MeaningClickListener) :
    PagingDataAdapter<Word, RecyclerView.ViewHolder>(

        object : DiffUtil.ItemCallback<Word>() {
            override fun areItemsTheSame(
                oldItem: Word,
                newItem: Word
            ): Boolean = oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: Word,
                newItem: Word
            ): Boolean = oldItem == newItem
        }) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder = when (viewType) {
        0 -> WordViewHolder(parent, meaningClickListener)
        else -> MeaningViewHolder(parent, showOnlyMeaning = false)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        if (item == null) {
            //holder.bindPlaceholder()
        } else {
            if (holder is WordViewHolder) {
                holder.bind(item)
            } else if (holder is MeaningViewHolder) {
                val meaning = item.meanings!!.first()
                holder.bind(meaning.toModel(item.text ?: ""), meaningClickListener)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        return when {
            item?.meanings == null || item.meanings.size == 1 -> 1
            else -> 0
        }
    }
}


