package com.pavlov.demo_translator.meaning_common.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pavlov.demo_translator.meaning_common.R
import com.pavlov.demo_translator.meaning_common.databinding.ItemMeaningBinding
import com.pavlov.demo_translator.meaning_common.ui.model.MeaningClickListener
import com.pavlov.demo_translator.meaning_common.ui.model.MeaningModel
import com.pavlov.demo_translator.meaning_common.ui.model.SelectedMeaningModel

class MeaningViewHolder(
    parent: ViewGroup,
    private val showOnlyMeaning: Boolean)
    : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_meaning, parent, false)
) {
    private val binding = ItemMeaningBinding.bind(itemView)

    init {
        binding.meaning.isVisible = !showOnlyMeaning
    }

    fun bind(item: MeaningModel, meaningClickListener: MeaningClickListener) {
        if (showOnlyMeaning) {
            binding.text.text = item.translation
        } else {
            binding.text.text = item.word
            binding.meaning.text = item.translation
        }
        if (item.previewUrl.isNullOrBlank()) {
            binding.image.isVisible = false
        } else {
            binding.image.isVisible = true
            Glide.with(itemView).load(item.previewUrl).into(binding.image)
        }
        itemView.setOnClickListener { meaningClickListener(SelectedMeaningModel(item.id, item)) }
    }
}