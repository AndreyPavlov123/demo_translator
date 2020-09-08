package com.pavlov.demo_translator.ui.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pavlov.demo_translator.R
import com.pavlov.demo_translator.ui.model.MeaningClickListener
import com.pavlov.demo_translator.ui.model.MeaningModel
import com.pavlov.demo_translator.ui.model.SelectedMeaningModel
import kotlinx.android.synthetic.main.item_meaning.view.*

class MeaningViewHolder(
    parent: ViewGroup,
    private val showOnlyMeaning: Boolean)
    : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_meaning, parent, false)
) {
    init {
        itemView.meaning.isVisible = !showOnlyMeaning
    }

    fun bind(item: MeaningModel, meaningClickListener: MeaningClickListener) {
        if (showOnlyMeaning) {
            itemView.text.text = item.translation
        } else {
            itemView.text.text = item.word
            itemView.meaning.text = item.translation
        }
        if (item.previewUrl.isNullOrBlank()) {
            itemView.image.isVisible = false
        } else {
            itemView.image.isVisible = true
            Glide.with(itemView).load(item.previewUrl).into(itemView.image)
        }
        itemView.setOnClickListener { meaningClickListener(SelectedMeaningModel(item.id, item)) }
    }
}