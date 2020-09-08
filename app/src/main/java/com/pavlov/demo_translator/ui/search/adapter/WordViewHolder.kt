package com.pavlov.demo_translator.ui.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.pavlov.demo_translator.R
import com.pavlov.demo_translator.core.api.data.Word
import com.pavlov.demo_translator.ui.model.MeaningClickListener
import com.pavlov.demo_translator.ui.model.toModel
import com.pavlov.demo_translator.ui.tools.getFilterImageId
import kotlinx.android.synthetic.main.item_word.view.*

class WordViewHolder(parent: ViewGroup, meaningClickListener: MeaningClickListener) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_word, parent, false)) {

    private val expandAdapter = MeaningAdapter(meaningClickListener)

    init {
        itemView.expandLayout.adapter = expandAdapter
    }

    fun bind(item: Word) {
        itemView.text.text = item.text
        itemView.meaning.text = item.meanings?.take(10)?.map { it.translation?.text }?.joinToString()
        itemView.image.setImageResource(item.meanings!!.size.getFilterImageId())
        expandAdapter.submitList(item.meanings!!.map { it.toModel(item.text ?: "") })

        fun applyExpand() {
            if (item.isViewExpanded) {
                itemView.expandButton.setImageResource(R.drawable.ic_arrow_up_drop_circle_outline)
                itemView.expandLayout.isVisible = true
            } else {
                itemView.expandButton.setImageResource(R.drawable.ic_arrow_down_drop_circle_outline)
                itemView.expandLayout.isVisible = false
            }
        }

        applyExpand()

        itemView.expandButton.setOnClickListener {
            item.isViewExpanded = !item.isViewExpanded
            applyExpand()
        }

        itemView.setOnClickListener {
            item.isViewExpanded = !item.isViewExpanded
            applyExpand()
        }
    }
}