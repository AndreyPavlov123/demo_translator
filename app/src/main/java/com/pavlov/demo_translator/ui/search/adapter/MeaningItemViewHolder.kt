package com.pavlov.demo_translator.ui.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pavlov.demo_translator.R
import com.pavlov.demo_translator.core.api.data.MeaningShort
import com.pavlov.demo_translator.core.api.data.Word
import com.pavlov.demo_translator.core.api.data.correctPreviewUrl
import kotlinx.android.synthetic.main.item_meaning.view.*

class MeaningItemViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_meaning, parent, false)
) {
    fun bind(item: MeaningShort, root: Word, meaningClickListener: MeaningClickListener) {
        itemView.text.text = root.text
        itemView.meaning.text = item.translation?.text
        Glide.with(itemView).load(item.correctPreviewUrl).into(itemView.image)
        itemView.setOnClickListener { meaningClickListener(SelectedMeaning(root, root.meanings!!.indexOf(item))) }
    }
}