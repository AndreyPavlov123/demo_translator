package com.pavlov.demo_translator.ui.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pavlov.demo_translator.R
import com.pavlov.demo_translator.core.api.MeaningShortRoot
import com.pavlov.demo_translator.ui.tools.getFilterImageId
import kotlinx.android.synthetic.main.item_search.view.*

class SearchItemViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_search, parent, false)) {
    fun bind(item: MeaningShortRoot) {
        itemView.text.text = item.text
        itemView.meaning.text = item.meanings?.take(10)?.map { it.translation?.text }?.joinToString()
        itemView.image.setImageResource(item.meanings!!.size.getFilterImageId())
    }
}