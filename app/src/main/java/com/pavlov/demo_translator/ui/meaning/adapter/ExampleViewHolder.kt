package com.pavlov.demo_translator.ui.meaning.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.pavlov.demo_translator.R
import com.pavlov.demo_translator.core.api.data.Definition
import com.pavlov.demo_translator.core.api.data.correctSoundUrl
import kotlinx.android.synthetic.main.item_example.view.*

class ExampleViewHolder(parent: ViewGroup, private val playSoundClickListener: (String) -> Unit)
    : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_example, parent, false)
) {
    fun bind(item: Definition) {
        itemView.text.text = item.text
        if (item.correctSoundUrl.isNullOrEmpty()) {
            itemView.playSoundButton.isVisible = false
        } else {
            itemView.playSoundButton.isVisible = true
            itemView.playSoundButton.setOnClickListener { playSoundClickListener(item.correctSoundUrl!!) }
        }
    }
}