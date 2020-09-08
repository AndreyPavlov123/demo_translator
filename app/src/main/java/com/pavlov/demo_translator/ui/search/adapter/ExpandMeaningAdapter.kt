package com.pavlov.demo_translator.ui.search.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.pavlov.demo_translator.core.api.data.MeaningShort
import com.pavlov.demo_translator.core.api.data.Word

class ExpandMeaningAdapter(private val clickListener: (Word, Int) -> Unit) : ListAdapter<Pair<Word, MeaningShort>, MeaningItemViewHolder>(
    object : DiffUtil.ItemCallback<Pair<Word, MeaningShort>>() {
        override fun areItemsTheSame(
            oldItem: Pair<Word, MeaningShort>,
            newItem: Pair<Word, MeaningShort>
        ): Boolean = oldItem.second.id == newItem.second.id

        override fun areContentsTheSame(
            oldItem: Pair<Word, MeaningShort>,
            newItem: Pair<Word, MeaningShort>
        ): Boolean = oldItem.second.id == newItem.second.id
    }
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MeaningItemViewHolder =
        MeaningItemViewHolder(parent)

    override fun onBindViewHolder(holder: MeaningItemViewHolder, position: Int) {
        val (a, b) = getItem(position)
        holder.bind(b, a, clickListener)
    }
}