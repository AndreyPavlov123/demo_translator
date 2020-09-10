package com.pavlov.demo_translator.meaning_detailed.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.pavlov.demo_translator.meaning_detailed.repository.dto.Definition

class ExampleAdapter(private val playSoundClickListener: (String) -> Unit) :
    ListAdapter<Definition, ExampleViewHolder>(
        object : DiffUtil.ItemCallback<Definition>() {

            override fun areItemsTheSame(
                oldItem: Definition,
                newItem: Definition
            ): Boolean = oldItem.text == newItem.text

            override fun areContentsTheSame(
                oldItem: Definition,
                newItem: Definition
            ): Boolean = oldItem == newItem
        }
    ) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExampleViewHolder =
        ExampleViewHolder(parent, playSoundClickListener)

    override fun onBindViewHolder(holder: ExampleViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }
}