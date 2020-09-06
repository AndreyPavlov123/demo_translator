package com.pavlov.demo_translator.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.pavlov.demo_translator.R
import com.pavlov.demo_translator.api.data.MeaningShortRoot
import kotlinx.android.synthetic.main.item_search.view.*

class SearchAdapter(diffCallback: DiffUtil.ItemCallback<MeaningShortRoot>) :
    PagingDataAdapter<MeaningShortRoot, SearchAdapter.MeaningShortRootViewHolder>(diffCallback) {
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
            holder.bind(item)
        }
    }

    class MeaningShortRootViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: MeaningShortRoot) {
            itemView.textView.text = item.text
        }
    }
}
