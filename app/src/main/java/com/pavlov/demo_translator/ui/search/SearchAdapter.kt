package com.pavlov.demo_translator.ui.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.pavlov.demo_translator.R
import com.pavlov.demo_translator.core.api.MeaningShortRoot
import kotlinx.android.synthetic.main.item_search.view.*

class SearchAdapter(private val onClickListener: (MeaningShortRoot, Int) -> Unit) :
    PagingDataAdapter<MeaningShortRoot, SearchAdapter.MeaningShortRootViewHolder>(
        object : DiffUtil.ItemCallback<MeaningShortRoot>() {
            override fun areItemsTheSame(
                oldItem: MeaningShortRoot,
                newItem: MeaningShortRoot
            ): Boolean = oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: MeaningShortRoot,
                newItem: MeaningShortRoot
            ): Boolean = oldItem.id == newItem.id
        }) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MeaningShortRootViewHolder {
        return MeaningShortRootViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_search, parent, false))
    }

    override fun onBindViewHolder(holder: MeaningShortRootViewHolder, position: Int) {
        val item = getItem(position)
        if(item == null) {
            //holder.bindPlaceholder()
        } else {
            holder.bind(item) { onClickListener(item, position) }
        }
    }

    class MeaningShortRootViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: MeaningShortRoot, onClickListener: () -> Unit) {
            itemView.word.text = item.text
            itemView.meaning.text = item.meanings?.take(3)?.map { it.translation?.text }?.joinToString()
            itemView.setOnClickListener { onClickListener() }
        }
    }
}
