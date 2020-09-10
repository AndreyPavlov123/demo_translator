package com.pavlov.demo_translator.meaning_search.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.pavlov.demo_translator.meaning_common.ui.model.MeaningClickListener
import com.pavlov.demo_translator.meaning_common.ui.model.MeaningModel

class MeaningAdapter(private val meaningClickListener: MeaningClickListener) :
    ListAdapter<MeaningModel, MeaningViewHolder>(

        object : DiffUtil.ItemCallback<MeaningModel>() {
            override fun areItemsTheSame(
                oldItem: MeaningModel,
                newItem: MeaningModel
            ): Boolean = oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: MeaningModel,
                newItem: MeaningModel
            ): Boolean = oldItem == newItem
        }
    ) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MeaningViewHolder =
        MeaningViewHolder(parent, showOnlyMeaning = true)

    override fun onBindViewHolder(holder: MeaningViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, meaningClickListener)
    }
}