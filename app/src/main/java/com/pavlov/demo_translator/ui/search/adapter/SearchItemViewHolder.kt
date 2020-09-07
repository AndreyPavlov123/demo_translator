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

    fun bind(item: MeaningShortRoot, meaningClickListener: (MeaningShortRoot, Int) -> Unit) {
        itemView.text.text = item.text
        itemView.meaning.text = item.meanings?.take(10)?.map { it.translation?.text }?.joinToString()
        itemView.image.setImageResource(item.meanings!!.size.getFilterImageId())

        fun expand() {
            itemView.expandButton.setImageResource(R.drawable.ic_arrow_up_drop_circle_outline)
            itemView.expandLayout.removeAllViews()
            for ((i, m) in item.meanings!!.withIndex()) {
                val meaningViewHolder = MeaningItemViewHolder(itemView.expandLayout)
                meaningViewHolder.bind(m, item) {
                    meaningClickListener(item, i)
                }
                itemView.expandLayout.post {
                    itemView.expandLayout.addView(meaningViewHolder.itemView)
                }
            }
        }
        fun collapse() {
            itemView.expandButton.setImageResource(R.drawable.ic_arrow_down_drop_circle_outline)
            itemView.expandLayout.removeAllViews()
        }
        fun applyExpand() {
            if (item.isViewExpanded) {
                expand()
            } else {
                collapse()
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