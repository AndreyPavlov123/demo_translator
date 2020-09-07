package com.pavlov.demo_translator.ui.search.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.pavlov.demo_translator.core.api.MeaningShort
import com.pavlov.demo_translator.core.api.MeaningShortRoot

class ExpandMeaningAdapter(private val clickListener: (MeaningShortRoot, Int) -> Unit) : ListAdapter<Pair<MeaningShortRoot, MeaningShort>, MeaningItemViewHolder>(
    object : DiffUtil.ItemCallback<Pair<MeaningShortRoot, MeaningShort>>() {
        override fun areItemsTheSame(
            oldItem: Pair<MeaningShortRoot, MeaningShort>,
            newItem: Pair<MeaningShortRoot, MeaningShort>
        ): Boolean = oldItem.second.id == newItem.second.id

        override fun areContentsTheSame(
            oldItem: Pair<MeaningShortRoot, MeaningShort>,
            newItem: Pair<MeaningShortRoot, MeaningShort>
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