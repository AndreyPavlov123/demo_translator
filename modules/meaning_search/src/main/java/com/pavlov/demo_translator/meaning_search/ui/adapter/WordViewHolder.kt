package com.pavlov.demo_translator.meaning_search.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.pavlov.demo_translator.common.getFilterImageId
import com.pavlov.demo_translator.meaning_common.ui.model.MeaningClickListener
import com.pavlov.demo_translator.meaning_search.R
import com.pavlov.demo_translator.meaning_search.databinding.ItemWordBinding
import com.pavlov.demo_translator.meaning_search.repository.dto.Word
import com.pavlov.demo_translator.meaning_search.ui.model.toModel

class WordViewHolder(parent: ViewGroup, meaningClickListener: MeaningClickListener) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_word, parent, false)) {

    private val binding = ItemWordBinding.bind(itemView)
    private val expandAdapter = MeaningAdapter(meaningClickListener)

    init {
        binding.expandLayout.adapter = expandAdapter
    }

    fun bind(item: Word) {
        binding.text.text = item.text
        binding.meaning.text = item.meanings?.take(10)?.map { it.translation?.text }?.joinToString()
        binding.image.setImageResource(item.meanings!!.size.getFilterImageId())
        expandAdapter.submitList(item.meanings!!.map { it.toModel(item.text ?: "") })

        fun applyExpand() {
            if (item.isViewExpanded) {
                binding.expandButton.setImageResource(R.drawable.ic_arrow_up_drop_circle_outline)
                binding.expandLayout.isVisible = true
            } else {
                binding.expandButton.setImageResource(R.drawable.ic_arrow_down_drop_circle_outline)
                binding.expandLayout.isVisible = false
            }
        }

        applyExpand()

        binding.expandButton.setOnClickListener {
            item.isViewExpanded = !item.isViewExpanded
            applyExpand()
        }

        itemView.setOnClickListener {
            item.isViewExpanded = !item.isViewExpanded
            applyExpand()
        }
    }
}