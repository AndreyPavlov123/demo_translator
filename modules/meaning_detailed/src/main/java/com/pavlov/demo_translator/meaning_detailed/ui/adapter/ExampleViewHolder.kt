package com.pavlov.demo_translator.meaning_detailed.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.pavlov.demo_translator.meaning_detailed.R
import com.pavlov.demo_translator.meaning_detailed.databinding.ItemExampleBinding
import com.pavlov.demo_translator.meaning_detailed.repository.dto.Definition
import com.pavlov.demo_translator.meaning_detailed.repository.dto.correctSoundUrl

class ExampleViewHolder(parent: ViewGroup, private val playSoundClickListener: (String) -> Unit) :
    RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_example, parent, false)
    ) {
    private val binding = ItemExampleBinding.bind(itemView)

    fun bind(item: Definition) {
        binding.text.text = item.text
        if (item.correctSoundUrl.isNullOrEmpty()) {
            binding.playSoundButton.isVisible = false
        } else {
            binding.playSoundButton.isVisible = true
            binding.playSoundButton.setOnClickListener { playSoundClickListener(item.correctSoundUrl!!) }
        }
    }
}